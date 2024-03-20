package com.livecoding.estudos.domain.usuarios.enums;

public enum PapelPessoa {
    MOTORISTA("M", "Motorista"),
    COMPRADOR("C", "Comprador");

    private final String codigo;
    private final String descricao;

    PapelPessoa(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static PapelPessoa fromCodigo(String cod) {
        if ("M".equals(cod)) {
            return MOTORISTA;
        } else if ("C".equals(cod)) {
            return COMPRADOR;
        }
        throw new IllegalArgumentException("Papel Inválido");
    }

}