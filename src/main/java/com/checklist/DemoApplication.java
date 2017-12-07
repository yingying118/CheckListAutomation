package com.checklist;

import com.checklist.model.*;
import com.checklist.repository.*;
import com.checklist.service.AttributeValueService.AttributeValueService;
import com.checklist.service.SectionAttributeService.SectionAttributeService;
import com.checklist.service.SectionService.SectionService;
import com.checklist.service.TemplateService.TemplateService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.naming.directory.AttributeInUseException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    private static final String FILE_NAME = "C:/Users/azhang/Documents/CheckListAutomation/src/main/java/com/checklist/tmp/nicl_input.xlsx";

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
    @Autowired
    private SectionAttributeService sectionAttributeService;
    @Transactional
    @Override
    public void run(String... strings) throws Exception {
        Group g1 = new Group("PCI");
        groupRepository.save(g1);
        /*for static attributes reserved*/
        Attribute a1 = new Attribute("Prepayment Schedule", "Text", true);
        Attribute a2 = new Attribute("Call Schedule", "Text", true);
        Attribute a3 = new Attribute("Agent Name", "Text", true);
        Attribute a4 = new Attribute("Contact Name", "Text", true);
        Attribute a5 = new Attribute("Telephone", "Text", true);
        Attribute a6 = new Attribute("Email", "text", true);
        Attribute a7 = new Attribute("Prepared name", "Text", true);
        Attribute a8 = new Attribute("Prepared date", "Text", true);
        Attribute a9 = new Attribute("Reviewed name", "Text", true);
        Attribute a10 = new Attribute("Reviewed date", "Text", true);

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
        /*--------------------*/
        Attribute temp;
        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            int count=0;
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                System.out.println("----------------------------" + count++);
                Cell name= cellIterator.next();
                System.out.println("Name:" + name.getStringCellValue());
                Cell type= cellIterator.next();
                System.out.println("Type:" + type.getStringCellValue());
                Cell description = cellIterator.next();
                if(description.getStringCellValue().isEmpty()){
                    temp = new Attribute(name.getStringCellValue(),type.getStringCellValue());
                }
                else{
                    temp = new Attribute(name.getStringCellValue(),type.getStringCellValue(),description.getStringCellValue());
                }
                /* if it is dropdown type */
                Set tempOptions;
                if(type.getStringCellValue().equalsIgnoreCase("dropdown")){
                    System.out.println("dropdown values:");
                    tempOptions = new HashSet<AttributeValue>();
                    while (cellIterator.hasNext()) {
                        Cell currentCell = cellIterator.next();
                        if(currentCell.getStringCellValue().isEmpty()){
                            break;
                        }
                        System.out.print(currentCell.getStringCellValue()+";");
                        tempOptions.add(new AttributeValue(currentCell.getStringCellValue(), temp));
                    }
                    temp.setAttributeValues(tempOptions);

                }
                attributeRepository.save(temp);
                System.out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


/*


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

        SectionAttribute sectionAttribute1 = new SectionAttribute(t1,s1,a11,1);
        SectionAttribute sectionAttribute2 = new SectionAttribute(t1,s1,a12,2);
        SectionAttribute sectionAttribute3 = new SectionAttribute(t1,s1,a13,3);
        SectionAttribute sectionAttribute4 = new SectionAttribute(t1,s2,a14,6);
        SectionAttribute sectionAttribute5 = new SectionAttribute(t1,s2,a15,5);
        s1.getSectionAttributes().add(sectionAttribute1);
        s1.getSectionAttributes().add(sectionAttribute2);
        s1.getSectionAttributes().add(sectionAttribute3);
        s2.getSectionAttributes().add(sectionAttribute4);
        s2.getSectionAttributes().add(sectionAttribute5);

        Section finance_contact= new Section("Finance Contact", 101,t1,true);
        sectionRepository.save(finance_contact);
        t1.getSections().add(finance_contact);

        List<Attribute> financeContactAttrs = new ArrayList<Attribute>(){{
                add(a3);
                add(a4);
                add(a5);
                add(a6);
        }};
        sectionAttributeService.saveStaticSectionAttribute(finance_contact,t1,financeContactAttrs);
        /*static schedule section */

/*
        Section schedule= new Section("Schedule", 100,t1,true);
        sectionRepository.save(schedule);
        t1.getSections().add(schedule);
        List<Attribute> scheduleAttrs = new ArrayList<Attribute>(){{
                add(a1);
                add(a2);
        }};
        sectionAttributeService.saveStaticSectionAttribute(schedule,t1,scheduleAttrs);


        Section audit= new Section("Audit", 102,t1,true);
        sectionRepository.save(audit);
        t1.getSections().add(audit);

        List<Attribute> auditAttr = new ArrayList<Attribute>(){{

                add(a7);
                add(a9);
                add(a8);
                add(a10);

        }};

        sectionAttributeService.saveStaticSectionAttribute(audit, t1, auditAttr);*/


    }
}
