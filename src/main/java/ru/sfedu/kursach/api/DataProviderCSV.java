package ru.sfedu.kursach.api;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.kursach.Constants;
import ru.sfedu.kursach.Main;
import ru.sfedu.kursach.model.beans.School;
import ru.sfedu.kursach.model.beans.Student;
import ru.sfedu.kursach.utils.ConfigurationUtil;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.function.Predicate;

public class DataProviderCSV implements IDataProvider  {

    private static final Logger log = LogManager.getLogger(Main.class);

    @Override
    public boolean viewAllSchool() {
        try {
            log.info("Start viewing schools");
            School schoolBean = new School();
            List<School> schoolBeans = loadBeanList(Constants.SCHOOL_CSV_SOURCE, schoolBean);
            schoolBeans.stream().forEach(bean -> log.info(bean));
            log.info("Viewing complete");
        }
        catch(Exception e){
            log.info("Viewing schools Error");
            log.info(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean viewAllStudent() {
        try {
            log.info("Start viewing students");
            Student studentBean = new Student();
            List<Student> studentBeans = loadBeanList(Constants.STUDENT_CSV_SOURCE, studentBean);
            studentBeans.stream().forEach(bean -> log.info(bean));
            log.info("Viewing complete");
        }
        catch(Exception e){
            log.info("Viewing students Error");
            log.info(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public School receiveSchoolRecordById(long id) {
        School schoolBean = new School();
        try {
            log.info("Start receiving record by id");
            schoolBean = loadBeanList(Constants.SCHOOL_CSV_SOURCE, schoolBean).stream()
                    .filter(beans-> ((School) beans).getId() == id)
                    .findAny().get();
            log.info("Receiving record complete");
        }
        catch(Exception e) {
            log.info("Receiving record by id Error ");
            log.info(e.getClass().getName() + ": " + e.getMessage());
        }
        return schoolBean;
    }

    @Override
    public Student receiveStudentRecordById(long id) {
        Student studentBean = new Student();
        try {
            log.info("Start receiving record by id");
            studentBean = loadBeanList(Constants.STUDENT_CSV_SOURCE, studentBean).stream()
                    .filter(beans-> ((Student) beans).getId() == id)
                    .findAny().get();
            log.info("Receiving record complete");
        }
        catch(Exception e) {
            log.info("Receiving record by id Error ");
            log.info(e.getClass().getName() + ": " + e.getMessage());
        }
        return studentBean;
    }


    @Override
    public boolean deleteSchoolRecord(long id){
        try {
            log.info("Start deleting record: reading file");
            School schoolBean = new School();
            List<School> schoolBeans = loadBeanList(Constants.SCHOOL_CSV_SOURCE, schoolBean);
            log.info("Searching required record");
            Predicate<School> isDeletable = emp -> emp.getId() == id;
            log.info("Removing required record");
            schoolBeans.removeIf(isDeletable);
            log.info("Saving");
            saveFile(schoolBeans);
        }
        catch(Exception e) {
            log.info("Deleting Error");
            log.info(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteStudentRecord(long id) {
        try {
            log.info("Start deleting record: reading file");
            Student studentBean = new Student();
            List<Student> studentBeans = loadBeanList(Constants.STUDENT_CSV_SOURCE, studentBean);
            log.info("Searching required record");
            Predicate<Student> isDeletable = order -> order.getId() == id;
            log.info("Removing required record");
            studentBeans.removeIf(isDeletable);
            log.info("Saving");
            saveFile(studentBeans);
        }
        catch(Exception e) {
            log.info("Deleting Error");
            log.info(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }


    @Override
    public boolean updateSchoolRecord(long id, School school) {
        try {
            log.info("Start updating record: reading file");
            School schoolBean = new School();
            List<School> schoolBeans = loadBeanList(Constants.SCHOOL_CSV_SOURCE, schoolBean);
            log.info("Searching required record: searching id");
            int index = schoolBeans.indexOf(receiveSchoolRecordById(id));
            log.info("Insert new values");
            schoolBeans.set(index, school);
            saveFile(schoolBeans);
            log.info("Updating complete");
        }
        catch(Exception e) {
            log.info("Updating Error");
            log.info(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateStudentRecord(long id, Student student) {
        try {

            log.info("Start updating record: reading file");
            Student studentBean = new Student();
            List<Student> studentBeans = loadBeanList(Constants.STUDENT_CSV_SOURCE, studentBean);
            log.info("Searching required record: searching id");
            int index = studentBeans.indexOf(receiveStudentRecordById(id));
            log.info("Insert new values");
            studentBeans.set(index, student);
            saveFile(studentBeans);
            log.info("Updating complete");
        }
        catch(Exception e) {
            log.info("Updating Error");
            log.info(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean addSchoolRecord(School school) {
        try {
            log.info("Start adding record: reading file");
            School schoolBean = new School();
            List<School> schoolBeans = loadBeanList(Constants.SCHOOL_CSV_SOURCE, schoolBean);
            log.info("Adding record");
            schoolBeans.add(school);
            log.info("Adding complete");
            saveFile(schoolBeans);
        }
        catch(Exception e) {
            log.info(" Adding Error");
            log.info(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean addStudentRecord(Student student) {
        try {
            log.info("Start adding record: reading file");
            Student studentBean = new Student();
            List<Student> studentBeans = loadBeanList(Constants.STUDENT_CSV_SOURCE, studentBean);
            log.info(studentBeans);
            log.info("Adding record");
            studentBeans.add(student);
            log.info(studentBeans);
            log.info("Adding complete");
            saveFile(studentBeans);
        }
        catch(Exception e) {
            log.info(" Adding Error");
            log.info(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        return true;
    }

    private <T> boolean saveFile(List<T> beans)  {
        try {
            log.info("Start saving file: initialize file writer");
            log.info(findPath(beans));
            FileWriter sw = new FileWriter(ConfigurationUtil
                    .getConfigurationEntry(findPath(beans)));
            log.info("Try to initialize CSVWriter");
            CSVWriter writer = new CSVWriter(sw);
            log.info("Try to initialize BeanToCsv object");
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer).build();
            log.info("Try to write into file");
            beanToCsv.write(beans);
            log.info("Writing complete");
            writer.close();
        }
        catch(Exception e) {
            log.info("Saving file Error ");
            log.info(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        log.info("File Saved");
        return true;
    }

    private <T> List<T> loadBeanList(String path, T bean) {
        List<T> loadedBeans = null;
        try {
            log.info("Reading file");
            loadedBeans = new CsvToBeanBuilder(new FileReader(ConfigurationUtil
                    .getConfigurationEntry(path)))
                    .withType(bean.getClass())
                    .build()
                    .parse();
            log.info("Beans loaded successful");
        }
        catch(Exception e){
            log.info("Loading Error");
            log.info(e.getClass().getName() + ": " + e.getMessage());
        }
        return loadedBeans;
    }

    private  <T> String findPath(List<T> bean){
        log.info("Class of elements inside list: "+bean.get(0).getClass().getSimpleName());
        switch(bean.get(0).getClass().getSimpleName()) {
            case "School":
                return Constants.SCHOOL_CSV_SOURCE;
            case "Student":
                return Constants.STUDENT_CSV_SOURCE;

            default: return Constants.UNKNOWN_SOURCE_CSV;
        }
    }

}