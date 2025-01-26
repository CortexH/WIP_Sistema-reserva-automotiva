package com.example.SistemaReservaAutomotiva.services;

import com.example.SistemaReservaAutomotiva.domain.vehicle.Disponibility;
import com.example.SistemaReservaAutomotiva.domain.vehicle.Vehicle;
import com.example.SistemaReservaAutomotiva.dto.input.RegisterVehicleDTO;
import com.example.SistemaReservaAutomotiva.dto.output.RegisteredVehicleDTO;
import com.example.SistemaReservaAutomotiva.dto.output.ReturnVehicleDTO;
import com.example.SistemaReservaAutomotiva.exceptions.BadRequestCustomException;
import com.example.SistemaReservaAutomotiva.exceptions.VehicleNotAvailableException;
import com.example.SistemaReservaAutomotiva.repositories.VehicleRepository;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public RegisteredVehicleDTO registerVehicle(RegisterVehicleDTO data){
        if(vehicleRepository.existsByPlate(data.license_plate())) throw new BadRequestCustomException("There is already a vehicle with specified license_plate");

        Vehicle vehicle = Vehicle.builder()
                .color(data.color())
                .model(data.model())
                .mileage(data.mileage())
                .price(data.day_price())
                .brand(data.brand())
                .fab_year(data.fabrication_year())
                .plate(data.license_plate())
                .registerday(LocalDate.now())
                .disponibility(Disponibility.AVAILABLE)
                .build();

        Vehicle registered_Vehicle = vehicleRepository.save(vehicle);

        return RegisteredVehicleDTO.builder().registry(registered_Vehicle.getRegistry().toString()).build();
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public Vehicle getById(String id){
        UUID uid = UUID.fromString(id);

        Vehicle vh = vehicleRepository.findById(uid).orElseThrow(() -> new BadRequestCustomException("invalid registry"));
        if(vh == null) throw new BadRequestCustomException("invalid registry");

        return vh;
    }

    public List<ReturnVehicleDTO> getTopReserved(Integer limit){
        if(limit > 100){
            throw new BadRequestCustomException("Please insert a limit below to 100");
        }

        List<Vehicle> allVehicles = vehicleRepository.findTopMostReservedNative(limit);

        List<ReturnVehicleDTO> filteredVehicles = new ArrayList<>();

        for(int i = 0; i < allVehicles.size(); i++){
            Vehicle v = allVehicles.get(i);
            ReturnVehicleDTO data = new ReturnVehicleDTO(
                    v.getRegistry().toString(),
                    v.getBrand(),
                    v.getModel(),
                    v.getFab_year(),
                    v.getPlate(),
                    v.getColor(),
                    v.getMileage(),
                    v.getPrice(),
                    v.getDisponibility()
            );
            filteredVehicles.add(i, data);
        }

        return filteredVehicles;

    }

    public Vehicle getByLicensePlate(String plate){
        Vehicle vehicle = vehicleRepository.findByPlate(plate)
                .orElseThrow(() -> new BadRequestCustomException("Invalid license plate"));

        if(vehicle == null){
            throw new BadRequestCustomException("Invalid license plate");
        }

        return vehicle;
    }

}
