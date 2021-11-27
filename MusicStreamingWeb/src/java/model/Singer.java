/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author nvlon
 */
public class Singer {
    private int singerID;
    private String name, info, img;

    public Singer() {
    }

    public Singer(int singerID, String name, String info, String img) {
        this.singerID = singerID;
        this.name = name;
        this.info = info;
        this.img = img;
    }

    public int getSingerID() {
        return singerID;
    }

    public void setSingerID(int singerID) {
        this.singerID = singerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Singer{" + "singerID=" + singerID + ", name=" + name + ", info=" + info + ", img=" + img + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Singer other = (Singer) obj;
        if (this.singerID != other.singerID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.info, other.info)) {
            return false;
        }
        if (!Objects.equals(this.img, other.img)) {
            return false;
        }
        return true;
    }
    
}
