package br.com.felipe.luciano.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.felipe.luciano.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.felipe.luciano.mvc.mudi.model.Pedido;
import br.com.felipe.luciano.mvc.mudi.model.User;
import br.com.felipe.luciano.mvc.mudi.repository.PedidoRepository;
import br.com.felipe.luciano.mvc.mudi.repository.UserRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UserRepository userRepository;

    @GetMapping("formulario")// vai para o formulário de cadastro
	//@RequestMapping(method = RequestMethod.GET, value = "formulario")
	public String formulario(RequisicaoNovoPedido requisicaoNovoPedido) {

		return "/pedido/formulario";
	}

	
	//@RequestMapping(method = RequestMethod.POST, value = "novo")
	@PostMapping("novo") // vem do fomulário
	public String novo(@Valid RequisicaoNovoPedido requisicaoNovoPedido, BindingResult result) {
		
		if (result.hasErrors()) {
			return "/pedido/formulario";
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username);
		
		Pedido pedido = requisicaoNovoPedido.toPedido();
		pedido.setUser(user);
		pedidoRepository.save(pedido);

		return "redirect:/home";
	}
	
	

}
