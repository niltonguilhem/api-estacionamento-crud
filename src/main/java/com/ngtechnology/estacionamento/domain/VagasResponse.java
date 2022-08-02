package com.ngtechnology.estacionamento.domain;

public class VagasResponse {

    private Long idvaga;
    private Boolean disponivel;

    public VagasResponse(){
    }

    public VagasResponse(Long idvaga, Boolean disponivel) {
        this.idvaga = idvaga;
        this.disponivel = disponivel;
    }

    public Long getIdvaga() {
        return idvaga;
    }

    public void setIdvaga(Long idvaga) {
        this.idvaga = idvaga;
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
        setIdvaga(vagasId);
        return this;
    }
}
