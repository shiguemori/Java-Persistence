package com.persistence.java.service;

import com.persistence.java.entity.Pedido;
import com.persistence.java.entity.Produto;

import java.util.List;
import java.util.Set;

public interface PedidoService {

    Set<Produto> findProductsById(Long id);

    Pedido save(String user);

    void saveProducts(Pedido createPedido, List<Produto> produtos);

    void delete(Long id);
}

