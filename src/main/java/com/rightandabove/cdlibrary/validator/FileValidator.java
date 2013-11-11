package com.rightandabove.cdlibrary.validator;

import com.rightandabove.cdlibrary.model.UploadedFile;
import com.rightandabove.cdlibrary.service.HelperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Arsen Adzhiametov on 11/5/13 in IntelliJ IDEA.
 */
@Component
public class FileValidator implements Validator {

    static final Logger LOG = LoggerFactory.getLogger(FileValidator.class);

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
            LOG.error("File size = 0");
            errors.rejectValue("file", "please.select.a.file");
        } else if (!XMLValidation.validateByXSDSchema(file.getFile(), helperService.getXsdFilePath())) {
            LOG.error("Validation failed. File not supported");
            errors.rejectValue("file", "file.not.supported");
        }


    }
}
