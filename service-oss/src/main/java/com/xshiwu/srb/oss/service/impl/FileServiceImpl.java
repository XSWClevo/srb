package com.xshiwu.srb.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.xshiwu.srb.oss.service.FileService;
import com.xshiwu.srb.oss.util.OssProperties;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author xsw
 * @version 1.0
 * @date 2021/4/17 18:49
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {
    /**
     * 文件上传至阿里云
     *
     * @param inputStream 输入流
     * @param module      模块
     * @param fileName    文件名
     */
    @Override
    public String upload(InputStream inputStream, String module, String fileName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(
                OssProperties.ENDPOINT,
                OssProperties.KEY_ID,
                OssProperties.KEY_SECRET);
        //判断oss实例是否存在：如果不存在则创建，如果存在则获取
        if (!ossClient.doesBucketExist(OssProperties.BUCKET_NAME)) {
            //创建bucket
            ossClient.createBucket(OssProperties.BUCKET_NAME);
            //设置oss实例的访问权限：公共读
            ossClient.setBucketAcl(OssProperties.BUCKET_NAME, CannedAccessControlList.PublicRead);
        }

        //构建日期路径：avatar/2019/02/26/文件名
        String folder = new DateTime().toString("yyyy/MM/dd");

        //文件名：uuid.扩展名
        fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        //文件根路径
        String key = module + "/" + folder + "/" + fileName;

        //文件上传至阿里云
        ossClient.putObject(OssProperties.BUCKET_NAME, key, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        //阿里云文件绝对路径
        return "https://" + OssProperties.BUCKET_NAME + "." + OssProperties.ENDPOINT + "/" + key;
    }

    @Override
    public void remove(String url) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(
                OssProperties.ENDPOINT,
                OssProperties.KEY_ID,
                OssProperties.KEY_SECRET);
        //文件名（服务器上的文件路径）
        String host = "https://" + OssProperties.BUCKET_NAME + "." + OssProperties.ENDPOINT + "/";
        log.info("host:{}",host.length());
        // 返回一个字符串，该字符串是该字符串的子字符串。 子字符串以指定索引处的字符开头，并延伸到该字符串的末尾。
        String objectName = url.substring(host.length());
        log.info("Bucket_name: {}", OssProperties.BUCKET_NAME);
        log.info("objectName:{}",objectName);
        // 删除文件。相当于查找srb-oss下面ObjectName的文件名
        // OssProperties.BUCKET_NAME：srb-oss
        // objectName: WeNet/2021/04/17/4464a8dd-8fa3-4c24-924e-90f067e39c6a.jpg
        ossClient.deleteObject(OssProperties.BUCKET_NAME, objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
