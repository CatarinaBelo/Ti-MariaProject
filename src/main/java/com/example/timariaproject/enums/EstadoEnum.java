package com.example.timariaproject.enums;

public enum  EstadoEnum {
    ATIVO(1, "Ativo"),
    EXPIRADO(2, "Expirado"),
    EM_APROVACAO(3, "Em Aprovação"),
    NAO_ATIVO(4, "Não Ativo");

    private final int id;
    private final String descricao;

    EstadoEnum(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EstadoEnum fromId(int id) {
        for (EstadoEnum estado : values()) {
            if (estado.id == id) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado inválido: " + id);
    }
}
