package ninja.rasga.mambeerasganinja;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RasgaActivity extends AppCompatActivity {

    private ModeloRasgada novaRasgada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rasga);
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        final EditText nome = findViewById(R.id.nome);
        final EditText comentario = findViewById(R.id.comentario);
        final EditText referencia = findViewById(R.id.referencia);
        final EditText cidade = findViewById(R.id.cidade);
        Button salvar = findViewById(R.id.button);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                novaRasgada.setNome(nome.getText().toString());
                novaRasgada.setCidade(cidade.getText().toString());
                novaRasgada.setComentario(comentario.getText().toString());
                novaRasgada.setReferencia(referencia.getText().toString());

                Toast.makeText(RasgaActivity.this, "Salvando no banco de dados...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
