package at.htl.timetable.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Teacher extends PanacheEntityBase {
    @Id
    public Long id;
    public String firstName;
    public String lastName;
    public String room;


}
