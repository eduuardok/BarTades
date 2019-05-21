package com.bartades.model;

import java.util.ArrayList;

public class PedidoCompraProduto {
	
	private int CODIGOPEDIDO;
	private ArrayList<Produto> produtos;
	
	public PedidoCompraProduto(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public PedidoCompraProduto(int CODIGOPEDIDO,  ArrayList<Produto> produtos) {
		this.CODIGOPEDIDO = CODIGOPEDIDO;
		this.produtos = produtos;
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

	
	@Override
	public String toString() {
		
		String strFinal = "";
		
		strFinal = "Pedido numero: " + this.getCodigoPedido() + "\nContém os produtos: ";
		
		for(Produto p : this.produtos) {
			strFinal += "\n" + p.getNome();
		}
		
		return strFinal;
	}
}
