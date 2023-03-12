package br.com.java8Features.model;

import java.time.LocalDate;

public class Cliente {

    private String nome;
    private boolean emailAniversarioEnviado;
    private LocalDate dataAniversario;
    private String email;

    private Cliente(String nome, boolean emailAniversarioEnviado, LocalDate dataAniversario, String email) {
        this.nome = nome;
        this.emailAniversarioEnviado = emailAniversarioEnviado;
        this.dataAniversario = dataAniversario;
        this.email = email;
    }

    public static class ClienteBuilder {

        private String nome;
        private boolean emailAniversarioEnviado;
        private LocalDate dataAniversario;
        private String email;

        public ClienteBuilder comNome(String nome){
            this.nome = nome;
            return this;
        }

        public ClienteBuilder comEmailAniversarioEnviado(boolean emailAniversarioEnviado){
            this.emailAniversarioEnviado = emailAniversarioEnviado;
            return this;
        }

        public ClienteBuilder comDataAniversario(LocalDate dataAniversario){
            this.dataAniversario = dataAniversario;
            return this;
        }

        public ClienteBuilder comEmail(String email){
            this.email = email;
            return this;
        }

        public Cliente construir() {
            return new Cliente(nome, emailAniversarioEnviado, dataAniversario, email);
        }

    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isemailAniversarioEnviado() {
        return this.emailAniversarioEnviado;
    }

    public boolean getemailAniversarioEnviado() {
        return this.emailAniversarioEnviado;
    }

    public void setemailAniversarioEnviado(boolean emailAniversarioEnviado) {
        this.emailAniversarioEnviado = emailAniversarioEnviado;
    }


    public boolean isEmailAniversarioEnviado() {
        return this.emailAniversarioEnviado;
    }

    public boolean getEmailAniversarioEnviado() {
        return this.emailAniversarioEnviado;
    }

    public void setEmailAniversarioEnviado(boolean emailAniversarioEnviado) {
        this.emailAniversarioEnviado = emailAniversarioEnviado;
    }

    public LocalDate getDataAniversario() {
        return this.dataAniversario;
    }

    public void setDataAniversario(LocalDate dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Cliente atualizarEmailAniversarioEnviado(Cliente cliente) {
        if (cliente.getDataAniversario() == null) {
            cliente.setEmailAniversarioEnviado(Boolean.FALSE);
        }

        return cliente;

    }

}
