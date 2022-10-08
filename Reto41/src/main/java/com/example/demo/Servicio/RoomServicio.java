/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Servicio;

import com.example.demo.Modelo.Room;
import com.example.demo.Repositorio.RoomRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service

public class RoomServicio {
      @Autowired
    private RoomRepositorio roomRepository;

    public List<Room> getAll(){
        return roomRepository.getAll();
    }

    public Optional<Room> getRoom(int roomId) {
        return roomRepository.getRoom(roomId);
    }

    public Room save(Room room){
        if(room.getId()==null){
            return roomRepository.save(room);
        }else{
            Optional<Room> e=roomRepository.getRoom(room.getId());
            if(e.isEmpty()){
                return roomRepository.save(room);
            }else{
                return room;
            }
        }
    } 
    
      public Room update(Room room){
        if(room.getId()!=null){
            Optional<Room> e=roomRepository.getRoom(room.getId());
            if(!e.isEmpty()){
                if(room.getName()!=null){
                    e.get().setName(room.getName());
                }
                if(room.getHotel()!=null){
                    e.get().setHotel(room.getHotel());
                }
                if(room.getStars()!=null){
                    e.get().setStars(room.getStars());
                }
                if(room.getDescription()!=null){
                    e.get().setDescription(room.getDescription());
                }
                if(room.getCategory()!=null){
                    e.get().setCategory(room.getCategory());
                }
                roomRepository.save(e.get());
                return e.get();
            }else{
                return room;
            }
        }else{
            return room;
        }
    }
    
    public boolean deleteRoom(int roomId){
        Boolean d=getRoom(roomId).map(room -> {
            roomRepository.delete(room);
            return true;
        }).orElse(false);
        return d;
    }
}
