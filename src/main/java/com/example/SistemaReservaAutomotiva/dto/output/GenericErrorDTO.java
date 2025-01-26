package com.example.SistemaReservaAutomotiva.dto.output;


import java.time.LocalDateTime;

public record GenericErrorDTO(
        String timestamp,
        Integer code,
        String message

) {


}
