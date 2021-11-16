package ru.sfedu.kursach.model.beans;

import java.io.Serializable;
import java.util.List;

public class Group implements Serializable {
    private long id;
    private String name;
    private List<Integer> students;
    private int teacherId;
    // как лучше сделать? через Id или сам объект? Наверное Id
}
