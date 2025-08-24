package br.com.jean.pecas;

public class Rei extends Peca {
    public Rei(boolean branca) {
        super(branca, 'K');
    }

    @Override
    public boolean validarMovimento(int linhaOrigem, int colunaOrigem,
                                    int linhaDestino, int colunaDestino,
                                    Peca[][] tabuleiro) {
        int deltaLinha = Math.abs(linhaDestino - linhaOrigem);
        int deltaColuna = Math.abs(colunaDestino - colunaOrigem);

        //move apenas uma casa em qualquer direção
        return deltaLinha <= 1 && deltaColuna <= 1;
    }
}