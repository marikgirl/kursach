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
    public void receiveSchoolRecordByIdSuccessful() {
        log.info("receiveSchoolRecordByIdSuccesful");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        dataProviderCSV.addSchoolRecord(schoolBean);
        assertEquals(schoolBean, dataProviderCSV.receiveSchoolRecordById(schoolBean.getId()));
        dataProviderCSV.deleteSchoolRecord(schoolBean.getId());
    }

    @Test
    public void receiveSchoolRecordByIdFail() {
        log.info("receiveSchoolRecordByIdFail");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");


        dataProviderCSV.addSchoolRecord(schoolBean);
        assertNotEquals(schoolBean, dataProviderCSV.receiveSchoolRecordById(9999999999L));
        dataProviderCSV.deleteSchoolRecord(schoolBean.getId());
    }

    @Test
    public void deleteSchoolRecordSuccessful() {
        log.info("deleteSchoolRecordSuccesful");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        dataProviderCSV.addSchoolRecord(schoolBean);
        assertTrue(dataProviderCSV.deleteSchoolRecord(schoolBean.getId()));
    }

    @Test
    public void deleteSchoolRecordFail() {
        log.info("deleteSchoolRecordFail");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        dataProviderCSV.addSchoolRecord(schoolBean);
        dataProviderCSV.deleteSchoolRecord(schoolBean.getId());
        assertFalse(dataProviderCSV.deleteSchoolRecord(schoolBean.getId()));
    }

    @Test
    public void updateSchoolRecordSuccessful() {
        log.info("updateSchoolRecordSuccesful");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        dataProviderCSV.addSchoolRecord(schoolBean);
        schoolBean.setAddress("Другой адрес 99");
        assertTrue(dataProviderCSV.updateSchoolRecord(schoolBean.getId(),schoolBean));
        dataProviderCSV.deleteSchoolRecord(schoolBean.getId());
    }

    @Test
    public void updateSchoolRecordFail() {
        log.info("updateSchoolRecordFail");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        dataProviderCSV.addSchoolRecord(schoolBean);
        schoolBean.setAddress("Другой адрес 99");
        assertFalse(dataProviderCSV.updateSchoolRecord(500000000000000L,schoolBean));
        dataProviderCSV.deleteSchoolRecord(schoolBean.getId());
    }

    @Test
    public void addSchoolRecordSuccessful() {
        log.info("addSchoolRecordSuccesful");

        schoolBean.setId();
        schoolBean.setNumber(28);
        schoolBean.setAddress("Коммунистический 28");

        assertTrue(dataProviderCSV.addSchoolRecord(schoolBean));
        dataProviderCSV.deleteSchoolRecord(schoolBean.getId());
    }

    @Test
    public void addSchoolRecordFail() {
        log.info("addSchoolRecordNegative");
        assertFalse(dataProviderCSV.addSchoolRecord(null));
    }
}
