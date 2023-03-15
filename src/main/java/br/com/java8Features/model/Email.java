package br.com.java8Features.model;

import java.util.Objects;

public class Email {

    private String enderecoRemetente;
    private String enderecoDestinatario;
    private String descricao;

    public Email() {
    }

    private Email(String enderecoRemetente, String enderecoDestinatario, String descricao) {
        this.enderecoRemetente = enderecoRemetente;
        this.enderecoDestinatario = enderecoDestinatario;
        this.descricao = descricao;
    }

    public static class EmailBuilder {

        private String enderecoRemetente;
        private String enderecoDestinatario;
        private String descricao;

        public EmailBuilder comEnderecoRemetente(String enderecoRemetente) {
            this.enderecoRemetente = enderecoRemetente;
            return this;
        }

        public EmailBuilder comEnderecoDestinatario(String enderecoDestinatario) {
            this.enderecoDestinatario = enderecoDestinatario;
            return this;
        }

        public EmailBuilder comDescricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Email construir() {
            return new Email(enderecoRemetente, enderecoDestinatario, descricao);
        }

    }

    public String getEnderecoRemetente() {
        return this.enderecoRemetente;
    }

    public void setEnderecoRemetente(String enderecoRemetente) {
        this.enderecoRemetente = enderecoRemetente;
    }

    public String getEnderecoDestinatario() {
        return this.enderecoDestinatario;
    }

    public void setEnderecoDestinatario(String enderecoDestinatario) {
        this.enderecoDestinatario = enderecoDestinatario;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Email enderecoRemetente(String enderecoRemetente) {
        setEnderecoRemetente(enderecoRemetente);
        return this;
    }

    public Email enderecoDestinatario(String enderecoDestinatario) {
        setEnderecoDestinatario(enderecoDestinatario);
        return this;
    }

    public Email descricao(String descricao) {
        setDescricao(descricao);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Email)) {
            return false;
        }
        Email email = (Email) o;
        return Objects.equals(enderecoRemetente, email.enderecoRemetente) && Objects.equals(enderecoDestinatario, email.enderecoDestinatario) && Objects.equals(descricao, email.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enderecoRemetente, enderecoDestinatario, descricao);
    }

    @Override
    public String toString() {
        return "{" +
            " enderecoRemetente='" + getEnderecoRemetente() + "'" +
            ", enderecoDestinatario='" + getEnderecoDestinatario() + "'" +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
    
}
