package com.checklist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by azhang on 10/10/2017.
 */
@Entity
@Table(name = "AttributePool")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    private String description;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "template_attribute", joinColumns = @JoinColumn(name = "attribute_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "template_id", referencedColumnName = "id"))
    private Set<Template> templates;

    @OneToMany(mappedBy = "attribute",cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<AttributeValue> attributeValues;

    @OneToMany(mappedBy = "attribute",cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<NICLContent> niclContentSet;

    /*---------------getter and setter ------------------*/

    public Set<NICLContent> getNiclContentSet() {
        return niclContentSet;
    }
    @JsonIgnore
    public void setNiclContentSet(Set<NICLContent> niclContentSet) {
        this.niclContentSet = niclContentSet;
    }

    public Set<AttributeValue> getAttributeValues() {
        return attributeValues;
    }
    public void setAttributeValues(Set<AttributeValue> attributeValues) {
        this.attributeValues = attributeValues;
    }


    public Set<Template> getTemplates() {
        return templates;
    }
    @JsonIgnore
    public void setTemplates(Set<Template> templates) {
        this.templates = templates;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Attribute(){
        super();
    }
    public Attribute(String name) {
        this.name = name;
    }

    public Attribute(String name,String type) {
        this.name = name;
        this.type = type;
    }


    public Attribute(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }



}
