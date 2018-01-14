package ninja.rasga.mambeerasganinja;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

        nome.setText(rasgadaDetalhada.getNome());
        cidade.setText(rasgadaDetalhada.getCidade());
        referencia.setText(rasgadaDetalhada.getReferencia());
        comentario.setText(rasgadaDetalhada.getComentario());
        avatar.setText(rasgadaDetalhada.getNome().toUpperCase().charAt(0));
    }
}
