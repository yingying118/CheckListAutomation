package com.checklist.model;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by azhang on 16/11/2017.
 */
@Entity
public class SectionAttribute implements Serializable {

        @Id
        @ManyToOne
        @JoinColumn(name="sid")
        private Section section;

        @Id
        @ManyToOne
        @JoinColumn(name="tid")
        private Template template;

        @Id
        @ManyToOne
        @JoinColumn(name="aid")
        private Attribute attribute;

        @Column(name = "attribute_order")
        private int order;

        public Section getSection() {
                return section;
        }
        public void setSection(Section section) {
                this.section = section;
        }
        public Template getTemplate() {
                return template;
        }
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
