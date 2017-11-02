package com.checklist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by azhang on 16/10/2017.
 */
@Entity
@Table(name = "NICLHead")
public class NICLHead {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String owner;
    private String name;
    private String status;

    @ManyToOne
    @JoinColumn(name = "tid")
    private Template template;
/*---------------------constructors -----------------------------------------------*/

    public NICLHead(String owner) {
        this.owner = owner;
    }
    public NICLHead(){
        super();
    }



    /*---------------------head_content relationship -----------------------------------------------*/
    @OneToMany(mappedBy="niclHead", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<NICLContent> NICLContents;

    public Set<NICLContent> getNICLContents() {
        return NICLContents;
    }
    @JsonIgnore
    public void setNICLContents(Set<NICLContent> NICLContents) {
        this.NICLContents = NICLContents;
    }

    /*--------------getters & setters -------------------------------------------------*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public NICLHead(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    public NICLHead(String owner, String name, String status) {
        this.owner = owner;
        this.name = name;
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Template getTemplate() {
        return template;
    }
    public void setTemplate(Template template) {
        this.template = template;
    }
}
