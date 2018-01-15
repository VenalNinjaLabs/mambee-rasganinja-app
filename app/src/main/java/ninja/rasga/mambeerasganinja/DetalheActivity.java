package ninja.rasga.mambeerasganinja;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import ninja.rasga.mambeerasganinja.api.ServiceGenerator;
import ninja.rasga.mambeerasganinja.api.UrlService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalheActivity extends AppCompatActivity {

    private ModeloRasgada rasgadaDetalhada;
    private String id;

    TextView nome;
    TextView cidade;
    TextView referencia;
    TextView comentario;
    TextView avatar;
    CardView thumbUp;
    CardView thumbDown;
    CardView ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        id = "";

        if(intent.getExtras() != null){
            id = intent.getStringExtra("id");
        }

        nome = findViewById(R.id.nome);
        cidade = findViewById(R.id.cidade);
        referencia = findViewById(R.id.referencia);
        comentario = findViewById(R.id.comentario);
        avatar = findViewById(R.id.avatar);
        thumbUp = findViewById(R.id.thumbUp);
        ball = findViewById(R.id.ballAvatar);
        thumbDown = findViewById(R.id.thumbDown);

        thumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                votoPositivo();
            }
        });

        thumbDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                votoNegativo();
            }
        });

        pegarRasgada();
    }

    public void updateBall(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if(rasgadaDetalhada.getVotosDown() > rasgadaDetalhada.getVotosUp()){
                ball.setCardBackgroundColor(getResources().getColor(R.color.holo_red_light));
            }else{
                ball.setCardBackgroundColor(getResources().getColor(R.color.holo_green_light));
            }
        }
    }

    public void pegarRasgada(){
        ServiceGenerator.createService(UrlService.class).pegarRasgada(id).enqueue(new Callback<ModeloRasgada>() {
            @Override
            public void onResponse(Call<ModeloRasgada> call, Response<ModeloRasgada> response) {
                rasgadaDetalhada = response.body();

                nome.setText(rasgadaDetalhada.getNome());
                cidade.setText(rasgadaDetalhada.getCidade());
                referencia.setText(rasgadaDetalhada.getReferencia());
                comentario.setText(rasgadaDetalhada.getComentario());
                avatar.setText(String.valueOf(rasgadaDetalhada.getNome().toUpperCase().charAt(0)));
                updateBall();
            }

            @Override
            public void onFailure(Call<ModeloRasgada> call, Throwable t) {
                Toast.makeText(DetalheActivity.this, "Tente novamente.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void votoPositivo(){
        ServiceGenerator.createService(UrlService.class).votoPositivo(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                rasgadaDetalhada.setVotosUp(rasgadaDetalhada.getVotosUp()+1);
                updateBall();
                Toast.makeText(DetalheActivity.this, "Voto positivo: "+rasgadaDetalhada.getVotosUp(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(DetalheActivity.this, "Tente novamente.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void votoNegativo(){
        ServiceGenerator.createService(UrlService.class).votoNegativo(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                rasgadaDetalhada.setVotosDown(rasgadaDetalhada.getVotosDown()+1);
                updateBall();
                Toast.makeText(DetalheActivity.this, "Voto negativo: "+rasgadaDetalhada.getVotosDown(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(DetalheActivity.this, "Tente novamente.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
