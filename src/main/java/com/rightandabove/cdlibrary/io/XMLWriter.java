package com.rightandabove.cdlibrary.io;

import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created by Arsen Adzhiametov on 11/6/13 in IntelliJ IDEA.
 */
@Service
public class XMLWriter<T> {

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
        }
    }
}
