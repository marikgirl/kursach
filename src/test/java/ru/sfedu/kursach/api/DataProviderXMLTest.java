package ru.sfedu.kursach.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.kursach.model.beans.School;

import static org.junit.jupiter.api.Assertions.*;

public class DataProviderXMLTest {
    private static Logger log = LogManager.getLogger(DataProviderCSVTest.class);

    DataProviderXML dataProviderXML = new DataProviderXML();
    School schoolBean = new School();

    @Test
    public void viewAllSchool() {
        log.info("viewAllSchool");
        assertTrue(dataProviderXML.viewAllSchool());
    }


    @Test
    public void receiveSchoolRecordByIdSuccessful() {
        log.info("receiveSchoolRecordByIdSuccesful");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        dataProviderXML.addSchoolRecord(schoolBean);
        assertEquals(schoolBean, dataProviderXML.receiveSchoolRecordById(schoolBean.getId()));
        dataProviderXML.deleteSchoolRecord(schoolBean.getId());
    }

    @Test
    public void receiveSchoolRecordByIdFail() {
        log.info("receiveSchoolRecordByIdFail");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");


        dataProviderXML.addSchoolRecord(schoolBean);
        assertNotEquals(schoolBean, dataProviderXML.receiveSchoolRecordById(9999999999L));
        dataProviderXML.deleteSchoolRecord(schoolBean.getId());
    }

    @Test
    public void deleteSchoolRecordSuccessful() {
        log.info("deleteSchoolRecordSuccesful");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        dataProviderXML.addSchoolRecord(schoolBean);
        assertTrue(dataProviderXML.deleteSchoolRecord(schoolBean.getId()));
    }

    @Test
    public void deleteSchoolRecordFail() {
        log.info("deleteSchoolRecordFail");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        dataProviderXML.addSchoolRecord(schoolBean);
        dataProviderXML.deleteSchoolRecord(schoolBean.getId());
        assertFalse(dataProviderXML.deleteSchoolRecord(schoolBean.getId()));
    }

    @Test
    public void updateSchoolRecordSuccessful() {
        log.info("updateSchoolRecordSuccesful");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        dataProviderXML.addSchoolRecord(schoolBean);
        schoolBean.setAddress("Другой адрес 99");
        assertTrue(dataProviderXML.updateSchoolRecord(schoolBean.getId(),schoolBean));
        dataProviderXML.deleteSchoolRecord(schoolBean.getId());
    }

    @Test
    public void updateSchoolRecordFail() {
        log.info("updateSchoolRecordFail");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        dataProviderXML.addSchoolRecord(schoolBean);
        schoolBean.setAddress("Другой адрес 99");
        assertFalse(dataProviderXML.updateSchoolRecord(500000000000000L,schoolBean));
        dataProviderXML.deleteSchoolRecord(schoolBean.getId());
    }

    @Test
    public void addSchoolRecordSuccessful() {
        log.info("addSchoolRecordSuccesful");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        assertTrue(dataProviderXML.addSchoolRecord(schoolBean));
        dataProviderXML.deleteSchoolRecord(schoolBean.getId());
    }

    @Test
    public void addSchoolRecordFail() {
        log.info("addSchoolRecordNegative");
        assertFalse(dataProviderXML.addSchoolRecord(null));
    }
}
