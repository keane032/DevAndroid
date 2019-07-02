package br.ufc.quixada.dadm.variastelas;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.dadm.variastelas.database.AppDataBase;
import br.ufc.quixada.dadm.variastelas.models.Contato;
import br.ufc.quixada.dadm.variastelas.transactions.Constants;

public class MainActivity extends AppCompatActivity {

    int selected = -1;
    ArrayList<Contato> listaContatos = new ArrayList<>();
    //ArrayAdapter adapter;
    ExpandableListAdapter adapter;
    //ListView listViewContatos;
    ExpandableListView listViewContatos;

    AppDataBase database;

    private DatabaseReference mDatabase;

    private static final String CONTACTS_FILE = "br.ufc.quixada.dadm.variastelas.contacts_file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = AppDataBase.getAppDatabase(this);
        this.initialize();

    }

    public void initialize(){

//        mDatabase = FirebaseDatabase.getInstance().getReference().child("contatos");
//        listaContatos = new ArrayList<br.ufc.quixada.dadm.variastelas.models.Contato>();
//        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                listaContatos.clear();
//
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    br.ufc.quixada.dadm.variastelas.models.Contato contato = postSnapshot.getValue(br.ufc.quixada.dadm.variastelas.models.Contato.class);
//                    listaContatos.add(contato);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        listaContatos.clear();
        for (Contato contato : database.contatoDAO().getAll()) {
            Log.d("qweqweq",contato.getName());
            listaContatos.add(contato);
        }

            adapter = new ExpandableListAdapter(this, listaContatos);
            listViewContatos = (ExpandableListView) findViewById(R.id.expandableListView);
            listViewContatos.setAdapter(adapter);
            listViewContatos.setSelector(android.R.color.holo_blue_light);

            listViewContatos.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v,
                                            int groupPosition, long id) {
                    selected = groupPosition;
                    return false;
                }
            });

            adapter.notifyDataSetChanged();

    }


    @Override
    protected void onPause() {

        super.onPause();

        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString( CONTACTS_FILE, exportContactList() );
        editor.apply();
    }

    private String exportContactList(){
        String export = "";
        for( br.ufc.quixada.dadm.variastelas.models.Contato contato: listaContatos ){
            export += contato.getName() + "_";
        }
        return export;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_main_activity, menu );
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected( MenuItem item ) {

        switch(item.getItemId())
        {
            case R.id.add:
                clicarAdicionar();
                break;
            case R.id.edit:
                clicarEditar();
                break;
            case R.id.delete:
                apagarItemLista();
                break;
            case R.id.settings:
                break;
            case R.id.about:
                break;
        }
        return true;
    }

    private void apagarItemLista(){


        if( listaContatos.size() > 0 ){
            br.ufc.quixada.dadm.variastelas.models.Contato contato = listaContatos.get(selected);
           listaContatos.remove( selected );

//            mDatabase = FirebaseDatabase.getInstance().getReference().child("contatos")
//            .child(contato.getUid()+"");
//            mDatabase.removeValue();

            database.contatoDAO().delete(contato);

            adapter.notifyDataSetChanged();
        } else {
            selected = -1;
        }

    }

    public void clicarAdicionar(){
        Intent intent = new Intent( this, ContactActivity.class );
        startActivityForResult( intent, Constants.REQUEST_ADD );
    }

    public void clicarEditar(){

        Intent intent = new Intent( this, ContactActivity.class );

        Contato contato = listaContatos.get(selected);

        Log.d("wqewq",String.valueOf(contato.getUid()));

        intent.putExtra( "id", contato.getUid());
        intent.putExtra( "nome", contato.getName() );
        intent.putExtra( "telefone", contato.getPhone() );
        intent.putExtra( "endereco", contato.getEnde() );

        startActivityForResult( intent, Constants.REQUEST_EDIT );
    }


    @Override
    protected void onActivityResult( int requestCode, int resultCode, @Nullable Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);

        this.initialize();

    }








}
