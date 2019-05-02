package com.bartades.controller;

import com.bartades.dao.FornecedoresDAO;
import com.bartades.model.Fornecedores;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Antonio Carlos
 */
public class FornecedorController {

    public static boolean SalvarFornecedor(String nome, String cnpj, String telefone, String endereco, String numero, String complemento, String cep, String bairro, String cidade, String estado, boolean disponibilidade)
            throws ClassNotFoundException, SQLException {

        Fornecedores fornecedor = new Fornecedores(nome, cnpj, telefone, endereco, numero, complemento, cep, bairro, cidade, estado, disponibilidade);
        return FornecedoresDAO.incluir(fornecedor);

    }

    public static boolean AtualizarFornecedor(int id, String nome, String cnpj, String telefone, String endereco, String numero, String complemento, String cep, String bairro, String cidade, String estado, boolean disponibilidade)
            throws ClassNotFoundException, SQLException {

        Fornecedores f = new Fornecedores(id, nome, cnpj, telefone, endereco, numero, complemento, cep, bairro, cidade, estado, disponibilidade);

        return FornecedoresDAO.atualizar(f);
    }
    
     public static ArrayList<Fornecedores> listarFornecedores() throws ClassNotFoundException, SQLException{
        return FornecedoresDAO.listarFornecedores();
    }
}
