package models;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final Scanner sc = new Scanner(System.in);
    private final DecimalFormat df = new DecimalFormat("0.00");
    private final ExchangeApi exchange = new ExchangeApi();

    public void openMenu() {
        Conversor conversor = exchange.callApi();
        if (conversor == null) {
            System.out.println("Erro ao obter taxas de câmbio. Verifique sua conexão.");
            return;
        }

        boolean continuar = true;
        while (continuar) {
            exibirOpcoes();
            int opcaoSelecionada = lerOpcao(1, 7);

            if (opcaoSelecionada == 7) {
                System.out.println("Obrigado por usar o nosso programa!");
                continuar = false;
                continue;
            }

            double valorFornecido = lerValor();
            realizarConversao(opcaoSelecionada, valorFornecido, conversor);

            continuar = perguntarSeContinua();
        }
        sc.close();
    }

    private void exibirOpcoes() {
        System.out.println("\n*********************************************");
        System.out.println("Seja bem-vindo(a) ao Conversor de Moeda");
        System.out.println();
        System.out.println("1) Dólar =>> Peso argentino");
        System.out.println("2) Peso argentino =>> Dólar");
        System.out.println("3) Dólar =>> Real brasileiro");
        System.out.println("4) Real brasileiro =>> Dólar");
        System.out.println("5) Dólar =>> Boliviano");
        System.out.println("6) Boliviano =>> Dólar");
        System.out.println("7) Sair");
        System.out.println("Escolha uma opção válida:");
    }

    private int lerOpcao(int min, int max) {
        int opcao = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                opcao = sc.nextInt();
                if (opcao >= min && opcao <= max) {
                    entradaValida = true;
                } else {
                    System.out.println("Opção inválida. Digite um número entre " + min + " e " + max + ":");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite apenas números:");
                sc.next(); // Limpa o buffer
            }
        }
        return opcao;
    }

    private double lerValor() {
        System.out.println("Digite o valor a converter: ");
        double valor = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                valor = sc.nextDouble();
                if (valor >= 0) {
                    entradaValida = true;
                } else {
                    System.out.println("Por favor, digite um valor positivo:");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um valor numérico válido:");
                sc.next(); // Limpa o buffer
            }
        }
        return valor;
    }

    private void realizarConversao(int opcao, double valor, Conversor conversor) {
        double resultado = 0;
        String simbolo = switch (opcao) {
            case 1 -> {
                resultado = valor * conversor.getArs();
                yield "ARS $";
            }
            case 2 -> {
                resultado = valor / conversor.getArs();
                yield "USD $";
            }
            case 3 -> {
                resultado = valor * conversor.getBrl();
                yield "R$";
            }
            case 4 -> {
                resultado = valor / conversor.getBrl();
                yield "USD $";
            }
            case 5 -> {
                resultado = valor * conversor.getBob();
                yield "Bs";
            }
            case 6 -> {
                resultado = valor / conversor.getBob();
                yield "USD $";
            }
            default -> "";
        };

        System.out.println("O valor convertido é " + simbolo + " " + df.format(resultado));
    }

    private boolean perguntarSeContinua() {
        System.out.println("\nDeseja realizar outra conversão? (S/N)");
        String resposta = sc.next().trim().toUpperCase();
        return resposta.equals("S") || resposta.equals("SIM");
    }
}