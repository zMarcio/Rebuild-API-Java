package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.*;
import br.com.alura.adopet.api.dto.ListarECadastroPetDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoExcpetion;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.validacoes.Abrigo.ValidacaoCadastroAbrigo;
import br.com.alura.adopet.api.validacoes.Abrigo.ValidacaoListaPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbrigoService {
    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ValidacaoCadastroAbrigo validacao;

    @Autowired
    private ValidacaoListaPet validarListaPet;

    public List<AbrigoDTO> listarAbrigos(){
        return abrigoRepository.findAll().stream().map(AbrigoDTO::new).toList();
    }

    public void cadastrarAbrigo(CadastrarAbrigoDTO dto){
        validacao.validar(dto);
        Abrigo abrigo = new Abrigo(dto.nome(), dto.telefone(), dto.email());
        abrigoRepository.save(abrigo);
    }

    public List<PetDTO> listPets(String idOuNome){
        Abrigo abrigo = carregarAbrigo(idOuNome);
        return petRepository.findByAbrigo(abrigo).stream().map(PetDTO::new).toList();
    }

    public Abrigo carregarAbrigo(String idOuNome) {
        Optional<Abrigo> optional;
        try {
            Long id = Long.parseLong(idOuNome);
            optional = abrigoRepository.findById(id);
        } catch (NumberFormatException exception) {
            optional = Optional.ofNullable(abrigoRepository.findByNome(idOuNome));
        }

        return optional.orElseThrow(() -> new ValidacaoExcpetion("Abrigo não encontrado"));
    }

}
