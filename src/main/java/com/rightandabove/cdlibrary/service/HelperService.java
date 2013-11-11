package com.rightandabove.cdlibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by Arsen Adzhiametov on 11/11/13.
 */
@Service
public class HelperService {

    private static final String DEFAULT_MIME_TYPE = "application/octet-stream";

    @Value("${xml.path.relative}")
    public String xmlPathRelative;
    @Value("${xsd.path.relative}")
    public String xsdPathRelative;

    @Autowired
    ServletContext servletContext;

    public String getXmlFilePath() {
        return servletContext.getRealPath(xmlPathRelative);
    }

    public String getXsdFilePath() {
        return servletContext.getRealPath(xsdPathRelative);
    }

    public String getMimeType() {
        String mimeType = servletContext.getMimeType(getXmlFilePath());
        if (mimeType == null) {
            mimeType = DEFAULT_MIME_TYPE;
        }
        return mimeType;
    }

    public void prepareResponse(HttpServletResponse response, File downloadFile) {
        String mimeType = getMimeType();
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
        response.setHeader(headerKey, headerValue);
    }
}
