package at.htl.timetable.repository;

import at.htl.timetable.entity.Schoolclass;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SchoolclassRepository implements PanacheRepository<Schoolclass> {
}
