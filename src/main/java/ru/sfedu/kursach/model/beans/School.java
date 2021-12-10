package ru.sfedu.kursach.model.beans;

import com.opencsv.bean.CsvBindByName;
import org.simpleframework.xml.Element;

import java.io.Serializable;
import java.util.Objects;

public class School implements Serializable {
    @Element
    @CsvBindByName
    private long id;
    @Element
    @CsvBindByName
    private int number;
    @Element
    @CsvBindByName
    private String address;

    public School(){
        super();
    }

    public long getId() {
        return id;
    }

    public void setId() {this.id = System.currentTimeMillis();}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return id == school.id && number == school.number && Objects.equals(address, school.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, address);
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", number=" + number +
                ", address='" + address + '\'' +
                '}';
    }
}
