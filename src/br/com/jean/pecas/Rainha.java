package br.com.jean.pecas;

public class Rainha extends Peca {
    public Rainha(boolean branca) {
        super(branca, 'Q');
    }

    @Override
    public boolean validarMovimento(int linhaOrigem, int colunaOrigem,
                                    int linhaDestino, int colunaDestino,
                                    Peca[][] tabuleiro) {
        int deltaLinha = Math.abs(linhaDestino - linhaOrigem);
        int deltaColuna = Math.abs(colunaDestino - colunaOrigem);

        //move na horizontal, vertical ou diagonal
        return deltaLinha == 0 || deltaColuna == 0 || deltaLinha == deltaColuna;
    }
}