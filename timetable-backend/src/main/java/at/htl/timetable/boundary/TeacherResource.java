package at.htl.timetable.boundary;

import at.htl.timetable.entity.Teacher;
import at.htl.timetable.repository.TeacherRepository;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("teacher")
public class TeacherResource {

    @Inject
    TeacherRepository teacherRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Teacher> findAll() {
        return teacherRepository.findAll(Sort.by("lastname")).list();
    }
}
