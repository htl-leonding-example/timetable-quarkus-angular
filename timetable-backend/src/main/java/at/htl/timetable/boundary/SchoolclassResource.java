package at.htl.timetable.boundary;

import at.htl.timetable.entity.Schoolclass;
import at.htl.timetable.repository.SchoolclassRepository;
import io.quarkus.panache.common.Sort;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/class/")
public class SchoolclassResource {

    @Inject
    SchoolclassRepository schoolclassRepository;

    @GET
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Schoolclass> findAll() {
        return schoolclassRepository.findAll(Sort.by("id")).list();
    }
}
