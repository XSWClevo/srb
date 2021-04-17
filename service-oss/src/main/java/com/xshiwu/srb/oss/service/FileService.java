package com.xshiwu.srb.oss.service;

import java.io.InputStream;

/**
 * @author xsw
 * @version 1.0
 * @date 2021/4/17 18:48
 */
public interface FileService {
    /**
     * 文件上传至阿里云
     */
    String upload(InputStream inputStream, String module, String fileName);

    void remove(String url);
}
