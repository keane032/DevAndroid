package br.ufc.quixada.dadm.variastelas.modelsDAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.ufc.quixada.dadm.variastelas.models.Contato;

@Dao
public interface ContatoDAO {

    @Query("SELECT * FROM contato")
    List<Contato> getAll();

    @Update
    void update(Contato contato);

    @Query("SELECT * FROM contato WHERE uid IN (:contatoIds)")
    List<Contato> loadAllByIds(int[] contatoIds);

    @Query("SELECT * FROM contato WHERE name LIKE :name AND " +
            "phone LIKE :phone LIMIT 1")
    Contato findByName(String name, String phone);

    @Insert
    void insertAll(Contato... contato);

    @Delete
    void delete(Contato contato);

}
