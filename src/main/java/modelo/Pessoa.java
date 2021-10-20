package modelo;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.HibernateError;

import dao.DAO;

@Entity
@ViewScoped
@Table(name="pessoa")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 80)
	private  String nome;

	@Min(2)
	private Integer idade;
	
	@NotNull
	@NotBlank(message ="Email é obrigatório")
	@Size(min=10, max=100, message = "Email deve conter até 100 caracteres")
	private String email;
	

	
	private String telefone;
	private String observacao;
	private String tipoPessoa;
	
   public Pessoa() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
	public String getTipoPessoa() {
		return tipoPessoa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	public Pessoa(Integer id, String nome, @Min(2) Integer idade,
			@NotNull @NotBlank(message = "Email é obrigatório") @Size(min = 10, max = 100, message = "Email deve conter até 100 caracteres") String email,
			String tipoPessoa, String telefone, String observacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.email = email;
		this.tipoPessoa = tipoPessoa;
		this.telefone = telefone;
		this.observacao = observacao;
	}

	public static String inserir(Pessoa p) {
		String msg = null;
		try {
			EntityManager entityManager = DAO.getEntityManager();
			entityManager.getTransaction().begin();
				entityManager.persist(p);
			entityManager.getTransaction().commit();
			entityManager.close();
			System.out.println("conectado Salvo!");
			msg = "OK";
		} catch (HibernateError ex) {
			ex.printStackTrace();
		}
		return msg;
	}

	public static List<Pessoa> ler() { 
		
		EntityManager entityManager = DAO.getEntityManager();
		//entityManager.getTransaction().begin();
		
		String jpql = "SELECT obj from Pessoa obj";
		TypedQuery<Pessoa> query = entityManager.createQuery(jpql, Pessoa.class);
		
			try {
				return query.getResultList();
		
			
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			//entityManager.close();

		return query.getResultList();
		
	}
	
	
	

	
	
	
	
	
	
	/*Pesquisar por parte do nome da pessoa*/
	public static List<Pessoa> pesquisar(String nome) {
		
		EntityManager entityManager = DAO.getEntityManager();
		
		String jpql = "from Pessoa where nome like :nome";
		
		TypedQuery<Pessoa> query = entityManager.createQuery(jpql, Pessoa.class);
		query.setParameter("nome", "%"+ nome + "%");
		//entityManager.close();
		System.out.println(query);
		
		return query.getResultList();
	}
	
	
	public static Pessoa lerPorId(int id) {
		EntityManager entityManager = DAO.getEntityManager();

		entityManager.getTransaction().begin();

		Pessoa p = entityManager.find(Pessoa.class,id);
	
		entityManager.getTransaction().commit();
		//entityManager.close();
	
		return p;
		
	}

	

	public static void atualizar(int id, String nome, Integer idade, String observacao, String telefone, String email) {

		try {

			EntityManager entityManager = DAO.getEntityManager();

			Pessoa p = entityManager.find(Pessoa.class, id);
			
			entityManager.getTransaction().begin();
				p.setNome(nome);
				p.setIdade(idade);
				p.setTelefone(telefone);
				p.setEmail(email);
				p.setObservacao(observacao);				
			entityManager.getTransaction().commit();
			entityManager.close();

			System.out.println("Registro atualizado");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void remover(int id) {
		try {

			EntityManager entityManager = DAO.getEntityManager();

			entityManager.getTransaction().begin();

			Pessoa p = entityManager.find(Pessoa.class,id);
			entityManager.remove(p);
			entityManager.getTransaction().commit();
			entityManager.close();

			System.out.println("registro removido!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	


}
