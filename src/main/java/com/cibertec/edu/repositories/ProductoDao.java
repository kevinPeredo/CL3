package com.cibertec.edu.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.edu.models.Producto;

@Repository
public interface ProductoDao extends CrudRepository<Producto, Long>{
	public List<Producto> findAll();

}
