package br.com.nava.delegates;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Named("cadastroAprovadoDelegate")
public class CadastroAprovadoDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println("CadastroAprovadoDelegate -- ");
		
//		retorna um Object
		Integer parametro = (Integer)execution.getVariable("outroParametro");
		
		System.out.println("CadastroAprovadoDelegate Param -- " +parametro);
		
		
	}

}
