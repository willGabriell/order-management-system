package com.order_management_system.service;

import com.order_management_system.model.Cliente;
import com.order_management_system.repository.ClienteRepository;
import com.order_management_system.validator.ClienteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteValidator clienteValidator;

    public Cliente cadastrarCliente(Cliente cliente) {

        clienteValidator.validaCamposObrigatorios(cliente);

        clienteValidator.validaEmailDuplicado(cliente);

        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Long id, Cliente cliente) {
        Cliente clienteSalvo = buscarPorId(id);

        clienteSalvo.setNome(cliente.getNome());
        clienteSalvo.setEmail(cliente.getEmail());
        clienteSalvo.setEnderecoEntrega(cliente.getEnderecoEntrega());

        return clienteRepository.save(clienteSalvo);
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

}
