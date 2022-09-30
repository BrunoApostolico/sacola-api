package me.dio.sacola.repository;

import me.dio.sacola.model.Bag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BagRepository extends JpaRepository<Bag,Long> {
}
