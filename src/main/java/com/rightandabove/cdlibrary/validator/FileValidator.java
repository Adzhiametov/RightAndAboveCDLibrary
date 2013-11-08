package com.rightandabove.cdlibrary.validator;

import com.rightandabove.cdlibrary.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.servlet.ServletContext;

/**
 * Created by Arsen Adzhiametov on 11/5/13 in IntelliJ IDEA.
 */
@Component
public class FileValidator implements Validator {

    @Autowired
    ServletContext context;

    private static String xsdPathrelative = "/WEB-INF/resources/catalog.xsd";

    @Override
    public boolean supports(Class arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void validate(Object uploadedFile, Errors errors) {

        UploadedFile file = (UploadedFile) uploadedFile;

        if (file.getFile().getSize() == 0) {
            errors.rejectValue("file", "uploadForm.selectFile", "Please select a file!");
        } else if (!XMLValidation.validateByXSDSchema(file.getFile(), context.getRealPath(xsdPathrelative))) {
            errors.rejectValue("file", "uploadForm.selectFile", "File not supported");
        }


    }
}
