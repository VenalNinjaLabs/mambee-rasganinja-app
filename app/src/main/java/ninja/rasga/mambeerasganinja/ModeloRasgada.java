package ninja.rasga.mambeerasganinja;

import java.util.Date;

/**
 * Created by magdiel on 13/01/18.
 */

public class ModeloRasgada {
    public String nome;
    public String cidade;
    public String referencia;
    public String comentario;
    public int votosPositivos;
    public int votosNegativos;
    public int totalVotos;
    public Date horario;
    public String id;

    public  ModeloRasgada(){}

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
        return votosPositivos;
    }

    public void setVotosUp(int votosUp) {
        this.votosPositivos = votosUp;
    }

    public int getVotosDown() {
        return votosNegativos;
    }

    public void setVotosDown(int votosDown) {
        this.votosNegativos = votosDown;
    }

    public Date getData() {
        return horario;
    }

    public void setData(Date data) {
        this.horario = data;
    }

    public int getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(int totalVotos) {
        this.totalVotos = totalVotos;
    }
}
