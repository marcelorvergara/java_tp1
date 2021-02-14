/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helloworldapp;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author marcelo
 */
public class Main {

    static int count = 0;
    //arrays apesar de terem tamanho 101, há um teste
    //para verificar se o há 100 alunos no início do código

    static String[] alunos = new String[100];
    static double[] av1 = new double[100];
    static double[] av2 = new double[100];
    static int cod;

    static void mainCaller() throws IOException {
        //chamando a main
        String[] nada = new String[5];
        count++;
        if (count < 101) {
            main(nada);
        }
    }

    static void clearScreen() throws IOException {
        final String operatingSystem = System.getProperty("os.name");
        for (int clear = 0; clear < 1000; clear++) {
            System.out.println("\b");
            System.out.flush();
        }
        if (operatingSystem.contains("Windows")) {
            Runtime.getRuntime().exec("cls");
        } else {
            Runtime.getRuntime().exec("clear");
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        clearScreen();

        //mock para a opção 3 - listar todos os alunos
        for (int i = 0; i < alunos.length; i++) {
            alunos[i] = "Aluno " + i;
            double a1 = (double) ((double) 0.0 + Math.random() * (10.0 - 0.0));
            BigDecimal bd1 = new BigDecimal(a1).setScale(2, RoundingMode.UP);
            av1[i] = Double.parseDouble(bd1.toString());
            double a2 = (double) ((double) 0.0 + Math.random() * (10.0 - 0.0));
            BigDecimal bd2 = new BigDecimal(a2).setScale(2, RoundingMode.UP);
            av2[i] = Double.parseDouble(bd2.toString());
        }
        //caso de inserção realizada com sucesso
        if (args.length == 1) {
            System.out.println(args[0]);
            System.out.println("Aluno(a): " + alunos[cod - 1] + " cadastrado(a) com o código: Cod-" + cod);
            if (cod == 1) {
                System.out.println(cod + " aluno(a) cadastrado.\n");
            } else {
                System.out.println(cod + " aluno(a)s cadastrado(a)s.\n");
            }
        }
        //caso de erro
        if (args.length == 2) {
            System.out.println(args[0] + args[1] + "\n");
        }
        //status individual do aluno
        if (args.length == 3) {
            System.out.println("****Status Individual do Aluno!****\n");
            System.out.println(args[0] + args[1] + args[2] + "\n");
        }

        String input = menuApp();

        switch (input.charAt(0)) {
            case '1':
                System.out.println(cod);
                //controle dos 100 alunos
                if (cod < 100) {
                    System.out.println("A opção escolhida é a opção: " + input + ". Registrar as notas de um novo aluno.");
                    System.out.println("Entre com o nome do aluno: ");
                    Scanner nomeIn = new Scanner(System.in);
                    String nome = nomeIn.nextLine();
                    alunos[cod] = nome;
                    System.out.println("Entre com a primeira nota do aluno (AV1): ");
                    Scanner av1In = new Scanner(System.in);
                    String nota1 = av1In.nextLine();
                    av1[cod] = Float.parseFloat(nota1);
                    System.out.println("Entre com a segunda nota do aluno (AV2): ");
                    Scanner av2In = new Scanner(System.in);
                    String nota2 = av2In.nextLine();
                    av2[cod] = Float.parseFloat(nota2);
                    System.out.println();
                    cod += 1;
                    mainCaller("****Operação realizada com sucesso!****\n");
                } else {
                    System.out.println("Número máximo de alunos atingido. Compre mais licença!\n");
                }

                break;
            case '2':
                System.out.println("A opção escolhida é a opção: " + input + ". Consultar boletim de um aluno.");
                System.out.println("Entre com o código do aluno. Ex. \"1\" para Cod-1: ");
                Scanner codIn = new Scanner(System.in);
                String codigo = codIn.nextLine();
                if (codigo.length() > 2) {
                    mainCaller("****Código inválido.****\n", "O código só pode ter 1 ou 2 digitos.");
                } else {
                    int codInt = Integer.parseInt(codigo);
                    String nomeAluno = alunos[codInt - 1];
                    double notaAv1 = av1[codInt - 1];
                    double notaAv2 = av2[codInt - 1];
                    double media = calculaMedia(notaAv1, notaAv2);
                    String status = calcualStatus(media);

                    String nomeStr = "Nome do Aluno: " + nomeAluno;
                    String notasStr = "\nNota AV1: " + notaAv1 + "\nNota AV2: " + notaAv2;
                    String statusStr = "\nSituação: " + status;
                    mainCaller(nomeStr, notasStr, statusStr);
                }

                break;
            case '3':
                System.out.println("A opção escolhida é a opção: " + input + ". Consultar notas da turma.\n");
                //se for mock, colocar alunos.length, senão use cod
                for (int i = 0; i < alunos.length; i++) {
                    double media = calculaMedia(av1[i], av2[i]);
                    String statusFinal = calcualStatus(media);
                    System.out.println("Aluno: " + alunos[i] + " Av1: " + av1[i] + " Av2: " + av2[i] + " Situação: " + statusFinal);
                }
                menuGeral(true);
                break;
            case '4':
                System.out.println("Inté!");
                break;
            default:
                clearScreen();
                mainCaller("****Opão inváliada.****\n", "As opções são de 1 a 4.");

        }
    }

    //após a opção 3 imprimir todos os alunos, é dada a opção de continuar no programa
    private static void menuGeral(boolean par) throws IOException {
        if (par) {
            System.out.println("Deseja continuar o programa?\nDigide \"S\" para continuar ou \"N\" para sair");
            Scanner cont = new Scanner(System.in);
            String contStr = cont.nextLine();
            if ("S".equals(contStr)) {
                mainCaller();
            } else if ("N".equals(contStr)) {
                System.exit(0);
            } else {
                //opção inválida
                menuGeral(false);
            }
        } else {
            System.out.println("Opção inválida.\nDigide \"S\" para continuar ou \"N\" para sair");
            Scanner cont = new Scanner(System.in);
            String contStr = cont.nextLine();
            if ("S".equals(contStr)) {
                mainCaller();
            } else if ("N".equals(contStr)) {
                System.exit(0);
            } else {
                System.out.println("Opção inválida");
                menuGeral(false);
            }
        }

    }

    private static String menuApp() {
        System.out.println("Escolha uma opção: ");
        System.out.println("[1] Registrar as notas de um novo aluno.");
        System.out.println("[2] Consultar boletim de um aluno.");
        System.out.println("[3] Consultar notas da turma.");
        System.out.println("[4] Sair.");
        Scanner myObj = new Scanner(System.in);
        String input = myObj.nextLine();
        return input;
    }

    //método para exibir mensagem quando um aluno é cadastrado
    private static void mainCaller(String msg) throws IOException {
        String[] msgList = new String[1];
        msgList[0] = msg;
        count++;
        if (count < 101) {
            main(msgList);
        }
    }

    private static void mainCaller(String msg, String erro) throws IOException {

        String[] msgList = new String[2];
        msgList[0] = msg;
        msgList[1] = erro;
        count++;
        if (count < 101) {
            main(msgList);
        }
    }

    private static double calculaMedia(double notaAv1, double notaAv2) {
        double media = (notaAv1 + notaAv2) / 2;
        BigDecimal bdMed = new BigDecimal(media).setScale(2, RoundingMode.UP);
        return Double.parseDouble(bdMed.toString());
    }

    private static String calcualStatus(double media) {
        if (media < 4.0) {
            return "Reprovado com a média " + media;
        } else if (media < 7.0) {
            return "Prova final. Obteve a média " + media;
        } else {
            return "Aprovado com a média " + media;
        }
    }

    private static void mainCaller(String nomeStr, String notasStr, String statusStr) throws IOException {
        String[] msgList = new String[3];
        msgList[0] = nomeStr;
        msgList[1] = notasStr;
        msgList[2] = statusStr;
        count++;
        if (count < 101) {
            main(msgList);
        }
    }
}
