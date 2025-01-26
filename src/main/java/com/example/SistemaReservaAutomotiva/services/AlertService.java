package com.example.SistemaReservaAutomotiva.services;

import com.example.SistemaReservaAutomotiva.domain.alert.Alert;
import com.example.SistemaReservaAutomotiva.domain.alert.AlertState;
import com.example.SistemaReservaAutomotiva.domain.user.User;
import com.example.SistemaReservaAutomotiva.dto.input.NewAlertDTO;
import com.example.SistemaReservaAutomotiva.dto.input.SendAlertDTO;
import com.example.SistemaReservaAutomotiva.dto.output.AlertUpdatedDTO;
import com.example.SistemaReservaAutomotiva.exceptions.BadRequestCustomException;
import com.example.SistemaReservaAutomotiva.exceptions.NotFound404Exception;
import com.example.SistemaReservaAutomotiva.repositories.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private UserService userService;

    public void createAlert(NewAlertDTO data){
        Alert alert = Alert.builder()
                .emissionTime(LocalDateTime.now())
                .user(data.user())
                .message(data.message())
                .type(data.type())
                .state(AlertState.PENDING)
                .build();
        alertRepository.save(alert);
    }

    public AlertUpdatedDTO changeAlertState(SendAlertDTO data, String token){
        Alert alert = findAlertByRegistry(data.registry());
        if(alert.getUser().getMail().equals(userService.getUserByToken(token).getMail())){
            throw new NotFound404Exception("Alert with specified registry not found");
        }
        AlertState prevAlert = alert.getState();
        alert.setState(data.new_state());

        Alert saved_Alert = alertRepository.save(alert);

        return new AlertUpdatedDTO(saved_Alert.getId().toString(),
                saved_Alert.getState(),
                "Alert state updated successfully",
                alert.getState().name() + " -> " + prevAlert.name()
                );
    }

    public List<Alert> getUserAlertsByToken(String token, AlertState state, Long id){
        User user;
        if(id != null){
            user = userService.getUserById(id);
        }else if(token != null){
            user = userService.getUserByToken(token);
        }else{
            throw new BadRequestCustomException("Token or User Id not found");
        }

        List<Alert> allAlerts = alertRepository.findAllByUser(user);

        List<Alert> filteredAlert;

        if(state == AlertState.PENDING){
            filteredAlert = new ArrayList<>();
            allAlerts.forEach(alert -> {
                if(alert.getState() == AlertState.PENDING) filteredAlert.add(alert);
            });
        } else if(state == AlertState.READ) {
            filteredAlert = new ArrayList<>();
            allAlerts.forEach(alert -> {
                if(alert.getState() == AlertState.READ) filteredAlert.add(alert);
            });
        }else if(state == AlertState.DELETED) {
            filteredAlert = new ArrayList<>();
            allAlerts.forEach(alert -> {
                if(alert.getState() == AlertState.DELETED) filteredAlert.add(alert);
            });
        }else{
            filteredAlert = allAlerts;
        }

        return filteredAlert;
    }

    public Alert findAlertByRegistry(String registry){
        return alertRepository.findById(UUID.fromString(registry))
                .orElseThrow(() -> new NotFound404Exception("Alert with specified registry not found"));
    }

}
