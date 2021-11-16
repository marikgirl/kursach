package ru.sfedu.kursach;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import ru.sfedu.kursach.api.DataProviderCSV;
import ru.sfedu.kursach.model.beans.School;
import ru.sfedu.kursach.utils.ConfigurationUtil;

import java.io.IOException;

public class kursachClient {

    private static Logger log = LogManager.getLogger(kursachClient.class);

    public kursachClient() {
        log.debug("kursachClient[0]: starting application.........");
    }

    public void logBasicSystemInfo() throws IOException {
        School schoolBean = new School();

        schoolBean.setAddress("unknown address");
        schoolBean.setId();
        schoolBean.setNumber(58);
        DataProviderCSV dataProviderCSV = new DataProviderCSV();
        dataProviderCSV.addSchoolRecord(schoolBean);
        dataProviderCSV.viewAllSchool();
        schoolBean.setNumber(128);
        dataProviderCSV.receiveSchoolRecordById(schoolBean.getId());
        dataProviderCSV.updateSchoolRecord(schoolBean.getId(), schoolBean);
        dataProviderCSV.viewAllSchool();
        //String pathh = ConfigurationUtil.getConfigurationEntry(Constants.DB_CONFIG_USER);

//        log.info(pathh);
//        log.info("Launching the application...");
//        log.info(
//                "Operating System: " + System.getProperty("os.name") + " "
//                        + System.getProperty("os.version")
//        );
//        log.info("JRE: " + System.getProperty("java.version"));
//        log.info("Java Launched From: " + System.getProperty("java.home"));
//        log.info("Class Path: " + System.getProperty("java.class.path"));
//        log.info("Library Path: " + System.getProperty("java.library.path"));
//        log.info("User Home Directory: " + System.getProperty("user.home"));
//        log.info("User Working Directory: " + System.getProperty("user.dir"));
//        log.info("Test INFO logging.");
    }
}
