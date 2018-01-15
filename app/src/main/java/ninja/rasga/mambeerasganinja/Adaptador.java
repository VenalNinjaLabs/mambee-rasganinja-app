package ninja.rasga.mambeerasganinja;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by magdiel on 14/01/18.
 */

public class Adaptador extends RecyclerView.Adapter{

    private Context context;
    private List<ModeloRasgada> dados;

    private boolean isLoading;
    private int visibleThreshold = 10;
    private int lastVisibleItem, totalItemCount;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private OnLoadMoreListener onLoadMoreListener;
    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    public Adaptador(Context context, List<ModeloRasgada> dados, RecyclerView recyclerView){
        this.context = context;
        this.dados = dados;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ModeloRasgada rasgada = dados.get(position);
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).bind(rasgada,context);
            ((ViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,DetalheActivity.class);
                    intent.putExtra("id",rasgada.id);
                    context.startActivity(intent);
                }
            });
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return dados == null ? 0 : dados.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dados.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.cardview_rasgada, parent, false);
            return new ViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView avatar;
        TextView nome;
        TextView cidade;
        TextView referencia;
        TextView comentario;
        TextView votosUp;
        TextView votosDown;
        TextView data;
        CardView cardView;
        CardView ball;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView;
            avatar = itemView.findViewById(R.id.card_avatar);
            nome = itemView.findViewById(R.id.card_nome);
            cidade = itemView.findViewById(R.id.card_cidade);
            referencia = itemView.findViewById(R.id.card_referencia);
            comentario = itemView.findViewById(R.id.card_comentario);
            votosUp = itemView.findViewById(R.id.card_up_votes);
            votosDown = itemView.findViewById(R.id.card_down_votes);
            data = itemView.findViewById(R.id.card_data);
            ball = itemView.findViewById(R.id.ball);
        }

        public void bind(ModeloRasgada modeloRasgada,Context context){
            avatar.setText(String.valueOf(modeloRasgada.getNome().toUpperCase().charAt(0)));
            nome.setText(modeloRasgada.getNome());
            cidade.setText(modeloRasgada.getCidade());
            referencia.setText(modeloRasgada.getReferencia());
            comentario.setText(modeloRasgada.getComentario());
            votosUp.setText(String.valueOf(modeloRasgada.getVotosUp()));
            votosDown.setText(String.valueOf(modeloRasgada.getVotosDown()));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                if(modeloRasgada.getVotosDown() > modeloRasgada.getVotosUp()){
                    ball.setCardBackgroundColor(context.getResources().getColor(R.color.holo_red_light));
                }else if(modeloRasgada.getVotosDown() < modeloRasgada.getVotosUp()){
                    ball.setCardBackgroundColor(context.getResources().getColor(R.color.holo_green_light));
                }else{
                    ball.setCardBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                }
            }
            data.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(modeloRasgada.getData()));
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
