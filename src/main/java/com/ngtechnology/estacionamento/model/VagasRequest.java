package com.ngtechnology.estacionamento.model;



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

