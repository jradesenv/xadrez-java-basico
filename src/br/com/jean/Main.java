package br.com.jean;

import br.com.jean.pecas.*;

import java.util.Scanner;

public class Main {
    private static Peca[][] tabuleiro = new Peca[8][8];
    private static Scanner scanner = new Scanner(System.in);
    private static boolean vezDosBrancos = true;

    public static void main(String[] args) {
        inicializarTabuleiro();

        System.out.println("=== JOGO DE XADREZ SIMPLES ===");
        System.out.println("Peças brancas: minúsculas | Peças pretas: MAIÚSCULAS");
        System.out.println("K=Rei, Q=Rainha, R=Torre, B=Bispo, H=Cavalo, P=Peão");
        System.out.println("Coordenadas: linha (0-7) e coluna (0-7)");
        System.out.println("Digite -1 em qualquer coordenada para sair do jogo");
        System.out.println("BRANCOS começam jogando!");
        System.out.println();

        while (true) {
            exibirTabuleiro();

            if (verificarFimDeJogo()) {
                break;
            }

            //mostra de quem é a vez
            String jogadorAtual = vezDosBrancos ? "BRANCO (minúsculas)" : "PRETO (MAIÚSCULAS)";
            System.out.println("\n=== VEZ DO TIME " + jogadorAtual + " ===");
            System.out.print("Linha origem (0-7, -1 para sair): ");
            int linhaOrigem = scanner.nextInt();

            if (linhaOrigem == -1) {
                System.out.println("Jogo encerrado pelo jogador!");
                break;
            }

            System.out.print("Coluna origem (0-7, -1 para sair): ");
            int colunaOrigem = scanner.nextInt();

            if (colunaOrigem == -1) {
                System.out.println("Jogo encerrado pelo jogador!");
                break;
            }

            if (!coordenadaValida(linhaOrigem, colunaOrigem) ||
                    tabuleiro[linhaOrigem][colunaOrigem] == null) {
                System.out.println("Posição inválida ou vazia!");
                continue;
            }

            //verifica se a peça pertence ao time atual
            Peca pecaSelecionada = tabuleiro[linhaOrigem][colunaOrigem];
            if (pecaSelecionada.isBranca() != vezDosBrancos) {
                String corEsperada = vezDosBrancos ? "branca" : "preta";
                System.out.println("Você só pode mover peças " + corEsperada + "s!");
                continue;
            }

            System.out.println("PEÇA SELECIONADA: " + pecaSelecionada.getSimbolo());

            System.out.print("Linha destino (0-7, -1 para sair): ");
            int linhaDestino = scanner.nextInt();

            if (linhaDestino == -1) {
                System.out.println("Jogo encerrado pelo jogador!");
                break;
            }

            System.out.print("Coluna destino (0-7, -1 para sair): ");
            int colunaDestino = scanner.nextInt();

            if (colunaDestino == -1) {
                System.out.println("Jogo encerrado pelo jogador!");
                break;
            }

            if (!coordenadaValida(linhaDestino, colunaDestino)) {
                System.out.println("Posição de destino inválida!");
                continue;
            }

            if (moverPeca(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
                vezDosBrancos = !vezDosBrancos;
            }
        }

        System.out.println("\n=== FIM DO JOGO ===");
        System.out.println("Obrigado por jogar!");
        scanner.close();
    }

    private static void inicializarTabuleiro() {
        //peças pretas
        tabuleiro[0][0] = new Torre(false);
        tabuleiro[0][1] = new Cavalo(false);
        tabuleiro[0][2] = new Bispo(false);
        tabuleiro[0][3] = new Rainha(false);
        tabuleiro[0][4] = new Rei(false);
        tabuleiro[0][5] = new Bispo(false);
        tabuleiro[0][6] = new Cavalo(false);
        tabuleiro[0][7] = new Torre(false);

        for (int i = 0; i < 8; i++) {
            tabuleiro[1][i] = new Peao(false);
        }

        //peças brancas
        for (int i = 0; i < 8; i++) {
            tabuleiro[6][i] = new Peao(true);
        }

        tabuleiro[7][0] = new Torre(true);
        tabuleiro[7][1] = new Cavalo(true);
        tabuleiro[7][2] = new Bispo(true);
        tabuleiro[7][3] = new Rainha(true);
        tabuleiro[7][4] = new Rei(true);
        tabuleiro[7][5] = new Bispo(true);
        tabuleiro[7][6] = new Cavalo(true);
        tabuleiro[7][7] = new Torre(true);
    }

    private static void exibirTabuleiro() {
        System.out.println("\n  0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                if (tabuleiro[i][j] == null) {
                    System.out.print("# ");
                } else {
                    System.out.print(tabuleiro[i][j].getSimbolo() + " ");
                }
            }
            System.out.println();
        }
    }

    private static boolean coordenadaValida(int linha, int coluna) {
        return linha >= 0 && linha < 8 && coluna >= 0 && coluna < 8;
    }

    private static boolean verificarFimDeJogo() {
        boolean reiBrancoExiste = false;
        boolean reiPretoExiste = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tabuleiro[i][j] instanceof Rei) {
                    if (tabuleiro[i][j].isBranca()) {
                        reiBrancoExiste = true;
                    } else {
                        reiPretoExiste = true;
                    }
                }
            }
        }

        if (!reiBrancoExiste) {
            System.out.println("Rei branco foi capturado!");
            System.out.println("peças PRETAS venceram!");
            return true;
        }

        if (!reiPretoExiste) {
            System.out.println("Rei preto foi capturado!");
            System.out.println("peças BRANCAS venceram!");
            return true;
        }

        return false;
    }

    private static boolean moverPeca(int linhaOrigem, int colunaOrigem,
                                     int linhaDestino, int colunaDestino) {
        Peca peca = tabuleiro[linhaOrigem][colunaOrigem];

        //valida se nao vai capturar peça do mesmo time
        if (tabuleiro[linhaDestino][colunaDestino] != null &&
                tabuleiro[linhaDestino][colunaDestino].isBranca() == peca.isBranca()) {
            System.out.println("Não é possível capturar peça da mesma cor!");
            return false;
        }

        //polimorfismo: cada peça implementa sua própria validação
        if (peca.validarMovimento(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino, tabuleiro)) {
            //captura peça se houver
            if (tabuleiro[linhaDestino][colunaDestino] != null) {
                System.out.println("Peça capturada: " + tabuleiro[linhaDestino][colunaDestino].getSimbolo());
            }

            //posiciona a peça
            tabuleiro[linhaDestino][colunaDestino] = peca;
            tabuleiro[linhaOrigem][colunaOrigem] = null;
            System.out.println("Movimento realizado com sucesso!");
            return true;
        } else {
            System.out.println("Movimento inválido para esta peça!");
            return false;
        }
    }
}