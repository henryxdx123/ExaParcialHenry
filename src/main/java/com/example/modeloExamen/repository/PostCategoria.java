
package com.example.modeloExamen.repository;

import com.example.modeloExamen.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCategoria extends JpaRepository<Categoria, Long>{
    
}
