package com.checklist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by azhang on 06/10/2017.
 */
@Entity
@Table(name = "group_table")

public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "groupObject", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Template> templates;

    public Set<Template> getTemplates() {
        return templates;
    }
    @JsonIgnore
    public void setTemplates(Set<Template> templates) {
        this.templates = templates;
    }


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group(){
        super();
    }
    public Group(String name) {
        this.name = name;
    }

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        String result = String.format(
                "Group[id=%d, name='%s']%n",
                id, name);
        if (templates != null) {
            for(Template template : templates) {
                result += String.format(
                        "Template[id=%d, name='%s']%n",
                        template.getId(), template.getName());
            }
        }

        return result;
    }

}
