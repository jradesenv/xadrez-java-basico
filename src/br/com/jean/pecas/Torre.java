package br.com.jean.pecas;

public class Torre extends Peca {
    public Torre(boolean branca) {
        super(branca, 'R');
    }

    @Override
    public boolean validarMovimento(int linhaOrigem, int colunaOrigem,
                                    int linhaDestino, int colunaDestino,
                                    Peca[][] tabuleiro) {
        //move apenas na horizontal ou vertical
        return linhaOrigem == linhaDestino || colunaOrigem == colunaDestino;
    }
}