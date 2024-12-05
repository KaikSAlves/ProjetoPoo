package br.com.senacsp.projetopoo.crudapi;

import br.com.senacsp.projetopoo.crudapi.clients.ApiClient;
import br.com.senacsp.projetopoo.crudapi.model.Marca;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CrudapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudapiApplication.class, args);
		Scanner sc = new Scanner(System.in);

		System.out.print("Informe o id da marca: " );
		int id = sc.nextInt();
		List<Marca> marcas = (List<Marca>) ApiClient.get(id, Marca.class);
		boolean sucess = ApiClient.post(new Marca("TesteApi", "DescricaoTesteAPi"));
		System.out.println(sucess);
		marcas.forEach(System.out::println);
		System.out.println(marcas.size());
	}

}
