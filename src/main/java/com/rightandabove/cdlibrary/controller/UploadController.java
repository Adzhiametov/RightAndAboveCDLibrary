package com.rightandabove.cdlibrary.controller;

import com.rightandabove.cdlibrary.entity.Catalog;
import com.rightandabove.cdlibrary.io.XMLReadWriteAccess;
import com.rightandabove.cdlibrary.model.UploadedFile;
import com.rightandabove.cdlibrary.service.ConcurrentResourceReadWriter;
import com.rightandabove.cdlibrary.service.HelperService;
import com.rightandabove.cdlibrary.validator.FileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arsen Adzhiametov on 11/5/13 in IntelliJ IDEA.
 */
@Controller
public class UploadController implements HandlerExceptionResolver {

    static final Logger LOG = LoggerFactory.getLogger(UploadController.class);
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
        fileValidator.validate(uploadedFile, result);
        if (result.hasErrors()) {
            return "upload";
        }

        MultipartFile file = uploadedFile.getFile();
        Catalog newCatalog;
        try {
            newCatalog = xmlReadWriteAccess.readFromStream(file.getInputStream(), Catalog.class);
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Exception " + e);
            result.rejectValue("file", "something.goes.wrong");
            return "upload";
        }

        concurrentResourceReadWriter.updateCatalog(newCatalog, helperService.getXmlFilePath());
        return "redirect:show/first";
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        Map<Object, Object> model = new HashMap<Object, Object>();
        if (exception instanceof MaxUploadSizeExceededException) {
            model.put(
                    "errors",
                    MessageFormat.format(("File size should be less then {0} byte."),
                    ((MaxUploadSizeExceededException) exception).getMaxUploadSize())
            );
            LOG.error("FileSizeException " + exception);
        } else {
            model.put(
                    "errors",
                    "Unexpected error");
            LOG.error("Unexpected error " + exception);
        }
        return new ModelAndView("upload", (Map) model);
    }
}
