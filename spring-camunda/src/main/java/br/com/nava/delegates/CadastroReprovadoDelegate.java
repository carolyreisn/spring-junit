package br.com.nava.delegates;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Named("cadastroReprovadoDelegate")
public class CadastroReprovadoDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println("CadastroReprovadoDelegate -- ");
		
		
	}

}
