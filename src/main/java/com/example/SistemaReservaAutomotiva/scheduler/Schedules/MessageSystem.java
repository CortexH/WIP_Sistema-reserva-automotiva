package com.example.SistemaReservaAutomotiva.scheduler.Schedules;

import com.example.SistemaReservaAutomotiva.repositories.AlertRepository;
import com.example.SistemaReservaAutomotiva.services.AlertService;
import com.example.SistemaReservaAutomotiva.services.ReservationService;
import com.example.SistemaReservaAutomotiva.services.UserService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class MessageSystem {

    /*
     Casos:
        - Quando o usuário criar uma reserva, chegará um aviso para ele informando que
        a reserva foi criada

        - Quando o tempo para coletar o carro for menor que 1 hora, chegará um aviso
        informando que falta 1 hora para o tempo de coleta do carro

        - Quando for hora de coleta do carro, chegará um aviso informando ao usuário
        que é horário de coleta do carro.

        - Se o usuário não coletar o carro em pelo menos duas horas após o tempo de coleta,
        chegará um aviso informando que o usuário não poderá coletar o carro e terá duas
        opções: reagendar a reserva ou cancelá-la por completo.

        - Se o usuário tiver 3 cancelas por falta de compromisso, (e.g não coletar o carro no
        horário informado) o usuário estará bloqueado de realizar uma reserva, e chegará um
        aviso informando-o sobre isso. O usuário poderá recuperar a conta ao realizar um apelo.
     */

    @Autowired
    private UserService userService;
    @Autowired
    private AlertService alertService;
    @Autowired
    private ReservationService reservationService;

    public void beginMessageProccess(){
        log.info("PROCCESS BEGIN AT : {}", LocalDateTime.now());



    }



}
