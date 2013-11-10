package com.rightandabove.cdlibrary.service;

import com.rightandabove.cdlibrary.entity.Catalog;
import com.rightandabove.cdlibrary.entity.CompactDisc;
import com.rightandabove.cdlibrary.io.XMLReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Arsen Adzhiametov on 11/9/13 in IntelliJ IDEA.
 */
@Service
public class ConcurrentResourceModifier {

    @Autowired
    XMLReadWriteAccess<Catalog> xmlReadWriteAccess;

    private Lock lock = new ReentrantReadWriteLock().writeLock();

    public void readUpdateWrite(Catalog newCatalog, String filePath) {
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
