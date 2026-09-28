package br.com.example.hexagon_example.domain.port.out;

import br.com.example.hexagon_example.domain.model.User;

public interface TokenProviderPort {

    String generate(User user);

}
