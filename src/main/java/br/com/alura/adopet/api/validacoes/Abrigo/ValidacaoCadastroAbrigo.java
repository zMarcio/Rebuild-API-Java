package br.com.alura.adopet.api.validacoes.Abrigo;

import br.com.alura.adopet.api.dto.CadastrarAbrigoDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoExcpetion;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoCadastroAbrigo {
    @Autowired
    private AbrigoRepository repository;

    public boolean validar(CadastrarAbrigoDTO dto){
        if (!repository.existsByNome(dto.nome())) throw new ValidacaoExcpetion("Nome está em uso");
        if(!repository.existsByTelefone(dto.telefone())) throw new ValidacaoExcpetion("Número do telefone já está cadastrado");
        if(!repository.existsByEmail(dto.email())) throw new ValidacaoExcpetion("Email está em uso");
        return true;
    }

}
