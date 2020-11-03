package br.com.felipe.luciano.mvc.mudi.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.luciano.mvc.mudi.model.Pedido;
import br.com.felipe.luciano.mvc.mudi.model.StatusPedido;
import br.com.felipe.luciano.mvc.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosRest {

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping(value = "/aguardando")
	public List<Pedido> getPedidosAguardandoOferta() {
		Sort sort = Sort.by("id").descending();
		PageRequest pageRequest = PageRequest.of(0, 10, sort);
		return pedidoRepository.findByStatus(StatusPedido.AGUARDANDO, pageRequest);

	}

}
