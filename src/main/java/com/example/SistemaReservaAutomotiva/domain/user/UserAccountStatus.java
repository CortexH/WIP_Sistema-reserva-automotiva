package com.example.SistemaReservaAutomotiva.domain.user;

public enum UserAccountStatus {

    ALLOWED, // permitido para as ações
    BLOCKED, // bloqueado para as ações
    DELETED, // apagado (deadzone sem retorno)
    BANNED // banido temporáriamente

}
