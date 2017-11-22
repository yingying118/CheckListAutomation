package com.checklist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by azhang on 16/11/2017.
 */
@Entity
public class SectionAttribute implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        public Long getId() {
                return id;
        }
        public void setId(Long id) {
                this.id = id;
        }

        @ManyToOne
        @JoinColumn(name="sid")
        private Section section;

        @ManyToOne
        @JoinColumn(name="tid")
        private Template template;

        @ManyToOne
        @JoinColumn(name="aid")
        private Attribute attribute;

        @Column(name = "attribute_order")
        private int order;

        public SectionAttribute(Template template, Section section, Attribute attribute, int order) {
                this.section = section;
                this.template = template;
                this.attribute = attribute;
                this.order = order;
        }
        public SectionAttribute(){
                super();
        }
        /*
        @OneToMany(mappedBy = "sectionAttribute",cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
        private Set<NICLContent> niclContentSet;

        public Set<NICLContent> getNiclContentSet() {
                return niclContentSet;
        }
        @JsonIgnore
        public void setNiclContentSet(Set<NICLContent> niclContentSet) {
                this.niclContentSet = niclContentSet;
        }*/

        public Section getSection() {
                return section;
        }
        @JsonIgnore
        public void setSection(Section section) {
                this.section = section;
        }

        public Template getTemplate() {
                return template;
        }
        @JsonIgnore
        public void setTemplate(Template template) {
                this.template = template;
        }
        public Attribute getAttribute() {
                return attribute;
        }

        public void setAttribute(Attribute attribute) {
                this.attribute = attribute;
        }
        public int getOrder() {
                return order;
        }
        public void setOrder(int order) {
                this.order = order;
        }
}
