package br.com.jean.pecas;

public abstract class Peca {
    protected boolean branca;
    protected char simbolo;

    public Peca(boolean branca, char simbolo) {
        this.branca = branca;
        this.simbolo = simbolo;
    }

    public char getSimbolo() {
        return branca ? Character.toLowerCase(simbolo) : Character.toUpperCase(simbolo);
    }

    public boolean isBranca() {
        return branca;
    }

    public abstract boolean validarMovimento(int linhaOrigem, int colunaOrigem,
                                             int linhaDestino, int colunaDestino,
                                             Peca[][] tabuleiro);
}