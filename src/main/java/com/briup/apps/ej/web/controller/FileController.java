package com.briup.apps.ej.web.controller;

import com.briup.apps.ej.utils.Message;
import com.briup.apps.ej.utils.MessageUtil;
import com.briup.apps.ej.bean.BaseFile;
import com.briup.apps.ej.service.IBaseFileService;
import com.briup.apps.ej.utils.FastDFS;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: paz
 * @description: 文件控制器类
 * @author: charles
 * @create: 2019-03-15 23:15
 **/
@Api(description = "文件上传相关接口")
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private IBaseFileService baseFileService;

    @ApiOperation(value="远程文件删除")
    @GetMapping("deleteById")
    public Message delete(
            @ApiParam(value = "文件id",required = true) String id
    ) throws Exception {
        int code = FastDFS.delete(id);
        if(code == 0) {
            baseFileService.deleteById(id);
            return MessageUtil.success("删除成功");
        } else {
            return MessageUtil.error("删除失败");
        }
    }


    @ApiOperation(value="文件上传",notes="文件大小限制为3M,附件服务器地址：http://134.175.100.63:8686 。 图片地址为 【服务器地址/groupName/id】,例如 http://134.175.100.63:8686/group1/M00/00/19/rBAACV21epyAI-4vAAABwSILlJ0364.txt")
    @PostMapping("upload")
    public Message upload(
            @RequestParam("file") MultipartFile file, HttpServletRequest req
    ) throws Exception{
        String fileName = file.getOriginalFilename();
        String ext_name = fileName.substring(fileName.lastIndexOf(".") + 1);
        if(ext_name.contains("?")){
            ext_name = ext_name.substring(0,ext_name.indexOf("?"));
        }
        long fileSize = file.getSize();
        if(fileSize > 3145728) {
            return MessageUtil.error("文件大小不能超过了3M");
        }

        String[] result = FastDFS.upload(file.getBytes(),ext_name);
        if(result!=null && result.length >1) {
            String erpGroupName = result[0];
            String erpFileName = result[1];
            BaseFile baseFile = new BaseFile();
            baseFile.setFilename(fileName);
            baseFile.setId(erpFileName);
            baseFile.setGroupname(erpGroupName);
            baseFile.setUploadtime(new Date().getTime()+"");
            baseFile.setFilesize(fileSize);

            baseFileService.saveOrUpdate(baseFile);
            //上传成功
            return MessageUtil.success("success",baseFile);
        }
        return MessageUtil.error("上传失败");
    }
}
