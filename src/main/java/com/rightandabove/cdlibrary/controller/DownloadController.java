package com.rightandabove.cdlibrary.controller;

import com.rightandabove.cdlibrary.service.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Arsen Adzhiametov on 11/6/13 in IntelliJ IDEA.
 *
 * Traditional simple controller for downloading file.
 * But I think in this controller I have to do file
 * reading synchronized with file modification.
 */
@Controller
public class DownloadController {

    public static final int BUFFER_SIZE = 4096;

    @Autowired
    HelperService helperService;

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void doDownload(HttpServletResponse response) throws IOException {
        File downloadFile = new File(helperService.getXmlFilePath());
        FileInputStream inputStream = new FileInputStream(downloadFile);

        helperService.prepareResponse(response, downloadFile);

        OutputStream outStream = response.getOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        } finally {
            inputStream.close();
            outStream.close();
        }
    }


}
