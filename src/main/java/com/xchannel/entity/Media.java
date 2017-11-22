package com.xchannel.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;
import java.util.Date;

/**
 *
 * Created by Erdem Akyıldız on 21.11.2017.
 *
 */
@Document(collection = "x_media")
public class Media {

    @Id
    private String id;

    private String user;
    private String adress;
    private String host;
    private int port;

    private String mediaTitle;
    private String fileObjectId;
    private Date createDate;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }

    public void setMediaTitle(String mediaTitle) {
        this.mediaTitle = mediaTitle;
    }

    public String getFileObjectId() {
        return fileObjectId;
    }

    public void setFileObjectId(String fileObjectId) {
        this.fileObjectId = fileObjectId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
