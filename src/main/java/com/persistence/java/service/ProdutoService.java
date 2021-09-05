package com.persistence.java.service;

import com.persistence.java.entity.Produto;
import org.springframework.data.domain.Page;

public interface ProdutoService {

    Produto findProductById(Long id);

    Page<Produto> getAllProducts(Integer page, Integer size);

    Produto saveProduct(Produto Produto);

    void updateProduct(Produto Produto);

    void deleteProduct(Long id);

}
