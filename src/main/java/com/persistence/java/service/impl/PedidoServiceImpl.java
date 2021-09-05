package com.persistence.java.service.impl;

import com.persistence.java.entity.Pedido;
import com.persistence.java.entity.Produto;
import com.persistence.java.repository.PedidoRepository;
import com.persistence.java.service.PedidoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository PedidoRepository;

    @Override
    public Set<Produto> findProductsById(Long id) {
        final Pedido Pedido = PedidoRepository.findOne(id);
        return Pedido.getProdutos();
    }

    @Override
    public Pedido save(String user) {
        return PedidoRepository.save(new Pedido(user));
    }

    @Override
    public void saveProducts(Pedido createPedido, List<Produto> produtos) {
        for (Produto produto : produtos) {
            createPedido.getProdutos().add(produto);
        }
    }

    @Override
    public void delete(Long id) {
        PedidoRepository.delete(id);
    }
}