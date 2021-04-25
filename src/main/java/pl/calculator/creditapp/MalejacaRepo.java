package pl.calculator.creditapp;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MalejacaRepo extends CrudRepository<MalejacaRata, Long> {

    @Query (nativeQuery = true, value = "SELECT SUM(RATA) FROM MALEJACA_RATA")
    Double calculateCalkowitaKwotaKredytu();

    @Transactional
    @Modifying
    @Query (nativeQuery = true, value = "ALTER TABLE MALEJACA_RATA ALTER COLUMN ID RESTART WITH 1")
    void resetIDColumn();

    @Query (nativeQuery = true, value = "SELECT RATA FROM MALEJACA_RATA LIMIT 1")
    Double showFirstRata();

    @Query (nativeQuery = true, value = "SELECT RATA FROM MALEJACA_RATA ORDER BY ID DESC LIMIT 1")
    Double showLastRata();
}
