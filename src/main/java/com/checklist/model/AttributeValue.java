package com.checklist.model;

import javax.persistence.*;

/**
 * Created by azhang on 30/10/2017.
 */
@Entity
@Table(name = "AttributeValuePool")
public class AttributeValue {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String Value;
    @ManyToOne
    @JoinColumn(name = "aid")
    private Attribute attribute;


    public AttributeValue(String value, Attribute attribute) {
        Value = value;
        this.attribute = attribute;
    }
    public AttributeValue(){
        super();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }


}
