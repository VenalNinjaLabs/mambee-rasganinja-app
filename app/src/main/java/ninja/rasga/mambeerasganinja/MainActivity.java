package ninja.rasga.mambeerasganinja;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
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

import ninja.rasga.mambeerasganinja.api.RespostaListagem;
import ninja.rasga.mambeerasganinja.api.ServiceGenerator;
import ninja.rasga.mambeerasganinja.api.UrlService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView container;
    private Adaptador adaptador;
    private List<ModeloRasgada> dados;
    private NestedScrollView scrollView;
    private SwipeRefreshLayout refresh;

    private int paginaAtual;
    private int proximaPagina;

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

        paginaAtual = 0;
        proximaPagina = 0;

        container = findViewById(R.id.container);
        container.setNestedScrollingEnabled(false);
        container.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        refresh = findViewById(R.id.refresh);
        scrollView = findViewById(R.id.scrollView);

        refresh.setOnRefreshListener(this);

        loadData(this);
    }

    public void loadData(final Context context){
        refresh.setRefreshing(true);
        ServiceGenerator.createService(UrlService.class).listarRasgadas().enqueue(new Callback<RespostaListagem>() {
            @Override
            public void onResponse(Call<RespostaListagem> call, Response<RespostaListagem> response) {
                paginaAtual = 1;
                proximaPagina = response.body().nextPage;
                dados = response.body().rasgadas;
                adaptador = new Adaptador(context,dados,container);
                container.setAdapter(adaptador);
                container.invalidate();
                refresh.setRefreshing(false);
                scrollView.smoothScrollTo(0,0);
                adaptador.setOnLoadMoreListener(new Adaptador.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        dados.add(null);
                        adaptador.notifyItemInserted(dados.size()-1);
                        nextPage();
                    }
                });
                if(proximaPagina == paginaAtual){
                    adaptador.setLoaded();
                }
            }

            @Override
            public void onFailure(Call<RespostaListagem> call, Throwable t) {
                refresh.setRefreshing(false);
                Toast.makeText(context, "Tente novamente.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void nextPage() {
        if(proximaPagina == paginaAtual){
            dados.remove(dados.size() - 1);
            adaptador.notifyItemRemoved(dados.size());
            adaptador.setLoaded();
        }else {
            ServiceGenerator.createService(UrlService.class).listarRasgadas(proximaPagina).enqueue(new Callback<RespostaListagem>() {
                @Override
                public void onResponse(Call<RespostaListagem> call, Response<RespostaListagem> response) {
                    paginaAtual=proximaPagina;
                    proximaPagina = response.body().nextPage;
                    dados.remove(dados.size() - 1);
                    adaptador.notifyItemRemoved(dados.size());
                    dados.addAll(response.body().rasgadas);
                    adaptador.notifyDataSetChanged();
                    adaptador.setLoaded();
                }

                @Override
                public void onFailure(Call<RespostaListagem> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Algo errado aconteceu.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onRefresh() {
        loadData(this);
    }
}
