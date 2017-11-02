package com.checklist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by azhang on 10/10/2017.
 */
@Entity
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


    /*-----------------------group_template many-to-one relationship -----------------------*/
    @ManyToOne
    @JoinColumn(name = "template_group_id")
    private Group groupObject;
    public Group getGroupObject() {
        return groupObject;
    }
    public void setGroupObject(Group groupObject) {
        this.groupObject = groupObject;
    }


    /*-----------------------template_attribute many-many relationship -------------------------*/

    @ManyToMany(mappedBy="templates", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
     private Set<Attribute> attributes;

    public Set<Attribute> getAttributes() {
        return attributes;
    }
    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }


    /*-----------------------NICLHead_template one-many relationship ---------------------------*/

    @OneToMany(mappedBy = "template", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<NICLHead> NICLHeads;
    public Set<NICLHead> getNICLHeads() {
        return NICLHeads;
    }
    @JsonIgnore
    public void setNICLHeads(Set<NICLHead> NICLHeads) {
        this.NICLHeads = NICLHeads;
    }

    /*--------------Constructor -----------------------------------------------------------------*/
    public Template(){
        super();
    }
    public Template(String name, Group group) {
        this.name = name;
        this.groupObject = group;
    }

    public Template(String name, Group groupObject, Set<Attribute> attributes) {
        this.name = name;
        this.groupObject = groupObject;
        this.attributes = attributes;
    }

    public Template(String name) {
        this.name = name;
    }
    /*--------------getters & setters -----------------------------------------------------------------*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


