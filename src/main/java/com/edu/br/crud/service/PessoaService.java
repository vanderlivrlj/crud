package com.edu.br.crud.service;

import com.edu.br.crud.model.Pessoa;
import com.edu.br.crud.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }
    public List<Pessoa> findAll(){
        return (List<Pessoa>) pessoaRepository.findAll();
    }

}
