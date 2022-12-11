package Trabalho.MVC.Modelo;

public class Filme extends Entidade {

     private String titulo;
     private int faixaEtaria; 
     private String genero;
     private int contagemdeIngressos;

    public Filme(){
        super();
        titulo = "";
        faixaEtaria = 0;
        genero = "";
        contagemdeIngressos = 0;
    }
    public Filme(String titulo, int faixaEtaria, String genero, int contagemdeIngressos){
        setTitulo(titulo);
        setFaixaEtaria(faixaEtaria);
        setGenero(genero);
        setContagemdeIngressos(contagemdeIngressos);
    }

    public int getContagemdeIngressos(){
        return contagemdeIngressos;
    }
    public void setContagemdeIngressos(int contagemdeingressos){
        this.contagemdeIngressos = contagemdeingressos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(int faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String toString(){
        return String.format("Filme ID(%d): \n\tTitulo: %s;\n\tFaixa Etaria: %d;\n\tGenero: %s.\n",
                getId(), getTitulo(), getFaixaEtaria(), getGenero());
    }

}
