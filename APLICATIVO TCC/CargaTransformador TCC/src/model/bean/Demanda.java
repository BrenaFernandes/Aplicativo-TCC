/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Cliente
 */
public class Demanda {
    private int id;
    private String contrato;
    private int mes;
    private int ano;
    private double demandamed;
    private String equipamento;
    private String alimentacao;
    private double demandacalc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getAlimentacao() {
        return alimentacao;
    }

    public void setAlimentacao(String alimentacao) {
        this.alimentacao = alimentacao;
    }

    public double getDemandacalc() {
        return demandacalc;
    }

    public void setDemandacalc(double demandacalc) {
        this.demandacalc = demandacalc;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getDemandamed() {
        return demandamed;
    }

    public void setDemandamed(double demandamed) {
        this.demandamed = demandamed;
    }

    @Override
    public String toString() {
        return String.valueOf(getAno()); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     *
     * @return
     */
   
}
