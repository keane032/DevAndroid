package br.ufc.quixada.dadm.variastelas;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.ufc.quixada.dadm.variastelas.database.AppDataBase;
import br.ufc.quixada.dadm.variastelas.models.Contato;
import br.ufc.quixada.dadm.variastelas.transactions.Constants;


public class ContactActivity extends AppCompatActivity {

    EditText edtNome;
    EditText edtTel;
    EditText edtEnd;

    AppDataBase database;

    boolean edit;
    int idContatoEditar = -1;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        edtNome = findViewById( R.id.editTextNome );
        edtTel = findViewById( R.id.editTextTel );
        edtEnd = findViewById( R.id.editTextEnd );

        edit = false;

        if( getIntent().getExtras() != null ){

            String nome = ( String )getIntent().getExtras().get( "nome" );
            String telefone = ( String )getIntent().getExtras().get( "telefone" );
            String endereco = ( String )getIntent().getExtras().get( "endereco" );
            idContatoEditar = (int)getIntent().getExtras().get( "id" );

            edtNome.setText( nome );
            edtTel.setText( telefone );
            edtEnd.setText( endereco );

            edit = true;

        }

    }

    public void cancelar( View view ){
        setResult( Constants.RESULT_CANCEL );
        finish();
    }

    public void adicionar(View view){
        Intent intent = new Intent();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("contatos");


        String nome = edtNome.getText().toString();
        String telefone = edtTel.getText().toString();
        String endereco = edtEnd.getText().toString();


        if (idContatoEditar != -1) {
//            Contato contato = new Contato(nome,telefone,endereco);
//            contato.setId(idContatoEditar);
//            mDatabase.child(String.valueOf(idContatoEditar)).setValue(contato);

            Contato contato1 =
                    new Contato(nome,telefone,endereco);
            contato1.setUid(this.idContatoEditar);
            Log.d("wqewq",String.valueOf(contato1.getUid()));
            Log.d("wqewq",contato1.getName());
            Log.d("wqewq",contato1.getEnde());
            Log.d("wqewq",contato1.getPhone());

            database.contatoDAO().update(contato1);

            Toast.makeText(getApplicationContext(), "Contato Atualizado", Toast.LENGTH_SHORT).show();

        }else {
//            Contato contato = new Contato(nome,telefone,endereco);
//            mDatabase.child(String.valueOf(contato.getId())).setValue(contato);

            database = AppDataBase.getAppDatabase(this);

            br.ufc.quixada.dadm.variastelas.models.Contato contato1 =
                    new br.ufc.quixada.dadm.variastelas.models.Contato(nome,telefone,endereco);

            database.contatoDAO().insertAll(contato1);

            Log.d("wqewqeqweq",contato1.getName());


            Toast.makeText(getApplicationContext(), "Contato Adicionado", Toast.LENGTH_SHORT).show();
        }

        setResult( Constants.RESULT_ADD, intent );
        finish();
    }
}
