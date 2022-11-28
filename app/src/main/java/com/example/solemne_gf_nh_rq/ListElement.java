package com.example.solemne_gf_nh_rq;

import java.io.Serializable;

public class ListElement implements Serializable{
    public Integer id;

    public ListElement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}


