package com.qilin.tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class UrlSource {

    private final String url;

    public UrlSource(String url) {
        this.url = url;
    }

    public InputStream inputStream() throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

        // 检查URL是否包含用户名和密码
        String userInfo = urlObj.getUserInfo();
        if (userInfo != null) {
            // 设置Basic Auth头信息
            String encodedUserInfo = Base64.getEncoder().encodeToString(userInfo.getBytes(StandardCharsets.UTF_8));
            connection.setRequestProperty("Authorization", "Basic " + encodedUserInfo);
        }

        // 设置请求方法为GET
        connection.setRequestMethod("GET");

        // 获取响应码
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            return connection.getInputStream();
        } else {
            throw new IOException("Failed to fetch data from URL. Response code: " + responseCode);
        }
    }
}