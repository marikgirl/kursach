package ru.sfedu.kursach.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ru.sfedu.kursach.Main;
import ru.sfedu.kursach.model.beans.School;

import static org.junit.jupiter.api.Assertions.*;

public class DataProviderCSVTest extends Main {
    private static Logger log = LogManager.getLogger(DataProviderCSVTest.class);

    DataProviderCSV dataProviderCSV = new DataProviderCSV();
    School schoolBean = new School();

    @Test
    public void viewAllSchool() {
        log.info("viewAllSchool");
        assertTrue(dataProviderCSV.viewAllSchool());
    }


    @Test
    public void receiveSchoolRecordByIdPositive() {
        log.info("receiveSchoolRecordByIdPositive");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        dataProviderCSV.addSchoolRecord(schoolBean);
        assertEquals(schoolBean, dataProviderCSV.receiveSchoolRecordById(schoolBean.getId()));
        dataProviderCSV.deleteSchoolRecord(schoolBean.getId());
    }

    @Test
    public void receiveSchoolRecordByIdNegative() {
        log.info("receiveSchoolRecordByIdNegative");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");


        dataProviderCSV.addSchoolRecord(schoolBean);
        assertNotEquals(schoolBean, dataProviderCSV.receiveSchoolRecordById(9999999999L));
        dataProviderCSV.deleteSchoolRecord(schoolBean.getId());
    }

    @Test
    public void deleteSchoolRecordPositive() {
        log.info("deleteSchoolRecordPositive");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        dataProviderCSV.addSchoolRecord(schoolBean);
        assertTrue(dataProviderCSV.deleteSchoolRecord(schoolBean.getId()));
    }

    @Test
    public void deleteSchoolRecordNegative() {
        log.info("deleteSchoolRecordNegative");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        dataProviderCSV.addSchoolRecord(schoolBean);
        dataProviderCSV.deleteSchoolRecord(schoolBean.getId());
        assertFalse(dataProviderCSV.deleteSchoolRecord(schoolBean.getId()));
    }

    @Test
    public void updateSchoolRecordPositive() {
        log.info("updateSchoolRecordPositive");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        dataProviderCSV.addSchoolRecord(schoolBean);
        schoolBean.setAddress("Другой адрес 99");
        assertTrue(dataProviderCSV.updateSchoolRecord(schoolBean.getId(),schoolBean));
        dataProviderCSV.deleteSchoolRecord(schoolBean.getId());
    }

    @Test
    public void updateSchoolRecordNegative() {
        log.info("updateSchoolRecordNegative");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        dataProviderCSV.addSchoolRecord(schoolBean);
        schoolBean.setAddress("Другой адрес 99");
        assertTrue(dataProviderCSV.updateSchoolRecord(5000000000L,schoolBean));
        dataProviderCSV.deleteSchoolRecord(schoolBean.getId());
    }

    @Test
    public void addSchoolRecordPositive() {
        log.info("addSchoolRecordPositive");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        assertTrue(dataProviderCSV.addSchoolRecord(schoolBean));
        dataProviderCSV.deleteSchoolRecord(schoolBean.getId());
    }

    @Test
    public void addSchoolRecordNegative() {
        log.info("addSchoolRecordNegative");
        School emptySchool = null;
        assertFalse(dataProviderCSV.addSchoolRecord(emptySchool));
    }
}
