package at.htl.timetable.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
public class Unit extends PanacheEntityBase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public int day;
    public int unit;
    public String subject;

    @ManyToOne
    public Teacher teacher;

    @ManyToOne
    public Schoolclass schoolclass;
}
