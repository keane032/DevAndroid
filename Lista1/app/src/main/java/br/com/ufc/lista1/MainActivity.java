package br.com.ufc.lista1;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.PopupMenu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    EditText ed;
    ToggleButton toggle;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggle = (ToggleButton)findViewById(R.id.toggBtn);

        ed  = (EditText)findViewById(R.id.ed);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    ed.setText("ativado");
                    Toast toast = Toast.makeText(MainActivity.this,"ativado", Toast.LENGTH_SHORT);
                    toast.show();

                } else {

                    ed.setText("desativado");
                    Toast toast = Toast.makeText(MainActivity.this, "desativado", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, button1);
                popup.getMenuInflater()
                        .inflate(R.menu.popup, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                MainActivity.this,
                                "You Clicked : " + item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show();
            }
        });

    }



    public void listaContatos(View view){
        Intent intent = new Intent(Lista_Contatos.ACAO_EXIBIR_CONTATOS);
        startActivity(intent);
    }

    public void listarMusica(View view){
        Intent intent = new Intent(Musica.ACAO_EXIBIR_MUSICAS);
        startActivity(intent);
    }

    public void cadastro(View view){
        Intent intent = new Intent(this,Cadastro.class);
        startActivity(intent);
    }

    public void fragment(View view){
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }



}
