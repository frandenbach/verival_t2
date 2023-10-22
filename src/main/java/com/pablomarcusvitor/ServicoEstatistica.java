package com.pablomarcusvitor;

import java.util.List;
import java.util.stream.Collectors;

public class ServicoEstatistica {
    private IEventoRepository eventoRep;
    private ICalculoEstatistica calculoEstatistica;

    public ServicoEstatistica(IEventoRepository eventoRepository, ICalculoEstatistica calculoEstatistica){
        this.eventoRep = eventoRepository;
        this.calculoEstatistica = calculoEstatistica;
    }

    public EstatisticasDTO calculaEstatisticas(int distancia){
        return calculoEstatistica.calculaEstatisticas(distancia);
    }

    public PerformanceDTO calculaAumentoPerformance(int ano){
        List<Evento> eventos = eventoRep
                        .todos()
                        .stream()
                        .filter(e->e.getAno() == ano)
                        .collect(Collectors.toList());
        int indiceMaiorDif1 = 0;
        int indiceMaiorDif2 = 0;
        double maiorDif = -1.0;

        for (int i = 0; i < eventos.size(); i++) {
            for (int j = i + 1; j < eventos.size(); j++) {
                Evento e1 = eventos.get(i);
                Evento e2 = eventos.get(j);
                double tempo1 = e1.getHoras() * 60 * 60 + e1.getMinutos() * 60.0 + e1.getSegundos();
                double tempo2 = e2.getHoras() * 60 * 60 + e2.getMinutos() * 60.0 + e2.getSegundos();
                double diferenca = Math.abs(tempo1 - tempo2);
    
                if (diferenca > maiorDif) {
                    maiorDif = diferenca;
                    indiceMaiorDif1 = i;
                    indiceMaiorDif2 = j;
                }
            }
        }

        return new PerformanceDTO(eventos.get(indiceMaiorDif1).getNome(),
                                  eventos.get(indiceMaiorDif2).getNome(),
                                  maiorDif);
    }
}
