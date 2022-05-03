package br.com.nava.controllers;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "clientes")
public class ClienteController {
	
	@Autowired
	private RuntimeService runTimeService;
	
	@GetMapping (value = "aprovar/{idade}")
	public void aprovarCadastro(@PathVariable int idade) {
//		instanciar um processo via código
		
		this.runTimeService.createProcessInstanceByKey("processa-idade")
		.setVariable("idade", idade)
		.execute()
		.getProcessInstanceId();
	}

}
