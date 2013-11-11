package com.rightandabove.cdlibrary.validator;

import com.rightandabove.cdlibrary.model.UploadedFile;
import com.rightandabove.cdlibrary.service.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Arsen Adzhiametov on 11/5/13 in IntelliJ IDEA.
 */
@Component
public class FileValidator implements Validator {

    @Autowired
    HelperService helperService;

    @Override
    public boolean supports(Class arg0) {
        return UploadedFile.class.isAssignableFrom(arg0);
    }

    @Override
    public void validate(Object uploadedFile, Errors errors) {

        UploadedFile file = (UploadedFile) uploadedFile;

        if (file.getFile().getSize() == 0) {
            errors.rejectValue("file", "please.select.a.file");
        } else if (!XMLValidation.validateByXSDSchema(file.getFile(), helperService.getXsdFilePath())) {
            errors.rejectValue("file", "file.not.supported");
        }


    }
}
