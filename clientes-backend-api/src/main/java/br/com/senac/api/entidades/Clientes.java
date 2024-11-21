package br.com.senac.api.entidades;

import jakarta.persistence.*;

@Entity
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;

    @Column
    private String sobrenome;

    @Column(nullable = false, unique = true) //usa-se esses atributos para o email é obrigatorio e deve ser unico
    private String email;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
