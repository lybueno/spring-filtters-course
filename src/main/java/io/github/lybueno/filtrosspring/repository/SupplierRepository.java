package io.github.lybueno.filtrosspring.repository;

import io.github.lybueno.filtrosspring.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
