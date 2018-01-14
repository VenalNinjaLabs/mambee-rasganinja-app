package ninja.rasga.mambeerasganinja;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView container;
    private Adaptador adaptador;
    private List<ModeloRasgada> dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RasgaActivity.class));
            }
        });

        dados = new ArrayList<>();

        dados.add(new ModeloRasgada("Fulano de tal",
                "Picos",
                "IFPI",
                "Fulano de tal tava andando de bicicleta e levou um tombo",
                10,
                5,
                Calendar.getInstance().getTime()));
        dados.add(new ModeloRasgada("Fulano de tal 2",
                "Picos",
                "IFPI",
                "Fulano de tal tava andando de bicicleta e levou um tombo 2",
                10,
                5,
                Calendar.getInstance().getTime()));
        dados.add(new ModeloRasgada("Fulano de tal 3",
                "Picos",
                "IFPI",
                "Fulano de tal tava andando de bicicleta e levou um tombo 3",
                10,
                5,
                Calendar.getInstance().getTime()));

        adaptador = new Adaptador(this,dados);

        container = findViewById(R.id.container);
        container.setNestedScrollingEnabled(false);
        container.setHasFixedSize(true);
        container.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        container.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
