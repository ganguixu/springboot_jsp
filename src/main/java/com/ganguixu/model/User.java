package com.ganguixu.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 2367788360458274833L;

    private String name;
    private String pass;
}
