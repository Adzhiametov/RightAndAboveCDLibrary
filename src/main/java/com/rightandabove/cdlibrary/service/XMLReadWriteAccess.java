package com.rightandabove.cdlibrary.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Arsen Adzhiametov on 11/6/13 in IntelliJ IDEA.
 *
 * Class that just provides reading and writing of file and transform xml to
 * object representation and vice versa.
 */
@Service
public class XMLReadWriteAccess<T> {

    static final Logger LOG = LoggerFactory.getLogger(XMLReadWriteAccess.class);

    public T readFromFile(String filePath, Class<T> clas) {
        T object = null;
        JAXBContext context = null;
        Unmarshaller unMarshaller = null;
        try {
            context = JAXBContext.newInstance(clas);
            unMarshaller = context.createUnmarshaller();
            object = (T) unMarshaller.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
            LOG.error("Exception " + e);
        }
        return object;
    }

    public T readFromStream(InputStream inputStream, Class<T> clas) {
        T object = null;
        JAXBContext context = null;
        Unmarshaller unMarshaller = null;
        try {
            context = JAXBContext.newInstance(clas);
            unMarshaller = context.createUnmarshaller();
            object = (T) unMarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
            LOG.error("Exception " + e);
        }
        return object;
    }

    public void saveToFile(T object, String destinationFilePath) {
        JAXBContext jaxbContext = null;
        Marshaller marshaller = null;
        try {
            jaxbContext = JAXBContext.newInstance(object.getClass());
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(object, new File(destinationFilePath));
        } catch (JAXBException e) {
            e.printStackTrace();
            LOG.error("Exception " + e);
        }
    }
}
