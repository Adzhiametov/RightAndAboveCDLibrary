package com.rightandabove.cdlibrary.controller;

import com.rightandabove.cdlibrary.entity.Catalog;
import com.rightandabove.cdlibrary.entity.CompactDisc;
import com.rightandabove.cdlibrary.io.XMLReader;
import com.rightandabove.cdlibrary.io.XMLWriter;
import com.rightandabove.cdlibrary.model.UploadedFile;
import com.rightandabove.cdlibrary.validator.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Arsen Adzhiametov on 11/5/13 in IntelliJ IDEA.
 */
@Controller
public class UploadController {

    @Autowired
    FileValidator fileValidator;
    @Autowired
    XMLReader<Catalog> xmlReader;
    @Autowired
    XMLWriter<Catalog> xmlWriter;
    @Autowired
    ServletContext servletContext;

    private static String relativeDestinationPath = "/WEB-INF/catalog/catalog.xml";

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String uploadFile(
            @ModelAttribute("uploadedFile")
            UploadedFile uploadedFile,
            BindingResult result) {

        MultipartFile file = uploadedFile.getFile();
        fileValidator.validate(uploadedFile, result);
        if (result.hasErrors()) {
            return "upload_file";
        }

        Catalog newCatalog;
        try {
            newCatalog = xmlReader.readFromStream(file.getInputStream(), Catalog.class);
        } catch (IOException e) {
            e.printStackTrace();
            result.rejectValue("file", "uploadForm.selectFile", "Something goes wrong");
            return "upload_file";
        }

        Catalog oldCatalog = xmlReader.readFromFile(getFilePath(), Catalog.class);
        Set<CompactDisc> cds = newCatalog.getCds();
        cds.addAll(oldCatalog.getCds());
        newCatalog.setCds(cds);
        xmlWriter.saveToFile(newCatalog, getFilePath());

        return "redirect:show/first";
    }

    private String getFilePath() {
        return servletContext.getRealPath(relativeDestinationPath);
    }


}
