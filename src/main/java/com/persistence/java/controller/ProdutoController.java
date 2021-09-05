package com.persistence.java.controller;

import com.persistence.java.entity.Produto;
import com.persistence.java.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/produto")
@Api(tags = {"Produtos"})
public class ProdutoController extends AbstractRestHandler {

    private final ProdutoService produtoService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a single Product.", notes = "You have to provide a valid Product ID.")
    public Produto findOneProduct(@ApiParam(value = "The ID of the Product.", required = true) @PathVariable("id") Long id) {
        return produtoService.findProductById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get a paginated list of all Products.", notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
    public @ResponseBody
    Page<Produto> getAllProducts(@ApiParam(value = "The page number (zero-based)", required = true)
                                 @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                 @ApiParam(value = "Tha page size", required = true)
                                 @RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        return produtoService.getAllProducts(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a Product resource.", notes = "Returns the URL of the new resource in the Location header.")
    public void createProduct(@RequestBody Produto Produto,
                              HttpServletRequest request, HttpServletResponse response) {
        final Produto createProduto = produtoService.saveProduct(Produto);
        response.setHeader("Location", request.getRequestURL().append("/").append(createProduto.getId()).toString());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a Product resource.", notes = "You have to provide a valid Product ID in the URL and in the payload. The ID attribute can not be updated.")
    public void modifyProduct(@ApiParam(value = "The ID of the existing Product resource.", required = true) @PathVariable("id") Long id, @RequestBody Produto Produto) {
        checkResourceFound(this.produtoService.findProductById(id));
        produtoService.updateProduct(Produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a Product resource.", notes = "You have to provide a valid Product ID in the URL. Once deleted the resource can not be recovered.")
    public void deleteProduct(@ApiParam(value = "The ID of the existing Product resource.", required = true) @PathVariable("id") Long id) {
        checkResourceFound(this.produtoService.findProductById(id));
        produtoService.deleteProduct(id);
    }

}