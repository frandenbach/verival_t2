package com.pablomarcusvitor;

import java.util.List;
import java.util.stream.Collectors;

public class EstatisticaDesconsidera implements ICalculoEstatistica {
    private IEventoRepository eventoRep;

    public EstatisticaDesconsidera(IEventoRepository eventoRep) {
        this.eventoRep = eventoRep;
    }

    public EstatisticasDTO calculaEstatisticas(int distancia) {
        // Seleciona os eventos da distancia informada
        List<Evento> eventos = eventoRep.todos().stream().filter(e -> e.getDistancia() == distancia)
                .collect(Collectors.toList());
        // Obtém um stream com os valores ordenados
        List<Double> valores = eventos.stream()
                .map(e -> e.getHoras() * 60 * 60 + e.getMinutos() * 60.0 + e.getSegundos()).sorted()
                .collect(Collectors.toList());
        // Se tem mais de 2 elementos remove o primeiro e o último
        
        /* Conferir
        if (eventos.size() >= 3){
            eventos.remove(0);
            eventos.remove(eventos.size()-1);
        }
        */

        // Calcula a média
        Double media = valores.stream().mapToDouble(v -> v).average().orElse(Double.NaN);
        // Calcula mediana
        Double mediana = Double.NaN;
        if (valores.size() > 0) {
            mediana = ((valores.size() % 2 == 0)
                    ? ((valores.get(valores.size() / 2 - 1)) + (valores.get(valores.size() / 2))) / 2.0
                    : (valores.get(valores.size() / 2)));
        }
        // Calcula o desvio padrao
        double variancia;
        double desvioPadrao = Double.NaN;
        if (mediana != Double.NaN) {
            variancia = valores.stream().mapToDouble(v -> v - media).map(v -> v * v).average().getAsDouble();
            desvioPadrao = Math.sqrt(variancia);
        }
        return new EstatisticasDTO(media, mediana, desvioPadrao);
    }
}
