package ru.sfedu.kursach.api;

import ru.sfedu.kursach.model.beans.School;
import ru.sfedu.kursach.model.beans.Student;

public interface IDataProvider {
    /**
     * Вывести всех работников из файла
     * @return boolean flg, если операция прошла успешно
     */
    boolean viewAllSchool();
    boolean viewAllStudent();


    boolean addSchoolRecord(School school);
    boolean addStudentRecord(Student student);
    /**
     * Записать в файл список объектов и сохранить
     * @param id - id удаляемой записи
     * @return boolean, если сохранение успешно
     */
    boolean deleteSchoolRecord(long id);
    boolean deleteStudentRecord(long id);

    /**
     * Записать в файл список объектов и сохранить
     * @param id - id изменяемой записи
     * @param school - бин
     * @return boolean, если сохранение успешно
     */
    boolean updateSchoolRecord(long id, School school);
    boolean updateStudentRecord(long id, Student student);


    /**
     * Получить запись по id
     * @return Объект <Emp>, полученный по айди
     */
    School receiveSchoolRecordById(long id);
    Student receiveStudentRecordById(long id);
}
