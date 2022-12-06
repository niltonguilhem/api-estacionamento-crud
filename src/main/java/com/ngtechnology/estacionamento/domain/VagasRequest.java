package com.ngtechnology.estacionamento.domain;



import javax.validation.constraints.NotNull;


public class VagasRequest {

    @NotNull
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

