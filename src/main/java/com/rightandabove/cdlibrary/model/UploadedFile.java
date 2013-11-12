package com.rightandabove.cdlibrary.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Arsen Adzhiametov on 11/5/13 in IntelliJ IDEA.
 *
 * Model for spring form. Serves like DTO.
 */
public class UploadedFile {

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
