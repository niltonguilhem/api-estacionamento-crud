package com.ngtechnology.estacionamento.domain;

public class VagasResponse {

    private Long idVaga;
    private Boolean disponivel;

    public VagasResponse(){
    }

    public VagasResponse(Long idVaga, Boolean disponivel) {
        this.idVaga = idVaga;
        this.disponivel = disponivel;
    }

    public Long getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(Long idVaga) {
        this.idVaga = idVaga;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public VagasResponse withBuilderDisponivel(Boolean disponivel){
        setDisponivel(disponivel);
        return this;
    }
    public VagasResponse withBuilderVagasId(Long vagasId){
        setIdVaga(vagasId);
        return this;
    }
}
