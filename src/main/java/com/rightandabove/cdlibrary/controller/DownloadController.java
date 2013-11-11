package com.rightandabove.cdlibrary.controller;

import com.rightandabove.cdlibrary.IOConstants;
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

import static com.rightandabove.cdlibrary.IOConstants.BUFFER_SIZE;

/**
 * Created by Arsen Adzhiametov on 11/6/13 in IntelliJ IDEA.
 */
@Controller
public class DownloadController {

    @Autowired
    HelperService helperService;

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void doDownload(HttpServletResponse response) throws IOException {
        File downloadFile = new File(helperService.getXmlFilePath());
        FileInputStream inputStream = new FileInputStream(downloadFile);

        helperService.prepeareResponse(response, downloadFile);

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
