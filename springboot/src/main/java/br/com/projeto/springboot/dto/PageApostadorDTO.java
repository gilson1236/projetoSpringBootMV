package br.com.projeto.springboot.dto;

import java.util.List;

public class PageApostadorDTO {

    private List<ApostadorDTO> apostadores;

    private long totaElements;

    private int totalPages;

    public PageApostadorDTO(List<ApostadorDTO> apostadores, long totaElements, int totalPages){
        this.apostadores = apostadores;
        this.totaElements = totaElements;
        this.totalPages = totalPages;
    }

    public List<ApostadorDTO> getApostadores() {
        return apostadores;
    }

    public long getTotaElements() {
        return totaElements;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
