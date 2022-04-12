package br.com.nava.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CalculadoraUtilTests {
	
	private CalculadoraUtil calculadoresUtil = new CalculadoraUtil();
	
	@Test
	void somarTeste() {
//		primeiro ideia: chamar o metodo que queremos testar e verificar se a resposta do metodo é a esperada
		
		int atual = calculadoresUtil.somar(5, 9);
		assertEquals(14, atual);
		
	}

	
}
