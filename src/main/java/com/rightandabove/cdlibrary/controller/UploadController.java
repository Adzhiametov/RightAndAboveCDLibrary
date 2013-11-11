package com.rightandabove.cdlibrary.controller;

import com.rightandabove.cdlibrary.entity.Catalog;
import com.rightandabove.cdlibrary.io.XMLReadWriteAccess;
import com.rightandabove.cdlibrary.model.UploadedFile;
import com.rightandabove.cdlibrary.service.ConcurrentResourceReadWriter;
import com.rightandabove.cdlibrary.service.HelperService;
import com.rightandabove.cdlibrary.validator.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Arsen Adzhiametov on 11/5/13 in IntelliJ IDEA.
 */
@Controller
public class UploadController {

    @Autowired
    FileValidator fileValidator;
    @Autowired
    XMLReadWriteAccess<Catalog> xmlReadWriteAccess;
    @Autowired
    HelperService helperService;
    @Autowired
    ConcurrentResourceReadWriter concurrentResourceReadWriter;

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String uploadFile(@ModelAttribute("uploadedFile") UploadedFile uploadedFile, BindingResult result) {
        MultipartFile file = uploadedFile.getFile();
        fileValidator.validate(uploadedFile, result);
        if (result.hasErrors()) {
            return "upload";
        }
        Catalog newCatalog;
        try {
            newCatalog = xmlReadWriteAccess.readFromStream(file.getInputStream(), Catalog.class);
        } catch (IOException e) {
            e.printStackTrace();
            result.rejectValue("file", "something.goes.wrong");
            return "upload";
        }
        concurrentResourceReadWriter.updateCatalog(newCatalog, helperService.getXmlFilePath());
        return "redirect:show/first";
    }


}
