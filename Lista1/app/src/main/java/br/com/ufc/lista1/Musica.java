package br.com.ufc.lista1;

import android.app.Activity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;


public class Musica extends Activity {

    MediaPlayer player;

    public static final String ACAO_EXIBIR_MUSICAS =
            "lista1.ACAO_EXIBIR_MUSICAS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musica);
        player = MediaPlayer.create(this,R.raw.song);

        GridView gridView = (GridView)findViewById(R.id.grid);

        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("Max");
        your_array_list.add("Italo");
        your_array_list.add("Rodrigo");
        your_array_list.add("Samuel");
        your_array_list.add("Roy");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list );

        gridView.setAdapter(arrayAdapter);
    }


    public void playSong(View view){
        player.start();
    }

    public void pause(View view){
        player.pause();
    }


}
