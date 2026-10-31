package com.vanesline.ecommerce.service;

import java.io.InputStream;

public interface StorageService {

    void uploadFile(String key, InputStream inputStream);

    InputStream downloadFile(String key);

    void deleteFile(String key);
}
