package com.ngtechnology.estacionamento.domain;

public class VagasResponse {

    private Long idVaga;
    private Boolean disponivel;

    public VagasResponse(){
    }

    public VagasResponse(Long id_Vaga, Boolean disponivel) {
        this.idVaga = id_Vaga;
        this.disponivel = disponivel;
    }

    public Long getId_Vaga() {
        return idVaga;
    }

    public void setId_Vaga(Long id_Vaga) {
        this.idVaga = id_Vaga;
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
        setId_Vaga(vagasId);
        return this;
    }
}
