package br.com.alura.adopet.api.validacoes.Abrigo;

import br.com.alura.adopet.api.dto.PetDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoExcpetion;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ValidacaoListaPet {

    @Autowired
    private AbrigoRepository repository;

    public void validacaoListaNull(List<PetDTO> pets){
        if (pets == null) throw new ValidacaoExcpetion("Lista de pets não existe");
    }

}
