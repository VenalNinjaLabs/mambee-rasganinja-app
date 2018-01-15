package ninja.rasga.mambeerasganinja;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ninja.rasga.mambeerasganinja.api.ServiceGenerator;
import ninja.rasga.mambeerasganinja.api.UrlService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        final Button salvar = findViewById(R.id.button);

        novaRasgada = new ModeloRasgada();

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar.setEnabled(false);
                novaRasgada.setNome(nome.getText().toString());
                novaRasgada.setCidade(cidade.getText().toString());
                novaRasgada.setComentario(comentario.getText().toString());
                novaRasgada.setReferencia(referencia.getText().toString());

                ServiceGenerator.createService(UrlService.class).salvar(novaRasgada).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(RasgaActivity.this, "Salvo.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RasgaActivity.this,MainActivity.class));
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(RasgaActivity.this, "Tente novamente.", Toast.LENGTH_SHORT).show();
                        salvar.setEnabled(true);
                    }
                });
            }
        });

    }
}
