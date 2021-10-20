package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Pessoa;



@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Pessoa p = new Pessoa();
	
	
	public List<Pessoa> pessoaList;  
	public List<Pessoa> pessoasList;

	public List<Pessoa> ps;
	

	//private ArrayList<String> ps = new ArrayList<String>();
	
	
	private ArrayList<Pessoa> pessoas;

	public PessoaBean() {

	}

	public String salvar() {
		String msg = null;
		try {
			msg = Pessoa.inserir(p);
			
			//System.out.println(msg);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return msg;
	}

	/*listar todos os registros  do banco de dados */
	public Collection<Pessoa> consultar() {
		try {
			ps =  Pessoa.ler();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ps;
	}
	
	
	public List<Pessoa> getPessoaList() {
		pessoasList = new ArrayList<>();
		pessoas =  (ArrayList<Pessoa>) Pessoa.ler();

		return pessoas;

	}
	
	
	
	
	
	
	/*consultar por descrição*/
	public  List<Pessoa> consultarPorDesc(String nome) {
		try {
			 pessoas =  (ArrayList<Pessoa>) Pessoa.pesquisar(nome);
		

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		//System.out.println(pessoas);
		
		return pessoas;
	}
	
	
	
	
	/*consultar por ID*/
	public Pessoa consultarPorId(int id) {
		try {

			p = Pessoa.lerPorId(id);
		

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println(p);
		return p;
	}
	
	
	
	

	
	
	/*Deletar*/

	public void deletar(int id) {
		try {

			Pessoa.remover(id);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("Registro apagado com sucesso!!!");

	}

	public void atualizar() {
		//System.out.println("chegou no editar");

		try {
			Pessoa.atualizar(getId(), getNome(), getIdade(), getObservacao(), getTelefone(), getEmail());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Integer getId() {
		return p.getId();
	}

	public void Integer(Integer id) {
		p.setId(id);
	}

	public String getNome() {
		return p.getNome();
	}

	public void setNome(String nome) {
		p.setNome(nome);
	}

	public Integer getIdade() {
		return p.getIdade();
	}

	public void setIdade(Integer idade) {
		p.setIdade(idade);
	}

	public String getEmail() {
		return p.getEmail();
	}

	public void setEmail(String email) {
		p.setEmail(email);
	}

	public String getTelefone() {
		return p.getTelefone();
	}

	public void setTelefone(String telefone) {
		p.setTelefone(telefone);
	}

	public String getObservacao() {
		return p.getObservacao();
	}

	public void setObservacao(String observacao) {
		p.setObservacao(observacao);
	}
	
	
	public String getTipoPessoa() {
		return p.getTipoPessoa();
	}
	
	
	public void setTipoPessoao(String tipoPessoa) {
		p.setObservacao(tipoPessoa);
	}

	public ArrayList<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(ArrayList<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public String getMsg() {
		return "OK!!";
	}
	

}
