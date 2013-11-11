package com.rightandabove.cdlibrary.validator;

import com.rightandabove.cdlibrary.IOConstants;
import com.rightandabove.cdlibrary.model.UploadedFile;
import com.rightandabove.cdlibrary.service.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.servlet.ServletContext;

import static com.rightandabove.cdlibrary.IOConstants.XSD_PATH_RELATIVE;

/**
 * Created by Arsen Adzhiametov on 11/5/13 in IntelliJ IDEA.
 */
@Component
public class FileValidator implements Validator {

    @Autowired
    HelperService helperService;

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
        } else if (!XMLValidation.validateByXSDSchema(file.getFile(), helperService.getXsdFilePath())) {
            errors.rejectValue("file", "uploadForm.selectFile", "File not supported");
        }


    }
}
