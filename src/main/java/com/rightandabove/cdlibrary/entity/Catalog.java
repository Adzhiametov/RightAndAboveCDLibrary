package com.rightandabove.cdlibrary.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Arsen Adzhiametov on 11/5/13 in IntelliJ IDEA.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CATALOG")
public class Catalog {

    @XmlElement(name = "CD", type = CompactDisc.class)
    private Set<CompactDisc> cds = new HashSet<CompactDisc>();

    public Set<CompactDisc> getCds() {
        return cds;
    }

    public void setCds(Set<CompactDisc> cds) {
        this.cds = cds;
    }
}
