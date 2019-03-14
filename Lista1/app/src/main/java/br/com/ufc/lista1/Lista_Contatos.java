package br.com.ufc.lista1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.AdapterView;
import android.view.View;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class Lista_Contatos extends Activity {

    public static final String ACAO_EXIBIR_CONTATOS =
            "lista1.ACAO_EXIBIR_CONTATOS";

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_contados);

        lv = (ListView) findViewById(R.id.ListaContatos);

        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("Max");
        your_array_list.add("Italo");
        your_array_list.add("Rodrigo");
        your_array_list.add("Samuel");
        your_array_list.add("Roy");


        lv.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                Toast.makeText(Lista_Contatos.this, " ok", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list );

        AutoCompleteTextView t1 = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView);


        t1.setThreshold(1);
        t1.setAdapter(arrayAdapter);

        lv.setAdapter(arrayAdapter);



    }


    public void listarMusica(View view){
        Intent intent = new Intent(Musica.ACAO_EXIBIR_MUSICAS);
        startActivity(intent);
    }

}
