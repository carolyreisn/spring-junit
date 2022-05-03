package br.com.nava.delegates;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Named("aprovarCadastroDelegate")
public class AprovarCadastroDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println("AprovarCadastroDelegate -- ");
		
		execution.setVariable("outroParametro", 1);
		
		
	}

}
