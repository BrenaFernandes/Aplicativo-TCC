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
public class Equipamento {
    private String id;
    private String sigla;
    private String modelo;
    private String marca;
    private String alimentador;
    private String subestacao;
    private int potencia;
    private int tensaoaliment;
    private String descricao;
    private String rua;
    private int numero;
    private String bairro;
    private String complemento;
    private String coordenada;
    private String codigolocalizacao;
    private String cidade;
    private String estado;
    private String cep;
    private int numfases;

    public int getNumfases() {
        return numfases;
    }

    public void setNumfases(int numfases) {
        this.numfases = numfases;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAlimentador() {
        return alimentador;
    }

    public void setAlimentador(String alimentador) {
        this.alimentador = alimentador;
    }

    public String getSubestacao() {
        return subestacao;
    }

    public void setSubestacao(String subestacao) {
        this.subestacao = subestacao;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getTensaoaliment() {
        return tensaoaliment;
    }

    public void setTensaoaliment(int tensaoaliment) {
        this.tensaoaliment = tensaoaliment;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(String coordenada) {
        this.coordenada = coordenada;
    }

    public String getCodigolocalizacao() {
        return codigolocalizacao;
    }

    public void setCodigolocalizacao(String codigolocalizacao) {
        this.codigolocalizacao = codigolocalizacao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return getId(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
