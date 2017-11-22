package com.checklist;

import com.checklist.model.*;
import com.checklist.repository.*;
import com.checklist.service.AttributeValueService.AttributeValueService;
import com.checklist.service.SectionService.SectionService;
import com.checklist.service.TemplateService.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.naming.directory.AttributeInUseException;
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
    @Autowired
    private AttributeValueService attributeValueService;
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private TemplateService templateService;

    @Transactional
    @Override
    public void run(String... strings) throws Exception {

        Group g1 = new Group("PCI");
        Group g2 = new Group("GroupA");
        groupRepository.save(g1);
        groupRepository.save(g2);

        Attribute a1 = new Attribute("Reporting Name","text");
        Attribute a2 = new Attribute("Issuer Name","text");
        /*For (GICS) Sector*/
        Attribute a3 = new Attribute("(GICS)Sector","dropdown","PI Definition:The main industry of the investment as defined by the Global Industry Classification Standard (GICS).");
        Set a3options=new HashSet<AttributeValue>(){{
            add(new AttributeValue("Materials",a3));
            add(new AttributeValue("Energy",a3));
            add(new AttributeValue("Utility",a3));
            add(new AttributeValue("Industrials",a3));
            add(new AttributeValue("Financials",a3));
            add(new AttributeValue("Health Care",a3));
            add(new AttributeValue("Telecommunication Services",a3));
        }};

        attributeValueService.saveAttributeValueSet(a3options);

        /*For Level of Approval*/
        Attribute a4 = new Attribute("Level of Approval","dropdown");
        Set  a4options= new HashSet<AttributeValue>(){{
            add( new AttributeValue("IC", a4));
            add(new AttributeValue("PICI,CIC", a4));
            add(new AttributeValue("PICI", a4));
            add(new AttributeValue("SMD", a4));

        }};
        attributeValueService.saveAttributeValueSet(a4options);


        Attribute a5 = new Attribute("Project Name of Investment","text");
        Attribute a6 = new Attribute("Approved Amount","text");
        /*For Legal Entity*/
        Attribute a7 = new Attribute("CPPIB Investing Entity(Legal entity)","dropdown");
        Set a7options= new HashSet<AttributeValue>(){{
            add(new AttributeValue("CPPIB", a7));
            add(new AttributeValue("CPPIB(Hong Kong) Limited", a7));
            add(new AttributeValue("CII Luxco", a7));
            add(new AttributeValue("CII US Holdings(1)", a7));

        }};
        attributeValueService.saveAttributeValueSet(a7options);
        /*For Investment Type*/
        Attribute a8 = new Attribute("Investment/Asset Type","dropdown");
        Set a8options= new HashSet<AttributeValue>(){{
            add(new AttributeValue("Leveraged Loans", a8));
            add(new AttributeValue("High Yield Bonds", a8));
            add(new AttributeValue("Mezzanie Debt", a8));
            add(new AttributeValue("Equity)", a8));

        }};
        attributeValueService.saveAttributeValueSet(a8options);
        /*For Investment Type*/
        Attribute a9 = new Attribute("Deal Currency","dropdown","The currency used for funding and expected for all distributions. Please advise PIBM and IFPI if these currencies are not aligned");
        Set a9options= new HashSet<AttributeValue>(){{
            add(new AttributeValue("CAD", a9));
            add(new AttributeValue("AUD", a9));
            add(new AttributeValue("CNY", a9));
            add(new AttributeValue("USD)", a9));
        }};
        attributeValueService.saveAttributeValueSet(a9options);

        /*For Country of Exposure Type*/
        Attribute a10 = new Attribute("Country of Exposure","dropdown","Currency which has the greatest impact on the value of the investment.");
        Set a10options= new HashSet<AttributeValue>(){{
            add(new AttributeValue("Austria", a10));
            add(new AttributeValue("Canada", a10));
            add(new AttributeValue("Egypt", a10));
            add(new AttributeValue("Hong Kong)", a10));
        }};
        attributeValueService.saveAttributeValueSet(a10options);

        /*for static attributes*/
        Attribute a11 = new Attribute("Prepayment Schedule", "text", true);
        Attribute a12 = new Attribute("Call Schedule", "text", true);
        Attribute a13 = new Attribute("Agent Name", "text", true);
        Attribute a14 = new Attribute("Contact Name", "text", true);
        Attribute a15 = new Attribute("Telephone", "text", true);
        Attribute a16 = new Attribute("Email", "text", true);
        Attribute a17 = new Attribute("Prepared name", "text", true);
        Attribute a18 = new Attribute("Prepared date", "text", true);
        Attribute a19 = new Attribute("Reviewed name", "text", true);
        Attribute a20 = new Attribute("Reviewed date", "text", true);
        /*--------------------*/

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
        attributeRepository.save(a11);
        attributeRepository.save(a12);
        attributeRepository.save(a13);
        attributeRepository.save(a14);
        attributeRepository.save(a15);
        attributeRepository.save(a16);
        attributeRepository.save(a17);
        attributeRepository.save(a18);
        attributeRepository.save(a19);
        attributeRepository.save(a20);

        Template t1 = new Template("Template T1", g1);
        Template t2 = new Template("Template T2", g2);

        Section s1= new Section("Overview", 1 ,t1);
        Section s2= new Section("Security Details", 2,t1);

        //templateRepository.save(t2);
        sectionRepository.save(s1);
        sectionRepository.save(s2);

        Set<Section> sectionSet=new HashSet<Section>(){{
            add(s1);
            add(s2);
        }};
        templateRepository.save(t1);
        t1.setSections(sectionSet);

        SectionAttribute sectionAttribute1 = new SectionAttribute(t1,s1,a1,1);
        SectionAttribute sectionAttribute2 = new SectionAttribute(t1,s1,a2,2);
        SectionAttribute sectionAttribute3 = new SectionAttribute(t1,s1,a3,3);
        SectionAttribute sectionAttribute4 = new SectionAttribute(t1,s2,a4,6);
        SectionAttribute sectionAttribute5 = new SectionAttribute(t1,s2,a5,5);
        s1.getSectionAttributes().add(sectionAttribute1);
        s1.getSectionAttributes().add(sectionAttribute2);
        s1.getSectionAttributes().add(sectionAttribute3);
        s2.getSectionAttributes().add(sectionAttribute4);
        s2.getSectionAttributes().add(sectionAttribute5);



/*
        Template t3 = new Template("Template T3", g1);
        Section s3=new Section("Overview", 1,t3 );
        Section s4=new Section("Security", 2,t3);

        Set<Section> sectionSetFort3=new HashSet<Section>(){{
            add(s3);
            add(s4);
        }};

        t3.setSections(sectionSetFort3);
        templateService.saveTemplate(t3);

        SectionAttribute sectionAttribute1 = new SectionAttribute(t3,s3,a1,1);
        a1.getSectionAttributes().add(sectionAttribute1);
        SectionAttribute sectionAttribute2 = new SectionAttribute(t3,s3,a2,2);
        a2.getSectionAttributes().add(sectionAttribute2);
        SectionAttribute sectionAttribute3 = new SectionAttribute(t3,s3,a3,3);
        a3.getSectionAttributes().add(sectionAttribute3);
        SectionAttribute sectionAttribute4 = new SectionAttribute(t3,s4,a4,6);
        a4.getSectionAttributes().add(sectionAttribute4);
        SectionAttribute sectionAttribute5 = new SectionAttribute(t3,s4,a5,5);
        a5.getSectionAttributes().add(sectionAttribute5);*/






/*
       Set templateAs = new HashSet<Template>() {{
            add(t1);
        }};
        Set templatePIC = new HashSet<Template>() {{
            add(t2);
        }};
        a1.setTemplates(templatePIC);
        a2.setTemplates(templatePIC);
        a3.setTemplates(templateAs);
        a4.setTemplates(templateAs);
        a5.setTemplates(templateAs);
        a6.setTemplates(templateAs);
        a7.setTemplates(templateAs);
        a8.setTemplates(templateAs);
        a9.setTemplates(templateAs);
        a10.setTemplates(templateAs);*/




        //AttributeValue a2value1= new AttributeValue("cppib ", a2);


    }
}
