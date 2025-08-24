package br.com.jean.pecas;

public class Cavalo extends Peca {
    public Cavalo(boolean branca) {
        super(branca, 'H');
    }

    @Override
    public boolean validarMovimento(int linhaOrigem, int colunaOrigem,
                                    int linhaDestino, int colunaDestino,
                                    Peca[][] tabuleiro) {
        int deltaLinha = Math.abs(linhaDestino - linhaOrigem);
        int deltaColuna = Math.abs(colunaDestino - colunaOrigem);

        //move em L: 2 casas numa direção e 1 na perpendicular
        return (deltaLinha == 2 && deltaColuna == 1) || (deltaLinha == 1 && deltaColuna == 2);
    }
}