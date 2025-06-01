package com.clima.gs.gs.repository;

import com.clima.gs.gs.model.Favoritos;
import com.clima.gs.gs.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritosRepository extends JpaRepository<Favoritos, Long> {
    
    List<Favoritos> findByUsuario(User usuario);

    boolean existsByUsuarioAndCidadeIgnoreCase(User usuario, String cidade);
}
