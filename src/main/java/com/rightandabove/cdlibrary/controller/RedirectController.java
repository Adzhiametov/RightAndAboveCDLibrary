package com.rightandabove.cdlibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Created by Arsen Adzhiametov on 11/7/13 in IntelliJ IDEA.
 *
 * The controller acts as a navigator. In some cases it is
 * convenient to prepare the model for some view.
 */
@Controller
public class RedirectController {

    @RequestMapping(value = "/gotoupload", method = RequestMethod.GET)
    public String goToUploadFilePage() {
        return "upload";
    }

    @RequestMapping(value = "/gotodownload", method = RequestMethod.GET)
    public String goToDownloadFilePage() {
        return "download";
    }
}