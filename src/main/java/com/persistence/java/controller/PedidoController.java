package com.persistence.java.controller;

import com.persistence.java.entity.Pedido;
import com.persistence.java.entity.Produto;
import com.persistence.java.service.PedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/pedido")
@Api(tags = {"Pedidos"})
public class PedidoController extends AbstractRestHandler {

    private final PedidoService pedidoService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Produto> findOneProduct(@ApiParam(value = "The ID of the Product.", required = true) @PathVariable("id") Long id) {
        return pedidoService.findProductsById(id);
    }

    @PostMapping("/{user}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@ApiParam(value = "The user.", required = true) @PathVariable("user") String user,
                              @RequestBody List<Produto> produtos, HttpServletRequest request, HttpServletResponse response) {
        final Pedido createPedido = pedidoService.save(user);
        pedidoService.saveProducts(createPedido, produtos);
        response.setHeader("Location", request.getRequestURL().append("/").append(
                pedidoService.findProductsById(createPedido.getId())
        ).toString());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@ApiParam(value = "O ID do pedido.", required = true) @PathVariable("id") Long id) {
        pedidoService.delete(id);
    }

}