package com.rar.compras.graphql.resolvers.cliente;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.rar.compras.graphql.resolvers.cliente.domain.ClienteType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteQueryResolverTest extends GraphQLTestTemplate {

	/**
	 * Exemplo de um teste de uma query
	 * @throws IOException
	 * @throws JSONException
	 */
	@Test
	public void testListarClientes() throws IOException, JSONException {
		
		//cliente.graphql arquivo disponível em src/test/resources que é utilizado como payload da requisição
		GraphQLResponse response = this.perform("cliente.graphql", null);
		
		assertTrue(response.isOk());
		
		String json = response.getRawResponse().getBody();
		System.out.println(json);
		
		JSONArray jc = new JSONObject(json).getJSONObject("data").getJSONArray("listarClientes");
		System.out.println(jc);
		
		ObjectMapper mapper = new ObjectMapper();
		List<ClienteType> clientes = mapper.readValue(jc.toString(), new TypeReference<List<ClienteType>>(){});
		
		clientes.forEach(cliente -> System.out.println(cliente.getNome()));
	}
	
}
