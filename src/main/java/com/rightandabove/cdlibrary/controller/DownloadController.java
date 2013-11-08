package com.rightandabove.cdlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Arsen Adzhiametov on 11/6/13 in IntelliJ IDEA.
 */
@Controller
public class DownloadController {

    private static final int BUFFER_SIZE = 4096;
    private static final String DEFAULT_MIME_TYPE = "application/octet-stream";

    private String filePath = "/WEB-INF/catalog/catalog.xml";

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void doDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String fullPath = servletContext.getRealPath(filePath);

        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        String mimeType = servletContext.getMimeType(fullPath);
        if (mimeType == null) {
            mimeType = DEFAULT_MIME_TYPE;
        }
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        OutputStream outStream = response.getOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();
    }
}
