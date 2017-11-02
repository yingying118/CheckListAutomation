package com.checklist;

import com.checklist.model.Attribute;
import com.checklist.model.AttributeValue;
import com.checklist.model.Group;
import com.checklist.model.Template;
import com.checklist.repository.AttributeRepository;
import com.checklist.repository.AttributeValueRepository;
import com.checklist.repository.GroupRepository;
import com.checklist.repository.TemplateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Autowired
    TemplateRepository templateRepository;

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    AttributeRepository attributeRepository;
    @Autowired
    AttributeValueRepository attributeValueRepository;
    @Transactional
    @Override
    public void run(String... strings) throws Exception {

        Group g1 = new Group("PCI");
        Group g2 = new Group("GroupA");
        Attribute a1 = new Attribute("Reporting Name");
        Attribute a2 = new Attribute("Issuer Name");
        Attribute a3 = new Attribute("(GICS)Sector");
        Attribute a4 = new Attribute("Sector Focus(level-4)");
        Attribute a5 = new Attribute("Project Name of Investment");
        Attribute a6 = new Attribute("Approved Amount");
        Attribute a7 = new Attribute("CPPIB Investing Entity(Legal entity)");
        Attribute a8 = new Attribute("Investment/Asset Type");
        Attribute a9 = new Attribute("Deal Currency");
        Attribute a10 = new Attribute("Country of Exposure");
        a1.setType("dropdown");
        a1.setDescription("this filed is for reporting name");
        a2.setType("text");

        groupRepository.save(g1);
        groupRepository.save(g2);
        attributeRepository.save(a1);
        attributeRepository.save(a2);
        attributeRepository.save(a3);
        attributeRepository.save(a4);
        attributeRepository.save(a5);
        attributeRepository.save(a6);
        attributeRepository.save(a7);
        attributeRepository.save(a8);
        attributeRepository.save(a9);
        attributeRepository.save(a10);

        Template t1 = new Template("Template T1", g1);
        Template t2 = new Template("Template T2", g2);

        Set templateAs = new HashSet<Template>() {{
            add(t1);
            add(t2);
        }};
        Set templatePIC = new HashSet<Template>() {{
            add(t1);
        }};

        a1.setTemplates(templateAs);
        a2.setTemplates(templateAs);
        a3.setTemplates(templateAs);
        a4.setTemplates(templateAs);
        a5.setTemplates(templateAs);
        a6.setTemplates(templateAs);
        a7.setTemplates(templateAs);
        a8.setTemplates(templateAs);
        a9.setTemplates(templatePIC);
        a10.setTemplates(templatePIC);



        AttributeValue a1value1= new AttributeValue("report name 1", a1);
        AttributeValue a1value2= new AttributeValue("report name 2", a1);
        //AttributeValue a2value1= new AttributeValue("cppib ", a2);

        attributeValueRepository.save(a1value1);
        attributeValueRepository.save(a1value2);
        //attributeValueRepository.save(a2value1);

    }
}
