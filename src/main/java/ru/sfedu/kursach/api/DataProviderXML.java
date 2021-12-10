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

    @Override
    public boolean viewAllSchool() {
        try {
            log.debug("Viewing users:");
            List<School> schoolBeans = loadBeanList(Constants.SCHOOL_XML_SOURCE);
            schoolBeans.forEach(log::debug);
            log.trace("Viewing School complete");
        }
        catch(Exception e){
            log.error("XML school viewing School Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean viewAllStudent() {
        try {
            log.debug("Viewing users:");
            List<Student> studentBeans = loadBeanList(Constants.STUDENT_XML_SOURCE);
            studentBeans.forEach(log::debug);
            log.trace("Viewing School complete");
        }
        catch(Exception e){
            log.error("XML student viewing School Error");
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
            List<School> schoolBeans = loadBeanList(Constants.SCHOOL_XML_SOURCE);
            log.trace("Adding record");
            schoolBeans.add(school);
            log.trace("Adding complete");
            saveFile(schoolBeans);
        }
        catch(Exception e) {
            log.error("XML school adding Error");
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
            List<Student> studentBeans = loadBeanList(Constants.STUDENT_XML_SOURCE);
            log.trace("Adding record");
            studentBeans.add(student);
            log.trace("Adding complete");
            saveFile(studentBeans);
        }
        catch(Exception e) {
            log.error("XML student adding Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteSchoolRecord(long id) {
        try {
            log.debug("Start deleting record: reading file");
            List<School> schoolBeans = loadBeanList(Constants.SCHOOL_XML_SOURCE);
            List<School> schoolBeansClone = loadBeanList(Constants.SCHOOL_XML_SOURCE);
            log.trace("Searching required record");
            Predicate<School> isDeletable = user -> user.getId() == id;
            log.trace("Removing required record");
            schoolBeans.removeIf(isDeletable);
            if (schoolBeans.equals(schoolBeansClone)) {
                throw new Exception("Deleting error, record with id "+id+" not found");
            }
            log.trace("Saving");
            saveFile(schoolBeans);

        }
        catch(Exception e) {
            log.error("XML school deleting Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteStudentRecord(long id) {
        try {
            log.debug("Start deleting record: reading file");
            List<Student> studentBeans = loadBeanList(Constants.STUDENT_XML_SOURCE);
            List<Student> studentBeansClone = loadBeanList(Constants.STUDENT_XML_SOURCE);
            log.trace("Searching required record");
            Predicate<Student> isDeletable = user -> user.getId() == id;
            log.trace("Removing required record");
            studentBeans.removeIf(isDeletable);
            if (studentBeans.equals(studentBeansClone)) {
                throw new Exception("Deleting error, record with id "+id+" not found");
            }
            log.trace("Saving");
            saveFile(studentBeans);

        }
        catch(Exception e) {
            log.error("XML student deleting Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateSchoolRecord(long id, School school) {
        try {
            log.debug("Start updating record: reading file");
            List<School> schoolBeans = loadBeanList(Constants.SCHOOL_XML_SOURCE);
            log.trace("Searching required record: searching id");
            int index = schoolBeans.indexOf(receiveSchoolRecordById(id));
            log.trace("Insert new values");
            schoolBeans.set(index, school);
            saveFile(schoolBeans);
            log.trace("Updating complete");
        }
        catch(Exception e) {
            log.error("XML school updating Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateStudentRecord(long id, Student student) {
        try {
            log.debug("Start updating record: reading file");
            List<Student> studentBeans = loadBeanList(Constants.STUDENT_XML_SOURCE);
            log.trace("Searching required record: searching id");
            int index = studentBeans.indexOf(receiveStudentRecordById(id));
            log.trace("Insert new values");
            studentBeans.set(index, student);
            saveFile(studentBeans);
            log.trace("Updating complete");
        }
        catch(Exception e) {
            log.error("XML student updating Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public School receiveSchoolRecordById(long id) {
        School schoolBean = new School();
        try {
            log.debug("Start receiving record by id");
            List<School> schoolBeans = loadBeanList(Constants.SCHOOL_XML_SOURCE);
            schoolBean = schoolBeans.stream()
                    .filter(bean -> bean.getId() == id)
                    .findAny()
                    .get();
            log.trace("Receiving record complete");
        }
        catch(Exception e) {
            log.error("XML school receiving record by id Error ");
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
        return schoolBean;
    }

    @Override
    public Student receiveStudentRecordById(long id) {
        Student studentBean = new Student();
        try {
            log.debug("Start receiving record by id");
            List<Student> studentBeans = loadBeanList(Constants.STUDENT_XML_SOURCE);
            studentBean = studentBeans.stream()
                    .filter(bean -> bean.getId() == id)
                    .findAny()
                    .get();
            log.trace("Receiving record complete");
        }
        catch(Exception e) {
            log.error("XML student receiving record by id Error ");
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
            log.error("XML creating file Error");
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
            log.error("XML writing file Error");
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
            log.debug("wrapperxml creted sucessfully");
            loadedBeans = xml.getList();
            log.debug("beans loaded sucesfully");
            file.close();
        }
        catch(Exception e){
            log.error("XML loading Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
        return loadedBeans;
    }


    private <T> String findPath(List<T> bean){
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
