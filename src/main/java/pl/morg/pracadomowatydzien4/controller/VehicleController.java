package pl.morg.pracadomowatydzien4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.morg.pracadomowatydzien4.model.Vehicle;
import pl.morg.pracadomowatydzien4.service.VehicleService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getVehicles() {
        return new ResponseEntity<>(vehicleService.getVehicles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable long id) {
        Optional<Vehicle> vehicleById = vehicleService.getVehicleById(id);
        return vehicleById.map(vehicle -> new ResponseEntity<>(vehicle, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/color")
    public ResponseEntity<List<Vehicle>> getVehiclesByColor(@RequestParam String color) {
        List<Vehicle> vehiclesByColor = vehicleService.getVehiclesByColor(color);
        return vehiclesByColor.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(vehiclesByColor, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(vehicle)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping
    public ResponseEntity<?> updatePartOfVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.updatePartOfVehicle(vehicle)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping
    public ResponseEntity<?> removeVehicle(@PathVariable long id) {
        return vehicleService.removeVehicle(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public long getLastID() {
        return vehicleService.getLastId();
    }
}
