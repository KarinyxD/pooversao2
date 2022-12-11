package Trabalho.MVC.Modelo;

import java.util.LinkedList;

public class Venda extends Entidade {

    private LinkedList<Ingresso> IngressosComprados = new LinkedList<>();
    private Cliente clienteDaVenda;

    public Venda(){
        super();
        clienteDaVenda = null;
    }

    public Venda(Cliente clientedavenda, Ingresso novoingresso){
        this.clienteDaVenda = clientedavenda;
        this.IngressosComprados.add(novoingresso);
    }

    public LinkedList<Ingresso> getIngressosComprados(){ return IngressosComprados; }
    public Cliente getClienteDaVenda(){ return clienteDaVenda; }

    public String toString(){
        return String.format("Venda ID " + getId() + ":" + "\n\t Ingressos comprados: " +
                getIngressosComprados() + "\n\t Cliente: " + getClienteDaVenda().getNome() + "; CPF: " +
                getClienteDaVenda().getCpf());
    }

}
