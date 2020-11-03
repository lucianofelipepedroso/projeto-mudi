package br.com.felipe.luciano.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.felipe.luciano.mvc.mudi.model.Pedido;
import br.com.felipe.luciano.mvc.mudi.model.StatusPedido;
import br.com.felipe.luciano.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("pedido")
	public ModelAndView home(Principal principal) {

		List<Pedido> pedidos = pedidoRepository.findAllByUsuario(principal.getName());
		ModelAndView mView = new ModelAndView("usuario/home");

		mView.addObject("pedidos", pedidos);
		return mView;

	}
	
	@GetMapping("pedido/{status}")
	public String porStatus(@PathVariable("status") String status,Model model,Principal principal) {

		List<Pedido> pedidos = pedidoRepository.findAllByStatusEUsuario(StatusPedido.valueOf(status.toUpperCase()),principal.getName());
		model.addAttribute("pedidos",pedidos);
		model.addAttribute("status",status);
		return "usuario/home";

	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/usuario/home";
	}


}
