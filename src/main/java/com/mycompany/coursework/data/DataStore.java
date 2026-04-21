/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.coursework.data;

import com.mycompany.coursework.model.Room;
import com.mycompany.coursework.model.Sensor;
import com.mycompany.coursework.model.SensorReading;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author User
 */
public class DataStore {
    public static Map<String, Room> rooms = new HashMap<>();
    public static Map<String, Sensor> sensors = new HashMap<>();
    
        // key = sensorId, value = list of readings for that sensor
    public static Map<String, List<SensorReading>> readings = new HashMap<>();
}