package br.com.jean.pecas;

public class Peao extends Peca {
    private boolean primeiroMovimento;

    public Peao(boolean branca) {
        super(branca, 'P');
        this.primeiroMovimento = true;
    }

    @Override
    public boolean validarMovimento(int linhaOrigem, int colunaOrigem,
                                    int linhaDestino, int colunaDestino,
                                    Peca[][] tabuleiro) {
        int deltaLinha = linhaDestino - linhaOrigem;
        int deltaColuna = Math.abs(colunaDestino - colunaOrigem);

        //direção possível muda conforme time do peão
        //peão branco sobe e peão preto desce
        int direcao = branca ? -1 : 1;

        //movimento normal para frente (1 casa)
        if (deltaColuna == 0 && deltaLinha == direcao) {
            //só pode mover se não há peça no destino
            if (tabuleiro[linhaDestino][colunaDestino] == null) {
                primeiroMovimento = false;
                return true;
            }
        }

        //movimento inicial (2 casas) permitido apenas como primeiro movimento
        if (primeiroMovimento && deltaColuna == 0 && deltaLinha == (2 * direcao)) {
            //as duas casas tem de estar livres
            if (tabuleiro[linhaOrigem + direcao][colunaOrigem] == null && tabuleiro[linhaDestino][colunaDestino] == null) {
                primeiroMovimento = false;
                return true;
            }
        }

        //captura permitida apenas na diagonal
        if (deltaColuna == 1 && deltaLinha == direcao) {
            Peca pecaDestino = tabuleiro[linhaDestino][colunaDestino];
            if (pecaDestino != null && pecaDestino.isBranca() != this.branca) {
                primeiroMovimento = false;
                return true;
            }
        }

        return false;
    }
}