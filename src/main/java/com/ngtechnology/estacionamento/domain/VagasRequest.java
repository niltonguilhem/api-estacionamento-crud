package com.ngtechnology.estacionamento.domain;

public class VagasRequest {
    private Boolean disponivel;

    public VagasRequest(){
    }

    public VagasRequest(Boolean disponivel){
        this.disponivel = disponivel;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }


}

