package edu.ifam.productlist.dto;

import edu.ifam.productlist.entity.Fornecedor;

public class FornecedorDTO {
    private long id;
    private int codigo;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;

    public FornecedorDTO() {

    }

    public FornecedorDTO(Fornecedor fornecedor){
        this.id = fornecedor.getId();
        this.codigo = fornecedor.getCodigo();
        this.nome = fornecedor.getNome();
        this.telefone = fornecedor.getTelefone();
        this.email = fornecedor.getEmail();
        this.endereco = fornecedor.getEndereco();
    }

    public Fornecedor getFornecedor(){
        return new Fornecedor(id, codigo, nome, telefone, email, endereco);
    }

    public long getId(){
        return id;
    }
}
