/**
 * Project Name:erp
 * File Name:FastDFS.java
 * Package Name:com.briup.apps.erp.util
 * Date:2018年9月12日下午10:08:14
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.briup.apps.ej.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.util.ResourceUtils;
/**
 * ClassName:FastDFS <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2018年9月12日 下午10:08:14 <br/>
 * @author   lichunyu
 * @version
 * @since    JDK 1.6
 * @see
 */
public class FastDFS {
	static {
		// 初始化配置文件
		try {
			ClientGlobal.init(ResourceUtils.getFile("classpath:fdfs_client.conf").getAbsolutePath());
//			ClientGlobal.init("/home/charles/fdfs_client.conf");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除fastDFS服务器上的图片
	 * */
	public static int delete(String remote_filename) throws Exception {
		String group_name ="group1";
	    try {
			// 获取跟踪服务器
			TrackerClient trackerClient = new TrackerClient();
			TrackerServer trackerServer = trackerClient.getConnection();
			// 声明存储服务器， 初始化存储客户端对象
			StorageServer storageServer = null;
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);
			int result = storageClient.delete_file(group_name, remote_filename);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 上传fastDFS服务器上的图片
	 * */
	public static String[] upload(byte[] local_filename,String ext_name) throws Exception {
		try {
			// 获取跟踪服务器
			TrackerClient trackerClient = new TrackerClient();
			TrackerServer trackerServer = trackerClient.getConnection();
			// 声明存储服务器， 初始化存储客户端对象
			StorageServer storageServer = null;
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);
			// 默认组为null
			String groupName = null;
			// 从跟踪客户端中获取所有的存储服务器
			StorageServer[] storageServers = trackerClient.getStoreStorages(trackerServer, groupName);
			if(storageServers == null) {
				System.out.println("无存储服务器");
			} else {
				int index = 0;
				for(StorageServer s : storageServers) {
					System.out.println("第"+(++index)+"台存储服务器："+s.getInetSocketAddress());
				}
			}
			NameValuePair[] meta_list = new NameValuePair[] {};
			String[] result = storageClient.upload_file(local_filename, ext_name, meta_list);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
}

