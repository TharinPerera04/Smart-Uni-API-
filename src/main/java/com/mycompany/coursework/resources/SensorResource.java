/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursework.resources;
import com.mycompany.coursework.data.DataStore;
import com.mycompany.coursework.model.Room;
import com.mycompany.coursework.model.Sensor;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
/**
 *
 * @author User
 */
public class SensorResource {

    @POST
    public Response createSensor(Sensor sensor, @Context UriInfo uriInfo) {

        if (sensor.getId() == null || sensor.getId().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Sensor ID is required")
                    .build();
        }

        if (sensor.getRoomId() == null || sensor.getRoomId().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("roomId is required")
                    .build();
        }

        Room room = DataStore.rooms.get(sensor.getRoomId());

        if (room == null) {
            return Response.status(422)
                    .entity("Referenced room does not exist")
                    .build();
        }

        DataStore.sensors.put(sensor.getId(), sensor);

        if (!room.getSensorIds().contains(sensor.getId())) {
            room.getSensorIds().add(sensor.getId());
        }

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(sensor.getId())
                .build();

        return Response.created(location).entity(sensor).build();
    }

    @GET
    public Response getSensors(@QueryParam("type") String type) {

        Collection<Sensor> allSensors = DataStore.sensors.values();

        if (type == null || type.trim().isEmpty()) {
            return Response.ok(allSensors).build();
        }

        List<Sensor> filteredSensors = new ArrayList<>();

        for (Sensor sensor : allSensors) {
            if (sensor.getType() != null && sensor.getType().equalsIgnoreCase(type)) {
                filteredSensors.add(sensor);
            }
        }

        return Response.ok(filteredSensors).build();
    }

    @GET
    @Path("/{id}")
    public Response getSensorById(@PathParam("id") String id) {
        Sensor sensor = DataStore.sensors.get(id);

        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Sensor not found")
                    .build();
        }

        return Response.ok(sensor).build();
    }
}