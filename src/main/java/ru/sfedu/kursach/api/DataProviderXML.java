package ru.sfedu.kursach.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.kursach.Constants;
import ru.sfedu.kursach.Main;
import ru.sfedu.kursach.model.beans.School;
import ru.sfedu.kursach.model.beans.Student;
import ru.sfedu.kursach.model.beans.WrapperXML;
import ru.sfedu.kursach.utils.ConfigurationUtil;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.function.Predicate;

public class DataProviderXML implements IDataProvider{
    private static final Logger log = LogManager.getLogger(Main.class);

    List<School> schoolBeans = null;
    List<Student> studentBeans = null;
    School schoolBean = new School();
    Student studentBean = new Student();

    @Override
    public boolean viewAllSchool() {
        try {
            log.debug("Viewing users:");
            schoolBeans = loadBeanList(Constants.SCHOOL_XML_SOURCE);
            schoolBeans.forEach(log::debug);
            log.trace("Viewing School complete");
        }
        catch(Exception e){
            log.error("Viewing School Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean viewAllStudent() {
        try {
            log.debug("Viewing users:");
            studentBeans = loadBeanList(Constants.STUDENT_XML_SOURCE);
            studentBeans.forEach(log::debug);
            log.trace("Viewing School complete");
        }
        catch(Exception e){
            log.error("Viewing School Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean addSchoolRecord(School school) {
        try {
            if (school == null)
                throw new Exception("Adding record error, record equals null");
            log.debug("Start adding record: reading file");
            schoolBeans = loadBeanList(Constants.SCHOOL_XML_SOURCE);
            log.trace("Adding record");
            schoolBeans.add(school);
            log.trace("Adding complete");
            saveFile(schoolBeans);
        }
        catch(Exception e) {
            log.error("Adding Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean addStudentRecord(Student student) {
        try {
            if (student == null)
                throw new Exception("Adding record error, record equals null");
            log.debug("Start adding record: reading file");
            studentBeans = loadBeanList(Constants.STUDENT_XML_SOURCE);
            log.trace("Adding record");
            studentBeans.add(student);
            log.trace("Adding complete");
            saveFile(studentBeans);
        }
        catch(Exception e) {
            log.error("Adding Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteSchoolRecord(long id) {
        try {
            log.debug("Start deleting record: reading file");
            schoolBeans = loadBeanList(Constants.SCHOOL_XML_SOURCE);
            List<School> userBeansClone = loadBeanList(Constants.SCHOOL_XML_SOURCE);
            log.trace("Searching required record");
            Predicate<School> isDeletable = user -> user.getId() == id;
            log.trace("Removing required record");
            schoolBeans.removeIf(isDeletable);
            if (schoolBeans.equals(userBeansClone)) {
                throw new Exception("Deleting error, record with id "+id+" not found");
            }
            log.trace("Saving");
            saveFile(schoolBeans);

        }
        catch(Exception e) {
            log.error("Deleting Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteStudentRecord(long id) {
        try {
            log.debug("Start deleting record: reading file");
            studentBeans = loadBeanList(Constants.STUDENT_XML_SOURCE);
            List<Student> userBeansClone = loadBeanList(Constants.STUDENT_XML_SOURCE);
            log.trace("Searching required record");
            Predicate<Student> isDeletable = user -> user.getId() == id;
            log.trace("Removing required record");
            studentBeans.removeIf(isDeletable);
            if (studentBeans.equals(userBeansClone)) {
                throw new Exception("Deleting error, record with id "+id+" not found");
            }
            log.trace("Saving");
            saveFile(studentBeans);

        }
        catch(Exception e) {
            log.error("Deleting Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateSchoolRecord(long id, School school) {
        try {
            log.debug("Start updating record: reading file");
            schoolBeans = loadBeanList(Constants.SCHOOL_XML_SOURCE);
            log.trace("Searching required record: searching id");
            int index = schoolBeans.indexOf(receiveSchoolRecordById(id));
            log.trace("Insert new values");
            schoolBeans.set(index, school);
            saveFile(schoolBeans);
            log.trace("Updating complete");
        }
        catch(Exception e) {
            log.error("Updating Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateStudentRecord(long id, Student student) {
        try {
            log.debug("Start updating record: reading file");
            studentBeans = loadBeanList(Constants.STUDENT_XML_SOURCE);
            log.trace("Searching required record: searching id");
            int index = studentBeans.indexOf(receiveStudentRecordById(id));
            log.trace("Insert new values");
            studentBeans.set(index, student);
            saveFile(studentBeans);
            log.trace("Updating complete");
        }
        catch(Exception e) {
            log.error("Updating Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public School receiveSchoolRecordById(long id) {
        try {
            log.debug("Start receiving record by id");
            schoolBeans = loadBeanList(Constants.SCHOOL_XML_SOURCE);
            schoolBean = schoolBeans.stream()
                    .filter(bean -> bean.getId() == id)
                    .findAny()
                    .get();
            log.trace("Receiving record complete");
        }
        catch(Exception e) {
            log.error("Receiving record by id Error ");
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
        return schoolBean;
    }

    @Override
    public Student receiveStudentRecordById(long id) {
        try {
            log.debug("Start receiving record by id");
            studentBeans = loadBeanList(Constants.STUDENT_XML_SOURCE);
            studentBean = studentBeans.stream()
                    .filter(bean -> bean.getId() == id)
                    .findAny()
                    .get();
            log.trace("Receiving record complete");
        }
        catch(Exception e) {
            log.error("Receiving record by id Error ");
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
        return studentBean;
    }

    public boolean createFile(String path){
        try{
            File file = new File(ConfigurationUtil.getConfigurationEntry(path));
            log.info("try to create file:");
            file.createNewFile();
            log.info("file created");

        }
        catch(Exception e){
            log.error("Creating file Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    private <T> boolean saveFile(List <T> beans){
        try {
            log.debug("start writing");
            Serializer serializer = new Persister();
            File result = new File(ConfigurationUtil.
                    getConfigurationEntry(findPath(beans)));
            Writer writer = new FileWriter(result);
            WrapperXML<T> xml = new WrapperXML<>(beans);
            serializer.write(xml, writer);
        }
        catch (Exception e){
            log.error("Writing file Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }


    private <T> List<T> loadBeanList(String path) {
        List<T> loadedBeans = null;
        try {
            log.debug("Start reading file");
            Serializer serializer = new Persister();
            FileReader file = new FileReader(ConfigurationUtil.getConfigurationEntry(path));
            WrapperXML<T> xml;
            xml = serializer.read(WrapperXML.class, file);
            loadedBeans = xml.getList();
            file.close();
        }
        catch(Exception e){
            log.error("Loading Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
        return loadedBeans;
    }


    private  <T> String findPath(List<T> bean){
        log.debug("Class of elements inside list: "+bean.get(0).getClass().getSimpleName());
        switch(bean.get(0).getClass().getSimpleName()) {
            case "School":
                return Constants.SCHOOL_XML_SOURCE;
            case "Student":
                return Constants.STUDENT_XML_SOURCE;
            default: return Constants.UNKNOWN_SOURCE_XML;

        }
    }
}
