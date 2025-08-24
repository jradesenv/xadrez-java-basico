package br.com.jean.pecas;

public class Bispo extends Peca {
    public Bispo(boolean branca) {
        super(branca, 'B');
    }

    @Override
    public boolean validarMovimento(int linhaOrigem, int colunaOrigem,
                                    int linhaDestino, int colunaDestino,
                                    Peca[][] tabuleiro) {
        int deltaLinha = Math.abs(linhaDestino - linhaOrigem);
        int deltaColuna = Math.abs(colunaDestino - colunaOrigem);

        //move apenas na diagonal
        return deltaLinha == deltaColuna && deltaLinha > 0;
    }
}