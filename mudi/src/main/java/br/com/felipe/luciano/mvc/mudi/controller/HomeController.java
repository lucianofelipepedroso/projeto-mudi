package br.com.felipe.luciano.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.constraintvalidation.SupportedValidationTarget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
@RequestMapping("home")
public class HomeController {

	@Autowired
	private PedidoRepository pedidoRepository;

//	@GetMapping("/home")
//	public String home(Model model) {
//
//		List<Pedido> pedidos = pedidoRepository.findAll();
//
//		model.addAttribute("pedidos", pedidos);
//		return "home";
//
//	}

	@GetMapping
	public ModelAndView home(Principal principal) {

		Sort sort = Sort.by("dataDaEntrega").descending();
		PageRequest pRequest = PageRequest.of(0,1, sort);
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE,pRequest);
		ModelAndView mView = new ModelAndView("home");

		mView.addObject("pedidos", pedidos);
		return mView;

	}


}
