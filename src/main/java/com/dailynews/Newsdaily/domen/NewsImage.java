package com.dailynews.Newsdaily.domen;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Entity
public class NewsImage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private String imageSource;
    private String contentType;
    private long contentSize;
    private String url;
    private Instant imageCreationDTM=Instant.now();

    private Date imageUpdationDTM;
    @JsonIgnore
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "newsid")
    private News news;
    @Lob
    private byte[] data;
    public NewsImage() {
    }

    public NewsImage(String imageSource,String url, boolean isActive, Instant imageCreationDTM, Date imageUpdationDTM, News news) {
        this.imageSource = imageSource;
        this.imageCreationDTM = imageCreationDTM;
        this.imageUpdationDTM = imageUpdationDTM;
        this.isActive = isActive;
        this.news = news;
        this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public Instant getImageCreationDTM() {
        return imageCreationDTM;
    }

    public void setImageCreationDTM(Instant imageCreationDTM) {
        this.imageCreationDTM = imageCreationDTM;
    }

    public Date getImageUpdationDTM() {
        return imageUpdationDTM;
    }

    public void setImageUpdationDTM(Date imageUpdationDTM) {
        this.imageUpdationDTM = imageUpdationDTM;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getContentSize() {
        return contentSize;
    }

    public void setContentSize(long contentSize) {
        this.contentSize = contentSize;
    }
}

