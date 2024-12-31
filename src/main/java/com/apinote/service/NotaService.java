package com.apinote.service;

import com.apinote.model.Nota;
import com.apinote.model.Usuario;
import com.apinote.model.repository.NotaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class NotaService {

    @Autowired
    NotaRepository notaRepository;


    @Transactional
    public Nota criarNota( Nota nota) {
        return notaRepository.save(nota);
    }

    public Nota atualizarNota(Long id) {
        try {
            Nota nota = notaRepository.findById(id).get();
            notaRepository.saveAndFlush(nota);
            return nota;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar nota: " + e.getMessage());
        }
        return null;
    }

    public List<Nota> listarNotas() {
        try {
            List<Nota> notas = notaRepository.findAll();
            return notas;
        } catch (Exception e) {
            System.out.println("Erro ao listar notas: " + e.getMessage());
        }
        return null;
    }

    @Transactional
    public void deletarNota(Long id) {
        try {
            notaRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro ao deletar nota: " + e.getMessage());
        }
    }
}
