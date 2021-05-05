package at.htl.timetable.repository;

import at.htl.timetable.entity.Unit;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UnitRepository implements PanacheRepository<Unit> {
}
