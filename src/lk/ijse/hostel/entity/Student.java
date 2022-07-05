package lk.ijse.hostel.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

@Entity
public class Student {
    @Id
    private String id;
    private String name;
    private String address;
    private String contact;

  /*  @OneToMany(mappedBy = "id", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ReserveDetail> reserve ;*/
  /* @ManyToMany
   private List<Room> rooms = new ArrayList<>();*/


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<ReserveDetail> reserve ;


    public Student(String id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    /*
    public Student() {
    }

    public Student(String id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }*/
}
