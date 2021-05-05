package at.htl.timetable.boundary;

import at.htl.timetable.entity.Unit;
import at.htl.timetable.repository.UnitRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;
import java.time.Instant;
import java.util.List;

@Path("unit")
@ApplicationScoped
public class UnitResource {

    private Sse sse;
    private volatile SseBroadcaster sseBroadcaster;
    private OutboundSseEvent.Builder eventBuilder;

    @Context
    public void setSse(Sse sse) {
        this.sse = sse;
        this.eventBuilder = sse.newEventBuilder();
        if (sseBroadcaster == null) {
            this.sseBroadcaster = sse.newBroadcaster();
        }
    }

    @Inject
    UnitRepository unitRepository;

    @GET
    @Path("/class/{classid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unit> findByClass(@PathParam("classid") String classid) {
        return unitRepository.find("schoolclass.id=?1", classid).list();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response save(Unit unit) {
        unitRepository.getEntityManager().merge(unit);
        broadcast(unit);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") long id) {
        unitRepository.deleteById(id);
        return Response.ok().build();
    }

    @GET
    @Path("/sse")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void subscribe(@Context SseEventSink sseEventSink) {
        this.sseBroadcaster.register(sseEventSink);
    }


    public void broadcast(Unit changedUnit) {
        try {
            OutboundSseEvent event = eventBuilder
                    //.name("Unit")
                    .id("" + Instant.now().getNano())
                    .mediaType(MediaType.APPLICATION_JSON_TYPE)
                    .data(changedUnit)
                    .build();
            sseBroadcaster.broadcast(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
