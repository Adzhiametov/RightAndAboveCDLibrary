package com.rightandabove.cdlibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToUploadFilePage() {
        return "upload_file";
    }

    @RequestMapping(value = "/gotodownload", method = RequestMethod.GET)
    public String goToDownloadFilePage() {
        return "download_file";
    }
}