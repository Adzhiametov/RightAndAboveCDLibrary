package com.rightandabove.cdlibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import java.io.File;

import static com.rightandabove.cdlibrary.IOConstants.XML_PATH_RELATIVE;
import static com.rightandabove.cdlibrary.IOConstants.XSD_PATH_RELATIVE;

/**
 * Created by Arsen Adzhiametov on 11/11/13.
 */
@Service
public class HelperService {

    private static final String DEFAULT_MIME_TYPE = "application/octet-stream";

    @Autowired
    ServletContext servletContext;

    public String getXmlFilePath(){
        return servletContext.getRealPath(XML_PATH_RELATIVE);
    }

    public String getXsdFilePath(){
        return servletContext.getRealPath(XSD_PATH_RELATIVE);
    }

    public String getMimeType(){
        String mimeType = servletContext.getMimeType(getXmlFilePath());
        if (mimeType == null) {
            mimeType = DEFAULT_MIME_TYPE;
        }
        return mimeType;
    }

    public void prepeareResponse(HttpServletResponse response, File downloadFile) {
        String mimeType = getMimeType();
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        response.setHeader(headerKey, headerValue);
    }
}
