package com.checklist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.naming.directory.AttributeInUseException;
import javax.persistence.*;

/**
 * Created by azhang on 24/10/2017.
 */
@Entity
@Table(name = "NICLContent")
public class NICLContent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String value;
    @ManyToOne
    @JoinColumn(name = "aid")
    private Attribute attribute;


    /**===================constructors ==================================**/
    public NICLContent(String value) {
        this.value = value;
    }

    public NICLContent(String value, Attribute attribute) {
        this.value = value;
        this.attribute = attribute;
    }

    public NICLContent(){
        super();
    }

    /**===================content_head relationship ==================================**/
    @ManyToOne
    @JoinColumn(name="nid")
    private NICLHead niclHead;

    /**===================getter and setter ==================================**/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public NICLHead getNiclHead() {
        return niclHead;
    }

    public void setNiclHead(NICLHead niclHead) {
        this.niclHead = niclHead;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
