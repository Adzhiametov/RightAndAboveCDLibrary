package com.rightandabove.cdlibrary.io;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by Arsen Adzhiametov on 11/5/13 in IntelliJ IDEA.
 */
@Service
public class FileSaver {

    public void saveFile(MultipartFile file, String destinationFilePath) {
        InputStream inputStream;
        OutputStream outputStream;
        try {
            inputStream = file.getInputStream();

            File newFile = new File(destinationFilePath);
            System.out.println(newFile.getAbsolutePath());
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
