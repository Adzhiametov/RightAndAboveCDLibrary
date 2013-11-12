package com.rightandabove.cdlibrary.service;

import com.rightandabove.cdlibrary.entity.Catalog;
import com.rightandabove.cdlibrary.entity.CompactDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Arsen Adzhiametov on 11/9/13 in IntelliJ IDEA.
 *
 * Service for concurrent modification of target file.
 */
@Service
public class ConcurrentResourceReadWriter {

    @Autowired
    XMLReadWriteAccess<Catalog> xmlReadWriteAccess;

    private Lock lock = new ReentrantLock();

    public void updateCatalog(Catalog newCatalog, String filePath) {
        try {
            lock.lock();
            Catalog oldCatalog = xmlReadWriteAccess.readFromFile(filePath, Catalog.class);
            Set<CompactDisc> cds = newCatalog.getCds();
            cds.addAll(oldCatalog.getCds());
            newCatalog.setCds(cds);
            xmlReadWriteAccess.saveToFile(newCatalog, filePath);
        } finally {
            lock.unlock();
        }
    }


}
