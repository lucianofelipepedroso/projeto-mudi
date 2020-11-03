package br.com.felipe.luciano.mvc.mudi.rest;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.luciano.mvc.mudi.dto.RequisicaoNovaOferta;
import br.com.felipe.luciano.mvc.mudi.model.Oferta;
import br.com.felipe.luciano.mvc.mudi.model.Pedido;
import br.com.felipe.luciano.mvc.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

	@Autowired
	private PedidoRepository pedidoRepository;

	@PostMapping
	@Transactional
	public Oferta criarOferta(@Valid @RequestBody RequisicaoNovaOferta requisicao) {
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(requisicao.getPedidoId());
		if (!pedidoBuscado.isPresent()) {
			return null;
		}

		Pedido pedido = pedidoBuscado.get();

		Oferta nova = requisicao.toOferta();

		nova.setPedido(pedido);
		pedido.getOfertas().add(nova);
		pedidoRepository.save(pedido);
		return nova;

	}
	
//	@PostMapping
//	public ResponseEntity<Oferta> criaOferta (@Valid @RequestBody RequisicaoNovaOferta requisicao){
//		
//		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(requisicao.getPedidoId());
//		if (!pedidoBuscado.isPresent()) {
//			return null;
//		}
//
//		Pedido pedido = pedidoBuscado.get();
//
//		Oferta nova = requisicao.toOferta();
//
//		nova.setPedido(pedido);
//		pedido.getOfertas().add(nova);
//		pedidoRepository.save(pedido);
//		
//		return ResponseEntity.ok().body(nova);
//		
//	}
//	

}
