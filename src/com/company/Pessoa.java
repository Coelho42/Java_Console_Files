package com.company;

public class Pessoa implements java.io.Serializable {

    //region Atributos

    private String nome;
    private int idade;

    //endregion

    //region Construtores

    /**
     * Construtor default
     * Nada devolve
     * Nada recebe
     * Se não for declarado pelo programador, o java cria-o por defeito
     * É por isso que podemos criar objetos das classes sem declarar um unico construtor -> Pessoa P = new Pessoa();
     */
    public Pessoa() {

    }

    /**
     * Construtor que recebe a string para o atributo nome
     * @param name nome da pessoa
     */
    public Pessoa(String name){
        // com "nome" nos parametros -> this.nome = nome;
        // ou com name nos parametros

        nome = name;
    }

    /**
     * Construtor que recebe a string para o atributo nome
     * @param name nome da pessoa
     * @param age idade da pessoa
     */
    public Pessoa(String name, int age){
        // com "nome" nos parametros -> this.nome = nome;
        // ou com name nos parametros

        nome = name;
        idade = age;
    }

    //endregion

    //region Metodos
    //endregion

    //endregion

    //region Getters & Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    //endregion

}
