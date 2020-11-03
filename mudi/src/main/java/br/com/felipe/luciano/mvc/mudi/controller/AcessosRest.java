package br.com.felipe.luciano.mvc.mudi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.luciano.mudi.interceptor.InterceptadorDeAcessos;
import br.com.felipe.luciano.mudi.interceptor.InterceptadorDeAcessos.Acesso;

@RequestMapping("api/acessos")
@RestController
public class AcessosRest {
	
	@GetMapping
	public List<Acesso> getAcessos(){
		return InterceptadorDeAcessos.list;
		
	}

}
