package com.order_management_system.validator;

import com.order_management_system.model.Cliente;
import com.order_management_system.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteValidator {

    private final ClienteRepository clienteRepository;

    public void validaCamposObrigatorios(Cliente cliente) {

        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }

        if (cliente.getSenha() == null || cliente.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatório");
        }

        if (cliente.getEnderecoEntrega() == null || cliente.getEnderecoEntrega().isEmpty()) {
            throw new IllegalArgumentException("Endereço de entrega é obrigatório");
        }

    }

    public void validaEmailDuplicado(Cliente cliente) {
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
    }

}
