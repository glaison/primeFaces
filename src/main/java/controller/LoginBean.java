package controller;


import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import modelo.Usuario;

@ManagedBean
@RequestScoped
public class LoginBean {
	
//	@Inject
	private Usuario usuario;
	private String nomeUsuario;
	private String senha;
	
	Usuario u = new Usuario();

	public String login() {
		//System.out.println(new Date());
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		if ("admin".equals(this.nomeUsuario) && "123".equals(this.senha)) {
			//u.setNome("ADM");
			//setNomeUsuario("ADM");
			//this.usuario.setNome("Admin");
			//this.usuario.setDataLogin(new Date());
			
			return "/index?faces-redirect=true";
		} else {
			//System.out.println(new Date());
		
			FacesMessage mensagem = new FacesMessage("Usuário/senha inválidos!");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);/*
			FacesMessage mensagem = new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Cadastro efetuado", " com sucesso!!!");
					context.addMessage(null, mensagem);*/

		}
		return null;
	}
	
	
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
		.invalidateSession();
		return "/Login?faces-redirect=true";
		}


	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}