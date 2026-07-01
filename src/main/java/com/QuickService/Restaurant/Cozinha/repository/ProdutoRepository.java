package com.QuickService.Restaurant.Cozinha.repository;

import com.QuickService.Restaurant.Cozinha.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
