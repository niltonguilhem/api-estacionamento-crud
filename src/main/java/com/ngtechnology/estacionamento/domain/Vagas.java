package com.ngtechnology.estacionamento.domain;

import javax.persistence.*;

@Entity
@Table (name = "vagas")
public class Vagas {

    /**@Column o que faz essa anotação? **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvaga")
    private Long idVaga;
    @Column(name = "disponivel")
    private Boolean disponivel;

    public Vagas(){

    }

    public Long getIdVaga() {
        return idVaga;
    }

    public void setIdvaga(Long idVaga) {
        this.idVaga = idVaga;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Vagas withBuilderDisponivel(Boolean disponivel){
        setDisponivel(disponivel);
        return this;
    }
    public Vagas withBuilderVagasId(Long id){
        setIdvaga(id);
        return this;
    }

}
