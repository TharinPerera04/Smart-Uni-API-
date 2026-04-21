/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursework.resources;
import com.mycompany.coursework.data.DataStore;
import com.mycompany.coursework.model.Room;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.net.URI;
import java.util.Collection;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
/**
 *
 * @author User
 */
public class RoomResource {

    // GET all rooms
    @GET
    public Collection<Room> getAllRooms() {
        return DataStore.rooms.values();
    }

    // POST create room
    @POST
    public Response createRoom(Room room, @Context UriInfo uriInfo) {

        if (room.getId() == null || room.getId().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Room ID is required")
                    .build();
        }

        DataStore.rooms.put(room.getId(), room);

        URI location = uriInfo.getAbsolutePathBuilder()
                .path(room.getId())
                .build();

        return Response.created(location).entity(room).build();
    }

    // GET room by ID
    @GET
    @Path("/{id}")
    public Response getRoom(@PathParam("id") String id) {

        Room room = DataStore.rooms.get(id);

        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Room not found")
                    .build();
        }

        return Response.ok(room).build();
    }

    
}