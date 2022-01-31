package de.htwberlin.kartenService;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KartenRepository extends CrudRepository<Karte, Long> {

}
