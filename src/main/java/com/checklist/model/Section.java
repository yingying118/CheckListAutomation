package com.checklist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by azhang on 16/11/2017.
 */
@Entity
@Table(name = "section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int section_order;

    public int getSection_order() {
        return section_order;
    }

    public void setSection_order(int section_order) {
        this.section_order = section_order;
    }

    @ManyToOne
    @JoinColumn(name = "tid")
    private Template template;


    @OneToMany(mappedBy = "section", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<SectionAttribute> sectionAttributes;

    public Set<SectionAttribute> getSectionAttributes() {
        return sectionAttributes;
    }
    public void setSectionAttributes(Set<SectionAttribute> sectionAttributes) {
        this.sectionAttributes = sectionAttributes;
    }
    public Section(String name) {
        this.name = name;
    }

    public Section(String name, int section_order, Template template) {
        this.name = name;
        this.section_order = section_order;
        this.template = template;
        sectionAttributes=new HashSet<>();
    }

    public Section(String name, int section_order) {
        this.name = name;
        this.section_order = section_order;
        sectionAttributes=new HashSet<>();

    }

    public Section(){
        super();
    }
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
    public Template getTemplate() {
        return template;
    }
    @JsonIgnore
    public void setTemplate(Template template) {
        this.template = template;
    }
}
