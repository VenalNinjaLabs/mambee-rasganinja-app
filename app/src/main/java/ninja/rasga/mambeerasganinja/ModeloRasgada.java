package ninja.rasga.mambeerasganinja;

import java.util.Date;

/**
 * Created by magdiel on 13/01/18.
 */

public class ModeloRasgada {
    private String nome;
    private String cidade;
    private String referencia;
    private String comentario;
    private int votosUp;
    private int votosDown;
    private Date data;

    public  ModeloRasgada(){}

    public ModeloRasgada(String nome, String cidade, String referencia, String comentario, int votosUp, int votosDown, Date data) {
        this.nome = nome;
        this.cidade = cidade;
        this.referencia = referencia;
        this.comentario = comentario;
        this.votosUp = votosUp;
        this.votosDown = votosDown;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getVotosUp() {
        return votosUp;
    }

    public void setVotosUp(int votosUp) {
        this.votosUp = votosUp;
    }

    public int getVotosDown() {
        return votosDown;
    }

    public void setVotosDown(int votosDown) {
        this.votosDown = votosDown;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
