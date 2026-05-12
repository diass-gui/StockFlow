package com.guilhermeDias.StockFlow.mapper;

import com.guilhermeDias.StockFlow.dto.EmpresaRequestDTO;
import com.guilhermeDias.StockFlow.dto.EmpresaResponseDTO;
import com.guilhermeDias.StockFlow.dto.ProdutoRequestDTO;
import com.guilhermeDias.StockFlow.dto.ProdutoResponseDTO;
import com.guilhermeDias.StockFlow.entity.Empresa;
import com.guilhermeDias.StockFlow.entity.Produto;
import java.util.List;

public class EmpresaMapper {

    public static EmpresaResponseDTO converterParaDTO(Empresa empresa) {
        EmpresaResponseDTO responseDTO = new EmpresaResponseDTO();

//        responseDTO.setId(produto.getId());
//        responseDTO.setNome(produto.getNome());
//        responseDTO.setQuantidade(produto.getQuantidade());
//        responseDTO.setPreco(produto.getPreco());
//        responseDTO.setCategoria(produto.getCategoria());

        responseDTO.setId(empresa.getId());
        responseDTO.setNome(empresa.getNome());
        responseDTO.setCnpj(empresa.getCpnj());
        responseDTO.setEmail(empresa.getEmail());
        responseDTO.setEstoque(empresa.getEstoque());

        return responseDTO;
    }

    public static Empresa converterParaEntity(EmpresaRequestDTO requestDTO) {
        Empresa empresa = new Empresa();

        empresa.setNome(requestDTO.getNome());
        empresa.setCpnj(requestDTO.getCnpj());
        empresa.setEmail(requestDTO.getEmail());
        empresa.setEstoque(requestDTO.getEstoque());

        return empresa;
    }

    public static List<EmpresaResponseDTO> converterParaDTOList(List<Empresa> empresas) {
        return empresas.stream()
                .map(EmpresaMapper::converterParaDTO)
                .toList();
    }

}
