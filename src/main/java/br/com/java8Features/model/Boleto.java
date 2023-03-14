package br.com.java8Features.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Boleto {

    private Long codigo;
    private BigDecimal valor;
    private Cliente cliente;

    public Boleto() {
    }

    private Boleto(Long codigo, BigDecimal valor, Cliente cliente) {
        this.codigo = codigo;
        this.valor = valor;
        this.cliente = cliente;
    }

    public static class BoletoBuilder {

        private Long codigo;
        private BigDecimal valor;
        private Cliente cliente;

        public BoletoBuilder comCodigo(Long codigo) {
            this.codigo = codigo;
            return this;
        }

        public BoletoBuilder comValor(BigDecimal valor) {
            this.valor = valor;
            return this;
        }
        
        public BoletoBuilder comCliente(Cliente cliente) {
            this.cliente = cliente;
            return this;
        }

        public Boleto construir() {
            return new Boleto(codigo, valor, cliente);
        }
        
    }

    public Long getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Boleto codigo(Long codigo) {
        setCodigo(codigo);
        return this;
    }

    public Boleto valor(BigDecimal valor) {
        setValor(valor);
        return this;
    }

    public Boleto cliente(Cliente cliente) {
        setCliente(cliente);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Boleto)) {
            return false;
        }
        Boleto boleto = (Boleto) o;
        return Objects.equals(codigo, boleto.codigo) && Objects.equals(valor, boleto.valor) && Objects.equals(cliente, boleto.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, valor, cliente);
    }

    @Override
    public String toString() {
        return "{" +
            " codigo='" + getCodigo() + "'" +
            ", valor='" + getValor() + "'" +
            ", cliente='" + getCliente() + "'" +
            "}";
    }

}
