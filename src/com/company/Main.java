package com.company;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);  // Usado para conseguir obter input to user
        String readKey;         // usado para simular o readkey em c#

        // Variaveis para navegar no menu
        int i = 0;
        String escolha;

        ArrayList<Pessoa> listaDePessoas = new ArrayList<>();

        // Variaveis para usar no adicionamento de uma pessoa nova
        String nomeDaPessoa;
        int idadeDaPessoa;

        String fileAndLocation = "C:\\Users\\newma\\Desktop\\ficheiro.bin";   // Destino onde sera guardado o file binario

        // MENUS
        // Continuar a perguntar que opção o utilizador quer fazer até que o mesmo introduza uma opção valida
        do
        {
            // Desenha o MENU PRINCIPAL
            desenhaMenu();
            System.out.println("");

            // Pede o numero da opção e guarda-o em string
            desenhaChar(10, ' ');
            System.out.print("Introduza o numero da opção que pretende escolher: ");
            escolha = scan.nextLine();

            // Navega pelo menu dependendo do input do utilizador
            switch (escolha)
            {
                // recolhe os dados de uma pessoa, para um objeto Pessoa e adiciona a collection ou array de objetos
                case "1":
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");

                    System.out.println("Introduza o nome da pessoa");
                    nomeDaPessoa = scan.nextLine();

                    System.out.println("");

                    System.out.println("Introduza a idade da pessoa");
                    idadeDaPessoa = scan.nextInt();

                    Pessoa pessoa = new Pessoa(nomeDaPessoa, idadeDaPessoa);

                    listaDePessoas.add(pessoa);

                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    break;

                // mostra os dados da collection ou array no ecra
                case "2":
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");

                    // Para cada objeto Pessoa dentro da lista, imprime os seus atributos
                    for (Pessoa pessoaAluno : listaDePessoas){
                        System.out.println("Nome: " + pessoaAluno.getNome());
                        System.out.println("Idade: " + pessoaAluno.getIdade());
                        System.out.println("");
                    }

                    readKey = scan.nextLine();
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    break;

                // Save: guarda a collection ou array numa file binaria
                case "3":
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");

                    Serializar(fileAndLocation, listaDePessoas);

                    System.out.println("");
                    readKey = scan.nextLine();
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    break;

                // load: lê a collection ou array da file binaria e lê no ecra
                // Save: guarda a collection ou array numa file binaria
                case "4":
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");

                    ArrayList<Pessoa> listaDePessoas2 = (ArrayList<Pessoa>) Desserializar(fileAndLocation);

                    // Para cada objeto Pessoa dentro da lista, imprime os seus atributos
                    for (Pessoa pessoaAluno : listaDePessoas2){
                        System.out.println("Nome: " + pessoaAluno.getNome());
                        System.out.println("Idade: " + pessoaAluno.getIdade());
                        System.out.println("");
                    }

                    readKey = scan.nextLine();
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    break;

                // SAIR
                case "0":
                    i = 1;
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    break;

                default:
                    System.out.println("");
                    System.out.println("");
                    System.out.println("");
                    break;
                // fim do switch de navegaçao
            }
        } while (i == 0);


        // FIM MAIN
    }

    /** Metodo para serializar
     * Ao ter no parametro o tipo Object, permite receber ali qualquer objeto
     * @param fileLocation string com a localização e nome do file onde sera gravado o objeto
     * @param obj Objeto a serualizar (Object é o topo da hierarquia dos objetos nas POO)
     */
    public static void Serializar(String fileLocation, Object obj) {
        // Tentativa de serializar. Caso ocorra uma exceção, é apanhada nos catch
        try{
            FileOutputStream fileout = new FileOutputStream(fileLocation);  // Cria uma stream para escrita no file
            ObjectOutputStream serialize = new ObjectOutputStream(fileout); // Cria o objeto de serialização (output)

            serialize.writeObject(obj);                                     // serializa uma Pessoa para o file
            serialize.close();                                              // fecha o objeto de serialização
            fileout.close();                                                // fecha o acesso a file
            System.out.println("Serializado para " + fileLocation);
        }
        catch(IOException e){
            System.out.println("ERRO: Impossivel criar ou abrir o ficheiro!");
            e.printStackTrace();      // imprime o erro tecnico
        }
        catch(Exception e) {
            System.out.println("ERRO: Ocorreu um erro inesperado: ");
            e.printStackTrace();    // imprime o erro tecnico
        }
    }

    /** Metodo para deserializar
     * recebe a string da localização e o nome do file que recebe o obj e o obj a desserializar
     * @param fileLocation  string com a localização e o nome da file a ser criada
     * @return obj objeto generico (topo da hierarquia dos objetos - tem que ser convertido com um CAST)
     */
    public static Object Desserializar(String fileLocation){
        // Tentativa de desSerializar. Caso ocorra uma Exceção, é apanhada nos catch
        try{
            FileInputStream fileIn = new FileInputStream(fileLocation);                 // Cria uma stream para a escrita no file
            ObjectInputStream deserialize = new ObjectInputStream(fileIn);              // Cria o obj de deserialização
            Object obj = deserialize.readObject();                                      // .readObject() extrai o obj que esta no file mas na sua forma generica: Object

            deserialize.close();                                                        // fecho dos objs file stream
            fileIn.close();

            // Neste momento o objeto generico ira ser devolvido a quem chamou a função
            // Será nesse momento, fora da função, que este objeto devera ser convertido para a sua forma
            return obj;
        }
        catch(IOException e){
            System.out.println("ERRO: Impossivel aceder ao ficheiro");
            e.printStackTrace();        // imprime o erro tenico
            return null;                // se erro, devolve um objeto null
        }
        catch(ClassNotFoundException e){
            System.out.println("ERRO: Objeto não foi encontrado no ficheiro");
            e.printStackTrace();        // imprime o erro tenico
            return null;
        }
        catch(Exception e){
            System.out.println("ERRO: Ocorreu um eror inesperado");
            e.printStackTrace();        // imprime o erro tenico
            return null;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Menu
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /// <summary>
    /// Número de astericos e tipo de carater que pretendemos utilizar para desenhar o Menu
    /// </summary>
    /// <param name="contador">Número de asteriscos</param>
    /// <param name="carater">Carater que pretendemos utilizar para desenhar o Menu</param>

    static void desenhaChar(int contador, char carater)
    {
        for (int i = 1; i <= contador; i++)
        {
            System.out.print(carater);
        }
    }

    /// <summary>
    /// Desenho e Opções do Menu
    /// </summary>
    static void desenhaMenu()
    {

        String menu = "Menu Principal";
        String opcao1 = "Adicionar uma pessoa";
        String opcao2 = "Mostrar as pessoas";
        String opcao3 = "Guardar numa file binária";
        String opcao4 = "Ler da file binária";
        String opcao5 = "Sair";

        int conta;

        // Cabeçalho do Menu
        desenhaChar(35, ' ');
        desenhaChar(50, '*');
        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(2, '*');
        desenhaChar(16, ' ');
        conta = menu.length();
        System.out.print(menu);
        desenhaChar(30 - conta, ' ');
        desenhaChar(2, '*');
        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(50, '*');

        // Linhas Laterais
        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(1, '*');
        desenhaChar(48, ' ');
        desenhaChar(1, '*');

        // Linhas Laterais e 1 ª opção do Menu
        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(1, '*');
        desenhaChar(5, ' ');
        System.out.print(" 1 - ");
        conta = opcao1.length();
        System.out.print(opcao1);
        desenhaChar(38 - conta, ' ');
        desenhaChar(1, '*');

        // Linhas Laterais
        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(1, '*');
        desenhaChar(48, ' ');
        desenhaChar(1, '*');

        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(1, '*');
        desenhaChar(48, ' ');
        desenhaChar(1, '*');

        // Linhas Laterais e 2 ª opção do Menu
        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(1, '*');
        desenhaChar(5, ' ');
        System.out.print(" 2 - ");
        conta = opcao2.length();
        System.out.print(opcao2);
        desenhaChar(38 - conta, ' ');
        desenhaChar(1, '*');

        // Linhas Laterais
        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(1, '*');
        desenhaChar(48, ' ');
        desenhaChar(1, '*');

        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(1, '*');
        desenhaChar(48, ' ');
        desenhaChar(1, '*');

        // Linhas Laterais e 3 ª opção do Menu
        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(1, '*');
        desenhaChar(5, ' ');
        System.out.print(" 3 - ");
        conta = opcao3.length();
        System.out.print(opcao3);
        desenhaChar(38 - conta, ' ');
        desenhaChar(1, '*');

        // Linhas Laterais
        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(1, '*');
        desenhaChar(48, ' ');
        desenhaChar(1, '*');

        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(1, '*');
        desenhaChar(48, ' ');
        desenhaChar(1, '*');

        // Linhas Laterais e 4 ª opção do Menu
        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(1, '*');
        desenhaChar(5, ' ');
        System.out.print(" 4 - ");
        conta = opcao4.length();
        System.out.print(opcao4);
        desenhaChar(38 - conta, ' ');
        desenhaChar(1, '*');

        // Linhas Laterais
        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(1, '*');
        desenhaChar(48, ' ');
        desenhaChar(1, '*');

        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(1, '*');
        desenhaChar(48, ' ');
        desenhaChar(1, '*');

        // Linhas Laterais e 5 ª opção do Menu
        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(1, '*');
        desenhaChar(5, ' ');
        System.out.print(" 0 - ");
        conta = opcao5.length();
        System.out.print(opcao5);
        desenhaChar(38 - conta, ' ');
        desenhaChar(1, '*');

        // Linhas Laterais
        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(1, '*');
        desenhaChar(48, ' ');
        desenhaChar(1, '*');

        // Linha de baixo do Menu
        System.out.println(" ");
        desenhaChar(35, ' ');
        desenhaChar(50, '*');
        System.out.println(" ");
        System.out.println(" ");
    }
}






