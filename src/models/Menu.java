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
        String simbolo = "";

        switch (opcao) {
            case 1: // USD para ARS
                resultado = valor * conversor.getArs();
                simbolo = "ARS $";
                break;
            case 2: // ARS para USD
                resultado = valor / conversor.getArs();
                simbolo = "USD $";
                break;
            case 3: // USD para BRL
                resultado = valor * conversor.getBrl();
                simbolo = "R$";
                break;
            case 4: // BRL para USD
                resultado = valor / conversor.getBrl();
                simbolo = "USD $";
                break;
            case 5: // USD para BOB
                resultado = valor * conversor.getBob();
                simbolo = "Bs";
                break;
            case 6: // BOB para USD
                resultado = valor / conversor.getBob();
                simbolo = "USD $";
                break;
        }

        System.out.println("O valor convertido é " + simbolo + " " + df.format(resultado));
    }

    private boolean perguntarSeContinua() {
        System.out.println("\nDeseja realizar outra conversão? (S/N)");
        String resposta = sc.next().trim().toUpperCase();
        return resposta.equals("S") || resposta.equals("SIM");
    }
}