package Trabalho.Visao;

import Trabalho.Persistencia.*;
import Trabalho.*;

import java.io.File;
import java.util.Scanner;

public class Menu2 {

    public Menu2(Scanner scn){

        String caminhoBDDClientes = System.getProperty("user.dir") + "\\PersistenciaClientes.json";
        File BDDClientes = new File(caminhoBDDClientes);
        String caminhoBDDFilmes = System.getProperty("user.dir") + "\\PersistenciaFilmes.json";
        File BDDFilmes = new File(caminhoBDDFilmes);
        String caminhoBDDVendas = System.getProperty("user.dir") + "\\PersistenciaVendas.json";
        File BDDVendas = new File(caminhoBDDVendas);
        String caminhoBDDIngressos = System.getProperty("user.dir") + "\\PersistenciaIngressos.json";
        File BDDIngressos = new File(caminhoBDDIngressos);

        boolean choiceForConstructorCliente = false;
        if(BDDClientes.exists() && BDDClientes.length() != 0) {
            choiceForConstructorCliente = true;
            System.out.println("\nObjetos do arquivo PersistenciaClientes.json foram carregados.");
        }
        boolean choiceForConstructorFilme = false;
        if(BDDFilmes.exists() && BDDFilmes.length() != 0) {
            choiceForConstructorFilme = true;
            System.out.println("\nObjetos do arquivo PersistenciaFilmes.json foram carregados.");
        }
        boolean choiceForConstructorVenda = false;
        if(BDDVendas.exists() && BDDVendas.length() != 0) {
            choiceForConstructorVenda = true;
            System.out.println("\nObjetos do arquivo PersistenciaVendas.json foram carregados.");
        }
        boolean choiceForConstructorIngressos = false;
        if(BDDIngressos.exists() && BDDIngressos.length() != 0){
            choiceForConstructorIngressos = true;
            System.out.println("\nObjetos do arquivo PersistenciaIngressos.json foram carregados.");
        }

        // Declaracao das variaveis de persistencia no formato Singleton
        ClienteP ClientePers = ClienteP.getinstancia();
        FilmeP FilmePers = FilmeP.getinstancia();
        VendaP VendaPers = VendaP.getinstancia();
        IngressoP IngressoPers = IngressoP.getinstancia();

        ClientePers.InicializarArquivo(choiceForConstructorCliente);
        FilmePers.InicializarArquivo(choiceForConstructorFilme);
        VendaPers.InicializarArquivo(choiceForConstructorVenda);
        IngressoPers.InicializarArquivo(choiceForConstructorIngressos);

        int opcaoLoopPrincipal = 1, opcaoMenuPrincipal, opcaoLoopClientes, opcaoMenuClientes;
        int opcaoLoopFilmes, opcaoMenuFilmes, opcaoLoopVenda, opcaoMenuVenda;
        int opcaoComprarOutroIngresso;

        while (opcaoLoopPrincipal != 0){
            System.out.println("\nMENU");
            System.out.println("\t1 - Para Clientes");
            System.out.println("\t2 - Para Filmes");
            System.out.println("\t3 - Para Vendas e Ingressos");
            System.out.println("\t0 - Para Voltar ao Menu Anterior");
            System.out.print("\n\tOpcao: ");
            opcaoMenuPrincipal = scn.nextInt();
            switch(opcaoMenuPrincipal){
                case 0: {
                    opcaoLoopPrincipal = 0;
                    ClientePers.AtualizarArquivo();
                    FilmePers.AtualizarArquivo();
                    VendaPers.AtualizarArquivo();
                    IngressoPers.AtualizarArquivo();
                    break;
                }

                case 1: {
                    opcaoLoopClientes = 1;
                    while (opcaoLoopClientes != 0) {
                        System.out.println("\nMENU DE CLIENTES");
                        System.out.println("\t1 - Insercao de cliente");
                        System.out.println("\t2 - Remocao de cliente");
                        System.out.println("\t0 - Para Voltar ao Menu Anterior");
                        System.out.print("\n\tOpcao: ");
                        opcaoMenuClientes = scn.nextInt();
                        switch (opcaoMenuClientes) {
                            case 0: {
                                opcaoLoopClientes = 0;
                                break;
                            }

                            case 1: {
                                Cliente clienteAux = new Cliente();
                                scn.nextLine();
                                System.out.print("\nDigite o nome do cliente: ");
                                clienteAux.setNome(scn.nextLine());
                                System.out.print("\nDigite o CPF do cliente: ");
                                String aux = scn.nextLine();
                                if (ClientePers.BuscarString(aux) != null) {
                                    System.out.println("\n" + Character.toString(100_62) +
                                            " O CPF ja foi cadastrado :/");
                                    break;
                                }
                                clienteAux.setCpf(aux);
                                System.out.print("\nDigite a idade do cliente: ");
                                clienteAux.setIdade(scn.nextInt());
                                ClientePers.Inserir(clienteAux);
                                break;
                            }

                            case 2: {
                                scn.nextLine();
                                System.out.print("\nDigite o ID ou o CPF do cliente a ser removido: ");
                                String aux = scn.nextLine();
                                Cliente auxBuscador;
                                if (aux.matches("[0-9]*")) {
                                    auxBuscador = ClientePers.BuscarId(Integer.parseInt(aux));
                                } else {
                                    auxBuscador = ClientePers.BuscarString(aux);
                                }
                                if (auxBuscador == null) {
                                    System.out.println("\n" + Character.toString(128_190) +
                                            " Cliente nao encontrado.");
                                    break;
                                }
                                if (VendaPers.BuscarString(auxBuscador.getCpf()) != null){
                                    System.out.println("\n" + Character.toString(128_190) +
                                            " Esse cliente nao pode ser excluido pois tem ingressos comprados!");
                                    break;
                                }
                                ClientePers.Excluir(auxBuscador);
                                break;
                            }
                            default: {
                                System.out.println("\n" + Character.toString(128_190) +
                                        " Opcao invalida!");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    opcaoLoopFilmes = 1;
                    while (opcaoLoopFilmes != 0){
                        System.out.println("\nMENU DE FILMES");
                        System.out.println("\t1 - Insercao de Filme");
                        System.out.println("\t2 - Remocao de Filme");
                        System.out.println("\t0 - Para Voltar ao Menu Anterior");
                        System.out.print("\n\tOpcao: ");
                        opcaoMenuFilmes = scn.nextInt();
                        switch (opcaoMenuFilmes){
                            case 0: {
                                opcaoLoopFilmes = 0;
                                break;
                            }
                            case 1: {
                                Filme filmeAux = new Filme();
                                scn.nextLine();
                                System.out.print("\nDigite o NOME do filme: ");
                                String aux = scn.nextLine();
                                if (FilmePers.BuscarString(aux) != null){
                                    System.out.println("\n" + Character.toString(128_190) +
                                            " Filme ja cadastrado!");
                                    break;
                                }
                                filmeAux.setTitulo(aux);
                                System.out.print("\nDigite a FAIXA ETARIA do filme: ");
                                filmeAux.setFaixaEtaria(scn.nextInt());
                                scn.nextLine();
                                System.out.print("\nDigite o GENERO do filme: ");
                                filmeAux.setGenero(scn.nextLine());
                                FilmePers.Inserir(filmeAux);
                                break;
                            }
                            case 2: {
                                scn.nextLine();
                                System.out.print("\nDigite o ID ou o titulo do filme a ser removido: ");
                                String aux = scn.nextLine();
                                Filme auxBuscador;
                                if (aux.matches("[0-9]*")) {
                                    auxBuscador = FilmePers.BuscarId(Integer.parseInt(aux));
                                } else {
                                    auxBuscador = FilmePers.BuscarString(aux);
                                }
                                if (auxBuscador == null) {
                                    System.out.println("\n" + Character.toString(128_190) +
                                            " Filme nao encontrado.");
                                    break;
                                }
                                if (auxBuscador.getContagemdeIngressos() != 0){
                                    System.out.println("\n" + Character.toString(128_190) +
                                            " Esse filme ainda tem ingressos disponiveis!");
                                    break;
                                }
                                FilmePers.Excluir(auxBuscador);
                                break;
                            }
                            default: {
                                System.out.println("\n" + Character.toString(128_190) +
                                        " Opcao invalida!");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 3: {
                    opcaoLoopVenda = 1;
                    while (opcaoLoopVenda != 0){
                        System.out.println("\nMENU DE VENDAS E INGRESSOS");
                        System.out.println("\t1 - Realizar venda");
                        System.out.println("\t2 - Remover venda");
                        System.out.println("\n\t 3 - Inserir Ingresso(s)\n\t4 - Remover Ingresso(s)");
                        System.out.println("\t0 - Para Voltar ao Menu Anterior");
                        System.out.print("\n\tOpcao: ");
                        opcaoMenuVenda = scn.nextInt();
                        switch(opcaoMenuVenda){
                            case 0: {
                                opcaoLoopVenda = 0;
                                break;
                            }
                            case 1: {
                                if (IngressoPers.getListadeIngressos().size() == 0){
                                    System.out.println("\n" + Character.toString(128_196) +
                                            " Ainda nao ha ingressos disponiveis para venda!");
                                    break;
                                }
                                Venda vendaAux;
                                scn.nextLine();
                                System.out.print("\nDigite o ID ou o CPF do cliente: ");
                                String aux = scn.nextLine();
                                Cliente auxBuscador;
                                if (aux.matches("[0-9]*")) {
                                    auxBuscador = ClientePers.BuscarId(Integer.parseInt(aux));
                                } else {
                                    auxBuscador = ClientePers.BuscarString(aux);
                                }
                                if (auxBuscador == null) {
                                    System.out.println("\n" + Character.toString(128_190) +
                                            " Cliente nao encontrado.");
                                    break;
                                }
                                opcaoComprarOutroIngresso = 1;
                                while(opcaoComprarOutroIngresso != 0) {
                                    System.out.print("\nDigite o ID ou o titulo do filme que quer comprar: ");
                                    String aux2 = scn.nextLine();
                                    Filme auxBuscador2;
                                    if (aux2.matches("[0-9]*")) {
                                        auxBuscador2 = FilmePers.BuscarId(Integer.parseInt(aux2));
                                    } else {
                                        auxBuscador2 = FilmePers.BuscarString(aux2);
                                    }
                                    if (auxBuscador2 == null) {
                                        System.out.println("\n" + Character.toString(128_190) +
                                                " Filme nao encontrado.");
                                        break;
                                    }
                                    // Verificando faixa etaria
                                    if (auxBuscador.getIdade() < auxBuscador2.getFaixaEtaria()) {
                                        System.out.println("\n" + Character.toString(129_306) +
                                                " UEPA! O filme tem classificacao etaria somente para maiores de " +
                                                auxBuscador2.getFaixaEtaria() + " anos.");
                                        break;
                                    }
                                    Ingresso ingressoAUX;
                                    System.out.print("\nQual o horario? ");
                                    int horarioesp = scn.nextInt();
                                    ingressoAUX = IngressoPers.BuscarString(Integer.toString(horarioesp));
                                    if (ingressoAUX == null){
                                        System.out.println("\n" + Character.toString(129_306) +
                                                " Nao existem filmes sendo exibidos no horario.");
                                        break;
                                    }
                                    if (!ingressoAUX.getFilme().getTitulo().equals(auxBuscador2.getTitulo())){
                                        System.out.println("\n" + Character.toString(129_306) +
                                                " O filme " + auxBuscador2.getTitulo() + " nao esta sendo exibido nesse horario.");
                                        break;
                                    }

                                    ingressoAUX = null;
                                    for (int i = 0; i < IngressoPers.getListadeIngressos().size(); i++) {
                                        if (((Ingresso) IngressoPers.getListadeIngressos().get(i)).getFilme().getTitulo().equals(auxBuscador2.getTitulo()) &&
                                                ((Ingresso) IngressoPers.getListadeIngressos().get(i)).getHorarioEntrada() == horarioesp) {
                                            auxBuscador2.setContagemdeIngressos(auxBuscador2.getContagemdeIngressos() - 1);
                                            ingressoAUX = new Ingresso((Ingresso) IngressoPers.getListadeIngressos().get(i));
                                            IngressoPers.Excluir(new Ingresso(1, auxBuscador2, horarioesp));
                                            break;
                                        }
                                    }
                                    if (ingressoAUX == null) {
                                        System.out.println("\n" + Character.toString(128_190) +
                                                " O filme nao existe / Esse horario para o filme nao existe.");
                                        break;
                                    }

                                    vendaAux = VendaPers.BuscarString(auxBuscador.getCpf());
                                    if (vendaAux == null) {
                                        vendaAux = new Venda(auxBuscador, ingressoAUX);
                                        VendaPers.Inserir(vendaAux);
                                    } else {
                                        for (int i = 0; i < VendaPers.getVendas().size(); i++) {
                                            if (VendaPers.getVendas().get(i).getId() == vendaAux.getId()) {
                                                ((Venda) VendaPers.getVendas().get(i)).getIngressosComprados().add(ingressoAUX);
                                                break;
                                            }
                                        }
                                    }
                                    System.out.print("\nDeseja comprar outro ingresso (0) nao (1) sim? ");
                                    while(true) {
                                        opcaoComprarOutroIngresso = scn.nextInt();
                                        if (opcaoComprarOutroIngresso != 0 && opcaoComprarOutroIngresso != 1) {
                                            System.out.print("\nDigite uma opcao valida!");
                                        }
                                        else {
                                            break;
                                        }
                                    }
                                }
                                break;
                            }

                            case 2: {
                                if (VendaPers.getVendas().size() == 0) {
                                    System.out.println("\n" + Character.toString(128_190) +
                                            " Nenhuma venda foi realizada!");
                                    break;
                                }
                                    scn.nextLine();
                                    System.out.print("\nDigite o ID ou o CPF do cliente da venda: ");
                                    String aux = scn.nextLine();
                                    Venda vendaAux;
                                    if (aux.matches("[0-9]*")) {
                                        vendaAux = VendaPers.BuscarId(Integer.parseInt(aux));
                                    } else {
                                        vendaAux = VendaPers.BuscarString(aux);
                                    }
                                    if (vendaAux == null) {
                                        System.out.println("\n" + Character.toString(128_190) +
                                                " Venda nao encontrada.");
                                        break;
                                    }
                                    VendaPers.Excluir(vendaAux);
                                    break;
                                }
                            case 3: {
                                scn.nextLine();
                                System.out.print("\nDigite o ID ou o nome do filme: ");
                                String aux = scn.nextLine();
                                Filme auxBuscador;
                                if (aux.matches("[0-9]*")) {
                                    auxBuscador = FilmePers.BuscarId(Integer.parseInt(aux));
                                } else {
                                    auxBuscador = FilmePers.BuscarString(aux);
                                }
                                if (auxBuscador == null) {
                                    System.out.println("\n" + Character.toString(128_190) +
                                            " Filme nao encontrado.");
                                    break;
                                }
                                Ingresso ingressoAux = new Ingresso();
                                System.out.print("\nQual o horario do filme? ");
                                String horario = scn.nextLine();
                                Ingresso ingressoAux2 = IngressoPers.BuscarString(horario);
                                if (ingressoAux2 != null) {
                                    if (!ingressoAux2.getFilme().getTitulo().equals(auxBuscador.getTitulo())) {
                                        System.out.println("\n" + Character.toString(128_190) +
                                                " O filme '" + ingressoAux2.getFilme().getTitulo() +
                                                "' estara sendo exibido nesse horario!");
                                        break;
                                    }
                                }
                                ingressoAux.setHorarioEntrada(Integer.parseInt(horario));
                                System.out.print("\nQual eh o valor do ingresso? ");
                                ingressoAux.setValor(scn.nextDouble());
                                System.out.print("\nDeseja inserir quantos ingressos: ");
                                int qtdIngressos = scn.nextInt();
                                auxBuscador.setContagemdeIngressos(auxBuscador.getContagemdeIngressos() + qtdIngressos);
                                ingressoAux.setFilme(auxBuscador);
                                IngressoPers.Inserir(ingressoAux);
                                for(int i = 1; i < qtdIngressos; i++) {
                                    Ingresso ingressoAux3 = new Ingresso(ingressoAux);
                                    IngressoPers.Inserir(ingressoAux3);
                                }
                                break;
                            }
                            case 4: {
                                if (IngressoPers.getListadeIngressos().size() == 0) {
                                    System.out.println("\n" + Character.toString(128_190) +
                                            " Nao ha ingressos disponiveis para serem removidos!");
                                    break;
                                }
                                System.out.print("\nQuantos ingressos deseja remover? ");
                                int qtdIngressosR = scn.nextInt();
                                if (qtdIngressosR == 0) break;
                                scn.nextLine();
                                System.out.print("\nDigite o ID ou o nome do filme a ser removido: ");
                                String aux = scn.nextLine();
                                Filme auxBuscador;
                                if (aux.matches("[0-9]*")) {
                                    auxBuscador = FilmePers.BuscarId(Integer.parseInt(aux));
                                } else {
                                    auxBuscador = FilmePers.BuscarString(aux);
                                }
                                if (auxBuscador == null) {
                                    System.out.println("\n" + Character.toString(128_190) +
                                            " Filme nao encontrado.");
                                    break;
                                }
                                if (qtdIngressosR > auxBuscador.getContagemdeIngressos())
                                    qtdIngressosR = auxBuscador.getContagemdeIngressos();
                                System.out.print("\nDeseja remover ingresso de um horario especifico (0) nao, (1) sim? ");
                                while(true) {
                                    int opcaoHorarioEsp = scn.nextInt();
                                    if (opcaoHorarioEsp != 0 && opcaoHorarioEsp != 1){
                                        System.out.println("Digite uma opcao valida!");
                                    }
                                    else {
                                        auxBuscador.setContagemdeIngressos(auxBuscador.getContagemdeIngressos() - qtdIngressosR);
                                        if (opcaoHorarioEsp == 0) {
                                            IngressoPers.Excluir(new Ingresso(qtdIngressosR, auxBuscador, -1));
                                        } else {
                                            System.out.print("\nDigite o horario HH que deseja remover: ");
                                            int horarioesp = scn.nextInt();
                                            Ingresso ingressoaux = IngressoPers.BuscarString(Integer.toString(horarioesp));
                                            if (ingressoaux == null) {
                                                System.out.println("\n" + Character.toString(128_190) +
                                                        " Nao existem filmes sendo exibidos nesse horario!");
                                                break;
                                            }
                                            IngressoPers.Excluir(new Ingresso(qtdIngressosR, auxBuscador, horarioesp));
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                            default: {
                                System.out.println("\n" + Character.toString(128_190) +
                                        " Opcao invalida!");
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
    }
}