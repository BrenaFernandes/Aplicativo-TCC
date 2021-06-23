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
public class Cliente {
    
    private String medidor;
    private String contrato;
    private String nome;
    private int rg;
    private int cpf;
    private int telefone;
    private String endereco;
    private int numero;
    private double demandacontratada;
    private String classe;
    private String unidadegeradora;
    private String alimentacao;
    private int tensao;
    private String poste;
    private String transformador;
    private String bairro;
    private String complemento;
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
    

        
    public Cliente(){
        
    }
    
    public Cliente(String medidor,String contrato,String nome,int rg,int cpf,int telefone,String endereco,
    int numero,double demandacontratada,String classe,String unidadegeradora,String alimentacao,int tensao,
            String poste,String transformador,String bairro,String complemento){
        this.medidor = medidor;
        this.contrato=contrato;
        this.nome=nome;
        this.rg=rg;
        this.cpf=cpf;
        this.telefone=telefone;
        this.endereco=endereco;
        this.numero=numero;
        this.demandacontratada=demandacontratada;
        this.classe=classe;
        this.unidadegeradora=unidadegeradora;
        this.alimentacao=alimentacao;
        this.tensao=tensao;
        this.poste=poste;
        this.transformador=transformador;
        this.bairro=bairro;
        this.complemento=complemento;
      
                            
    }

    public String getTransformador() {
        return transformador;
    }

    public void setTransformador(String transformador) {
        this.transformador = transformador;
    }

    public String getMedidor() {
        return medidor;
    }

    public void setMedidor(String medidor) {
        this.medidor = medidor;
    }
    

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getDemandacontratada() {
        return demandacontratada;
    }

    public void setDemandacontratada(double demandacontratada) {
        this.demandacontratada = demandacontratada;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getUnidadegeradora() {
        return unidadegeradora;
    }

    public void setUnidadegeradora(String unidadegeradora) {
        this.unidadegeradora = unidadegeradora;
    }

    public String getAlimentacao() {
        return alimentacao;
    }

    public void setAlimentacao(String alimentacao) {
        this.alimentacao = alimentacao;
    }

    public int getTensao() {
        return tensao;
    }

    public void setTensao(int tensao) {
        this.tensao = tensao;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    @Override
    public String toString() {
        return getBairro(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
