package ninja.rasga.mambeerasganinja;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class DetalheActivity extends AppCompatActivity {

    private ModeloRasgada rasgadaDetalhada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        rasgadaDetalhada = new ModeloRasgada("Fulano de tal",
                "Picos",
                "IFPI",
                "Fulano de tal tava andando de bicicleta e levou um tombo",
                10,
                5,
                Calendar.getInstance().getTime());

        TextView nome = findViewById(R.id.nome);
        TextView cidade = findViewById(R.id.cidade);
        TextView referencia = findViewById(R.id.referencia);
        TextView comentario = findViewById(R.id.comentario);
        TextView avatar = findViewById(R.id.avatar);
        CardView thumbUp = findViewById(R.id.thumbUp);
        CardView thumbDown = findViewById(R.id.thumbDown);

        thumbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rasgadaDetalhada.setVotosUp(rasgadaDetalhada.getVotosUp()+1);
                Toast.makeText(DetalheActivity.this, "Voto positivo: "+rasgadaDetalhada.getVotosUp(), Toast.LENGTH_SHORT).show();
            }
        });

        thumbDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rasgadaDetalhada.setVotosDown(rasgadaDetalhada.getVotosDown()+1);
                Toast.makeText(DetalheActivity.this, "Voto negativo: "+rasgadaDetalhada.getVotosDown(), Toast.LENGTH_SHORT).show();
            }
        });

        nome.setText(rasgadaDetalhada.getNome());
        cidade.setText(rasgadaDetalhada.getCidade());
        referencia.setText(rasgadaDetalhada.getReferencia());
        comentario.setText(rasgadaDetalhada.getComentario());
        avatar.setText(String.valueOf(rasgadaDetalhada.getNome().toUpperCase().charAt(0)));
    }
}
