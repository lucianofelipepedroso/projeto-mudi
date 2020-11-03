package br.com.felipe.luciano.mvc.mudi.dto;

import javax.validation.constraints.NotBlank;

import br.com.felipe.luciano.mvc.mudi.model.Pedido;
import br.com.felipe.luciano.mvc.mudi.model.StatusPedido;

public class RequisicaoNovoPedido {

	@NotBlank
	private String nomeProduto;
	@NotBlank
	private String urlProduto;
	@NotBlank
	private String urlImagem;
	
	private String descricao;

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getUrlProduto() {
		return urlProduto;
	}

	public void setUrlProduto(String urlProduto) {
		this.urlProduto = urlProduto;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pedido toPedido() {
		Pedido p= new Pedido();
		p.setDescricao(descricao);
		p.setNomeProduto(nomeProduto);
		p.setUrlProduto(urlProduto);
		p.setUrlImagem(urlImagem);
		p.setStatus(StatusPedido.AGUARDANDO);
		return p;
	}
}
