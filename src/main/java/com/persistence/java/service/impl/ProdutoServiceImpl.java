package com.persistence.java.service.impl;

import com.persistence.java.entity.Produto;
import com.persistence.java.exception.ResourceNotFoundException;
import com.persistence.java.repository.ProdutoRepository;
import com.persistence.java.service.ProdutoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository ProdutoRepository;
    private final RedisTemplate<String, Produto> redisTemplate;

    @Override
    public Produto findProductById(Long id) {
        final String key = "produto_" + id;
        final ValueOperations<String, Produto> operations = redisTemplate.opsForValue();
        final boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            final Produto Produto = operations.get(key);
            return Produto;
        }
        final Optional<Produto> Product = Optional.ofNullable(ProdutoRepository.findOne(id));
        if (Product.isPresent()) {
            operations.set(key, Product.get(), 10, TimeUnit.SECONDS);
            return Product.get();
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public Page<Produto> getAllProducts(Integer page, Integer size) {
        return ProdutoRepository.findAll(new PageRequest(page, size));
    }

    @Override
    public Produto saveProduct(Produto Produto) {
        return ProdutoRepository.save(Produto);
    }

    @Override
    public void updateProduct(Produto Produto) {
        final String key = "produto_" + Produto.getId();
        final boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
        }
        ProdutoRepository.save(Produto);
    }

    @Override
    public void deleteProduct(Long id) {
        final String key = "produto_" + id;
        final boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
        }
        final Optional<Produto> Product = Optional.ofNullable(ProdutoRepository.findOne(id));
        if (Product.isPresent()) {
            ProdutoRepository.delete(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }

}