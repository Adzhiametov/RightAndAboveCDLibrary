package com.rightandabove.cdlibrary.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * Created by Arsen Adzhiametov on 11/5/13 in IntelliJ IDEA.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CD")
public class CompactDisc {

    @XmlElement(name = "TITLE")
    private String title;
    @XmlElement(name = "ARTIST")
    private String artist;
    @XmlElement(name = "COUNTRY")
    private String country;
    @XmlElement(name = "COMPANY")
    private String company;
    @XmlElement(name = "PRICE")
    private BigDecimal price;
    @XmlElement(name = "YEAR")
    private Integer year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompactDisc that = (CompactDisc) o;

        if (!title.equals(that.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CompactDisc{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", country='" + country + '\'' +
                ", company='" + company + '\'' +
                ", price=" + price +
                ", year=" + year +
                '}';
    }
}
