package br.com.senacsp.projetopoo.crudapi.clients;

import br.com.senacsp.projetopoo.crudapi.model.*;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class ApiClient {
    private static WebClient client = WebClient.create("http://localhost:8080");

    public static List<? extends BaseEntity> get(Class classe){
        String endpoint = getEndpoint(classe);
        Flux<? extends  BaseEntity> flux = client.get()
                        .uri(endpoint)
                        .retrieve()
                        .bodyToFlux(classe);

        return flux.collectList().block();
    }

    public static List<? extends BaseEntity> get(int id, Class classe){
        String endpoint = getEndpoint(classe) + "/" + id;
        Flux<? extends  BaseEntity> flux = client.get()
                .uri(endpoint)
                .retrieve()
                .bodyToFlux(classe);

        return flux.collectList().block();
    }

    public static boolean post(Object obj){
        String endpoint = getEndpoint(obj.getClass());
        Mono<ApiResponse> mono = client.post()
                .uri(endpoint)
                .bodyValue(obj)
                .retrieve()
                .bodyToMono(ApiResponse.class);

        ApiResponse response = mono.block();
        return response != null && response.getStatus();
    }

    public static boolean delete(int id, Class classe){
        String endpoint = getEndpoint(classe) + "/" + id;
        Mono<ApiResponse> mono = client.delete()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(ApiResponse.class);

        ApiResponse response = mono.block();
        return response != null && response.getStatus();
    }

    private static String getEndpoint(Class classe){
        String endpoint = "/api/";
       if(classe == Produto.class){
            return endpoint + "produtos";
       } else if (classe == Categoria.class) {
           return endpoint + "categorias";
       } else if (classe == Marca.class) {
           return endpoint + "marcas";
       } else {
           return endpoint + "produtos";
       }
    }
}
