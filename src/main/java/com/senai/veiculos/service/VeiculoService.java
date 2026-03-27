package com.senai.veiculos.service;

import com.senai.veiculos.model.Veiculo;
import com.senai.veiculos.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

        @Autowired
        private VeiculoRepository veiculoRepository;

        public List<Veiculo> listarVeiculos() {
            return veiculoRepository.findAll();
        }

    public Veiculo cadastrarVeiculo(Veiculo veiculo) {

            return veiculoRepository.save(veiculo);
    }
}
