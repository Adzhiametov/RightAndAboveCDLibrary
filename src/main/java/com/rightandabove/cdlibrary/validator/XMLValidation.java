package com.rightandabove.cdlibrary.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Created by Arsen Adzhiametov on 11/5/13 in IntelliJ IDEA.
 *
 * Class provide XML validation via XSD.
 */
public class XMLValidation {

    static final Logger LOG = LoggerFactory.getLogger(XMLValidation.class);

    public static boolean validateByXSDSchema(MultipartFile file, String xsdPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(file.getInputStream()));
        } catch (IOException e) {
            LOG.error("Exception: " + e.getMessage());
            return false;
        } catch (SAXException e) {
            LOG.error("Exception: " + e.getMessage());
            return false;
        }
        return true;
    }
}
