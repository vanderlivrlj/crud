package com.edu.br.crud.controller;

import com.edu.br.crud.model.Pessoa;
import com.edu.br.crud.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository){
        super();
        this.pessoaRepository = pessoaRepository;
    }

    @GetMapping("/index")
    public String home(Model model){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        model.addAttribute("pessoas", pessoas);
        return "index";
    }

    @PostMapping("/add")
    public String novo (@Validated Pessoa pessoa, BindingResult result){
        if (result.hasErrors()){
            return "redirect:/form";
        }
        pessoaRepository.save(pessoa);
        return "redirect:/index";
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Pessoa>> getById(@PathVariable Integer id){
        Optional<Pessoa> pessoa;
        try{
            pessoa = pessoaRepository.findById(id);
            return new ResponseEntity<Optional<Pessoa>>(pessoa, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Optional<Pessoa>>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(path = "/form")
    public String pessoaForm (Pessoa pessoa){
        return "addPessoasForm";
    }

    @GetMapping(path = "delete/{id}")
    public String deleteById(@PathVariable(name = "id") Integer id, Model model){

        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário inválido ID:" +id));
        pessoaRepository.delete(pessoa);
        return "redirect:/index";
    }

    @GetMapping(value="form/{id}")
    public String updateForm (Model model, @PathVariable(name="id") int id){

        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário inválido ID:" + id));
        Pessoa pessoaForm = new Pessoa();
        pessoaForm.setNome(pessoa.getNome());
        pessoaForm.setIdade(pessoa.getIdade());
        model.addAttribute("pessoa", pessoa);
        return "atualizaForm";
    }

    @PostMapping("update/{id}")
    public String alterarPessoa (@Validated Pessoa pessoaForm, BindingResult result, @PathVariable int id){
        if (result.hasErrors()){
            return "redirect:/form";
        }
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário inválido ID:" + id));
        pessoa.setNome(pessoaForm.getNome());
        pessoa.setIdade(pessoaForm.getIdade());
        pessoaRepository.save(pessoa);
        return "redirect:/index";
    }

}
