package br.com.java8Features.Repository;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import br.com.java8Features.model.Cliente;

public class ClienteRepository {

    public static List<Cliente> filterCliente(List<Cliente> clientes, Predicate<Cliente> predicate) {
        return clientes.stream()
                        .filter(predicate)
                        .collect(Collectors.toList());
    }
    
}
