package ninja.rasga.mambeerasganinja;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by magdiel on 14/01/18.
 */

public class Adaptador extends RecyclerView.Adapter {

    private Context context;
    private List<ModeloRasgada> dados;

    public Adaptador(Context context, List<ModeloRasgada> dados){
        this.context = context;
        this.dados = dados;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ModeloRasgada rasgada = dados.get(position);
        ((ViewHolder) holder).bind(rasgada,context);
        ((ViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetalheActivity.class);
                intent.putExtra("id",rasgada.id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.cardview_rasgada, parent, false);
        vh = new ViewHolder(v);
        return vh;
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
}
