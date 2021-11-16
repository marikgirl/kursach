package ru.sfedu.kursach.model.beans;

import java.io.Serializable;
import java.util.Objects;

public class Student extends User implements Serializable {
    private School school;
    private int classNumber;
    private char classLetter;
    private Group group;

    public Student() {
        super();
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    public char getClassLetter() {
        return classLetter;
    }

    public void setClassLetter(char classLetter) {
        this.classLetter = classLetter;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return classNumber == student.classNumber && classLetter == student.classLetter && school.equals(student.school) && group.equals(student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), school, classNumber, classLetter, group);
    }
}
