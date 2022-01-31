package de.htwberlin.regelnService;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpielRepository extends CrudRepository<Spiel, Long> {

}
