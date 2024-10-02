package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizandoTutoDTO;
import br.com.alura.adopet.api.dto.CadastroTutorDTO;
import br.com.alura.adopet.api.excpetion.ValidacaoExcpetion;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TutorService {
    @Autowired
    private TutorRepository repository;

    public void cadastrar(CadastroTutorDTO dto){
        boolean telefoneJaCadastrado = repository.existsByTelefone(dto.tutor().getTelefone());
        boolean emailJaCadastrado = repository.existsByEmail(dto.tutor().getEmail());

        if (telefoneJaCadastrado || emailJaCadastrado) {
                throw new ValidacaoExcpetion("Dados já cadastrados para outro tutor!");
        }
    }

    public void atualizar(AtualizandoTutoDTO dto){
        Tutor tutor = repository.getReferenceById(dto.id());
        tutor.atualizarDados(dto);
    }
}
