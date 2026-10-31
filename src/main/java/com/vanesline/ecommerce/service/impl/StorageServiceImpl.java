package com.vanesline.ecommerce.service.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vanesline.ecommerce.service.StorageService;

import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final S3Template s3Template;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Override
    public void uploadFile(String key, InputStream inputStream) {
        s3Template.upload(bucketName, key, inputStream);
    }

    @Override
    public InputStream downloadFile(String key) {
        try {
            return s3Template.download(bucketName, key)
                    .getInputStream();
        } catch (Exception e) {
            throw new RuntimeException("Cannot download file from S3", e);
        }
    }

    @Override
    public void deleteFile(String key) {
        s3Template.deleteObject(bucketName, key);
    }
}
