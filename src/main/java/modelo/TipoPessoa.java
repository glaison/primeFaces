package modelo;

public enum TipoPessoa {
	
	FISICA("F"),
	JURIDICA("J");
	private String descricao;
	
	TipoPessoa(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}

}
