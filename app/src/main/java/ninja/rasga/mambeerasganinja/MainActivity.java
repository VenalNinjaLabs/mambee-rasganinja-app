package ninja.rasga.mambeerasganinja;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ninja.rasga.mambeerasganinja.api.ServiceGenerator;
import ninja.rasga.mambeerasganinja.api.UrlService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView container;
    private Adaptador adaptador;
    private List<ModeloRasgada> dados;
    private ScrollView scrollView;
    private SwipeRefreshLayout refresh;

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

        container = findViewById(R.id.container);
        container.setNestedScrollingEnabled(false);
        container.setHasFixedSize(true);
        container.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        refresh = findViewById(R.id.refresh);
        scrollView = findViewById(R.id.scrollView);

        refresh.setOnRefreshListener(this);

        loadData(this);
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

    public void loadData(final Context context){
        refresh.setRefreshing(true);
        ServiceGenerator.createService(UrlService.class).listarRasgadas().enqueue(new Callback<List<ModeloRasgada>>() {
            @Override
            public void onResponse(Call<List<ModeloRasgada>> call, Response<List<ModeloRasgada>> response) {
                dados = response.body();
                adaptador = new Adaptador(context,dados);
                container.setAdapter(adaptador);
                refresh.setRefreshing(false);
                scrollView.smoothScrollTo(0,0);
            }

            @Override
            public void onFailure(Call<List<ModeloRasgada>> call, Throwable t) {
                refresh.setRefreshing(false);
                Toast.makeText(context, "Tente novamente.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        loadData(this);
    }
}
