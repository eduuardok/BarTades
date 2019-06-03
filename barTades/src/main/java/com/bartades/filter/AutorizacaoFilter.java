package com.bartades.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bartades.model.Usuario;


/**
 * 
 * @author ELuna
 *
 */
@WebFilter(filterName = "AutenticacaoFilter", urlPatterns = {"/WEB-INF/jsp/*"}, servletNames = {"CadastrarProduto", "AtualizarProduto", "Home", "VisualizarProdutos", "EditarUsuario", 
		"BuscarFornecedores", "editarFornecedor", "visualizarFornecedor" , "cadastroFranquia", "CadastrarUsuario", "FornecedoresServlet", "VisualizarUsuarios", "LogoutServlet", "CadastroPedido", "ComprarProduto", "visualizarFranquia"})

public class AutorizacaoFilter implements Filter {
	
	
	 @Override
	    public void doFilter(
                    
	            ServletRequest request,
	            ServletResponse response,
	            FilterChain chain) throws IOException, ServletException {
	        
            
	        HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
	    
	        
	        HttpSession sessao = httpRequest.getSession();
	        
	        
	        if (sessao.getAttribute("usuario") == null) {
	        	
	            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
	            return;
	        }

	        
	        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
	       
	        if (verificarAcesso(usuario, httpRequest, httpResponse)) {
	           
	            chain.doFilter(request, response);
	            
	        } else {
	        	httpResponse.sendRedirect(httpRequest.getContextPath() + "/acessoNegado.jsp");
	        }
	        
	    }
	 
	 private boolean verificarAcesso(Usuario usuario,
	            HttpServletRequest request,
	            HttpServletResponse response) {
		 	String paginaAcessada = request.getRequestURI();
	        if(paginaAcessada.endsWith("/home")) {
	        	return true;
	        }
	        else if(usuario.getNivelAcesso() == 1 && 
	        (paginaAcessada.endsWith("/visualizarFornecedor") || paginaAcessada.endsWith("/visualizarPedidosCompra") || 
	        		paginaAcessada.endsWith("/visualizarProdutos") || paginaAcessada.endsWith("/visualizarPedidos") || paginaAcessada.endsWith("/logout"))){
	        	
	        	return true;
	        } 
	        else if(usuario.getNivelAcesso() == 2 && (paginaAcessada.endsWith("/editarFornecedor") || paginaAcessada.endsWith("/editarProduto") || 
	        		paginaAcessada.endsWith("/CadastroPedido") || paginaAcessada.endsWith("/cadastroProduto") || 
	        		paginaAcessada.endsWith("/compraProduto") || paginaAcessada.endsWith("/FornecedoresServlet") ||
	        		paginaAcessada.endsWith("/visualizarFornecedor") || paginaAcessada.endsWith("/visualizarPedidosCompra") ||
	        		paginaAcessada.endsWith("/visualizarProdutos")  || paginaAcessada.endsWith("/logout")
	        		)) {
	        	return true;
	        } 
	        else if(usuario.getNivelAcesso() == 3 && (paginaAcessada.endsWith("/visualizarProdutos") || paginaAcessada.endsWith("/buscarFornecedor") || 
	        		paginaAcessada.endsWith("/visualizarPedidosCompra") || paginaAcessada.endsWith("/visualizarPedidos") || 
	        		paginaAcessada.endsWith("/cadastroProduto") || paginaAcessada.endsWith("/editarProduto") ||
	        		paginaAcessada.endsWith("/visualizarFranquia") || paginaAcessada.endsWith("/compraProduto") ||
	        		paginaAcessada.endsWith("/visualizarPedidosCompra") || paginaAcessada.endsWith("/FornecedoresServlet") ||
	        		paginaAcessada.endsWith("/CadastroPedido") || paginaAcessada.endsWith("/visualizarPedidos") || paginaAcessada.endsWith("/editarPedido") || paginaAcessada.endsWith("/logout"))) {
	        	return true;
	        } 
	        else if(usuario.getNivelAcesso() == 4 && (paginaAcessada.endsWith("/visualizarProdutos") || paginaAcessada.endsWith("/buscarFornecedor") || 
	        		paginaAcessada.endsWith("/visualizarPedidosCompra") || paginaAcessada.endsWith("/visualizarPedidos") || 
	        		paginaAcessada.endsWith("/cadastroProduto") || paginaAcessada.endsWith("/editarProduto") ||
	        		paginaAcessada.endsWith("/visualizarFranquia") || paginaAcessada.endsWith("/compraProduto") ||
	        		paginaAcessada.endsWith("/visualizarPedidosCompra") || paginaAcessada.endsWith("/FornecedoresServlet") ||
	        		paginaAcessada.endsWith("/CadastroPedido") || paginaAcessada.endsWith("/visualizarPedidos") || paginaAcessada.endsWith("/visualizarUsuarios") || paginaAcessada.endsWith("/editarPedido") || paginaAcessada.endsWith("/logout"))) {
	        	
	        	return true;
	        }
	        else if(usuario.getNivelAcesso() == 5 && (paginaAcessada.endsWith("/visualizarProdutos") || paginaAcessada.endsWith("/buscarFornecedor") || 
	        		paginaAcessada.endsWith("/visualizarPedidosCompra") || paginaAcessada.endsWith("/visualizarPedidos") || 
	        		paginaAcessada.endsWith("/cadastroProduto") || paginaAcessada.endsWith("/editarProduto") ||
	        		paginaAcessada.endsWith("/visualizarFranquia") || paginaAcessada.endsWith("/compraProduto") ||
	        		paginaAcessada.endsWith("/visualizarPedidosCompra") || paginaAcessada.endsWith("/FornecedoresServlet") ||
	        		paginaAcessada.endsWith("/CadastroPedido") || paginaAcessada.endsWith("/visualizarPedidos") || paginaAcessada.endsWith("/editarPedido") || 
	        		paginaAcessada.endsWith("/logout") || paginaAcessada.endsWith("/visualizarUsuarios") || 
	        		paginaAcessada.endsWith("/visualizarFornecedor"))) {
	        	return true;
	        }
	        else if(usuario.getNivelAcesso() == 6) {
	        	return true;
	        }
//	        if (paginaAcessada.endsWith("/home") || paginaAcessada.endsWith("/cadastroProduto") 
//	        		|| paginaAcessada.endsWith("/editarProduto") || paginaAcessada.endsWith("/visualizarProdutos")
//	        		|| paginaAcessada.endsWith("/editarUsuario") || paginaAcessada.endsWith("/buscarFornecedor")
//	        		|| paginaAcessada.endsWith("/cadastroFranquia") || paginaAcessada.endsWith("/cadastroUsuario")
//	        		|| paginaAcessada.endsWith("/FornecedoresServlet") || paginaAcessada.endsWith("/visualizarUsuarios")
//	        		|| paginaAcessada.endsWith("/logout") || paginaAcessada.endsWith("/CadastroPedido") || paginaAcessada.endsWith("/compraProduto")) {
//	            return true;
//	        } 
	        else {
	        	return false;
	        	
	        }
	 }
	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	    }

	    @Override
	    public void destroy() {
	    }

}
	

