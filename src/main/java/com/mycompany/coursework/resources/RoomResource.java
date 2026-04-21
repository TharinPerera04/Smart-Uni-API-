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

}    