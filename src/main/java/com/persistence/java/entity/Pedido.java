package com.persistence.java.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "pedidos")
@SecondaryTable(name = "pedido_produtos", pkJoinColumns = {@PrimaryKeyJoinColumn(name = "pedido_id")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public String usuario;

    public Pedido(String usuario) {
        super();
        this.usuario = usuario;
    }

    public Pedido() {
        super();
    }

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "pedido",
            joinColumns = {@JoinColumn(name = "pedido_id")},
            inverseJoinColumns = {@JoinColumn(name = "produto_id")}
    )
    private Set<Produto> produtos = new HashSet<>();

    public long getId() {
        return this.id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}