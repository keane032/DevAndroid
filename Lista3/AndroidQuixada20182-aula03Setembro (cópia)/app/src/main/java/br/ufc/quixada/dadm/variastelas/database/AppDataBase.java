package br.ufc.quixada.dadm.variastelas.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.ufc.quixada.dadm.variastelas.models.Contato;
import br.ufc.quixada.dadm.variastelas.modelsDAO.ContatoDAO;

@Database(entities = {Contato.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    public abstract ContatoDAO contatoDAO();

    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class,
                    "schedule-db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
