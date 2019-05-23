package com.bartades.model;

import java.util.ArrayList;

public class PedidoCompraProduto {
	
	private int CODIGOPEDIDO;
	private int quantidadeProdutos;
	private ArrayList<Produto> produtos;
	private double valorTotalPedido;
	private String dataPedido;
	private int idUsuarioPedido;
	private String nomeUsuarioPedido;
	
	//Construtor utilizado no primeiro cadastro no banco de dados
	public PedidoCompraProduto(int quantidadeProdutos, ArrayList<Produto> produtos, int idUsuarioPedido) {
		this.quantidadeProdutos = quantidadeProdutos;
		this.produtos = produtos;
		this.setIdUsuarioPedido(idUsuarioPedido);
	}
	
	//Construtor completo de pedido compra
	public PedidoCompraProduto(int CODIGOPEDIDO,  int quantidadeProdutos, ArrayList<Produto> produtos, double valorTotalPedido, String dataPedido, String nomeUsuarioPedido) {
		this.CODIGOPEDIDO = CODIGOPEDIDO;
		this.quantidadeProdutos = quantidadeProdutos;
		this.produtos = produtos;
		this.valorTotalPedido = valorTotalPedido;
		this.setDataPedido(dataPedido);
		this.setNomeUsuarioPedido(nomeUsuarioPedido);
	}
	
	//Construtor utilizado ao salvar os produtos nas tabelas de compra no banco de dados (nao contem data e usuario)
	public PedidoCompraProduto(int CODIGOPEDIDO,  int quantidadeProdutos, ArrayList<Produto> produtos, double valorTotalPedido) {
		this.CODIGOPEDIDO = CODIGOPEDIDO;
		this.quantidadeProdutos = quantidadeProdutos;
		this.produtos = produtos;
		this.valorTotalPedido = valorTotalPedido;
		
	}
	
	
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public ArrayList<Produto> getProdutos(){
		return this.produtos;
	}
	
	public int getCodigoPedido() {
		return this.CODIGOPEDIDO;
	}
	
	public double getValorTotalPedido() {
		return this.valorTotalPedido;
	}
	
	public void setQuantidadeProdutos(int quantidadeProdutos) {
		this.quantidadeProdutos = quantidadeProdutos;
	}
	
	public int getQuantidadeProdutos() {
		return this.quantidadeProdutos;
	}
	
	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getNomeUsuarioPedido() {
		return nomeUsuarioPedido;
	}

	public int getIdUsuarioPedido() {
		return idUsuarioPedido;
	}

	public void setIdUsuarioPedido(int idUsuarioPedido) {
		this.idUsuarioPedido = idUsuarioPedido;
	}

	public void setNomeUsuarioPedido(String nomeUsuarioPedido) {
		this.nomeUsuarioPedido = nomeUsuarioPedido;
	}

	public void setValorTotalPedido(double valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}
	
	public void encontrarNomeProdutos(ArrayList<Produto> listaGeral) {
		for(Produto p1 : listaGeral) {
			for(Produto p2 : this.produtos) {
				if(p1.getId() == p2.getId()) {
					p2.setNome(p1.getNome());
				}
			}
		}
	}
	
	public void calculaValorTotalProduto() {
		
		for(Produto p : this.produtos) {
			p.setValorTotalCompra(p.getPrecoCompra() * p.getQuantidade());
		}
		
	}
	
	public void calculaValorTotalPedido() {
		double somaTotal = 0.0;
		for(Produto p : this.produtos) {
			somaTotal += p.getValorTotalCompra();
		}
		this.setValorTotalPedido(somaTotal);
	}

	
	@Override
	public String toString() {
				
		String strFinal = "";
		
		strFinal = "Pedido numero: " + this.getCodigoPedido() + ", feito pelo usuario: " + this.nomeUsuarioPedido + ", na data: " + this.dataPedido + "\nContém os produtos: ";
		
		for(Produto p : this.produtos) {
			
			strFinal += "\n" + p.getNome() + ", id: " + p.getId() + ", quantidade: " + p.getQuantidade() + ", valor total do produto: " + p.getPrecoCompra();
		}
			strFinal += "\nQuantidade de produtos: " + this.getQuantidadeProdutos();
			strFinal += "\nValor total: " + this.getValorTotalPedido();
		return strFinal;
	}
}
