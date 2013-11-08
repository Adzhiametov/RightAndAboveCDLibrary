package com.rightandabove.cdlibrary.io;

import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Arsen Adzhiametov on 11/6/13 in IntelliJ IDEA.
 */
@Service
public class XMLReader<T> {

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
        }
        return object;
    }

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
        }
        return object;
    }
}
