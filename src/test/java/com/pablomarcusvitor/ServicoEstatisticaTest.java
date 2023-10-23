package com.pablomarcusvitor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class ServicoEstatisticaTest {

    @Test
    public void testCalculaEstatisticas() {
        IEventoRepository eventoRepository = mock(IEventoRepository.class);
        ICalculoEstatistica calculoEstatistica = mock(ICalculoEstatistica.class);
        ServicoEstatistica servicoEstatistica = new ServicoEstatistica(eventoRepository, calculoEstatistica);

        when(calculoEstatistica.calculaEstatisticas(25000)).thenReturn(new EstatisticasDTO(8550, 8550, 3150));

        EstatisticasDTO estatisticas = servicoEstatistica.calculaEstatisticas(25000);

        assertEquals(8550, estatisticas.getMedia(), 0.001);
        assertEquals(8550, estatisticas.getMediana(), 0.001);
        assertEquals(3150, estatisticas.getDesvioPadrao(), 0.001);
    }

    @Test
    public void testCalculaAumentoPerformance() {
        IEventoRepository eventoRepository = mock(IEventoRepository.class);
        ICalculoEstatistica calculoEstatistica = mock(ICalculoEstatistica.class);
        ServicoEstatistica servicoEstatistica = new ServicoEstatistica(eventoRepository, calculoEstatistica);

        List<Evento> listaEventos = Arrays.asList(
            new Evento(20,"Corrida Pela Vida", 5, 4, 2023, 8000, 0, 45, 20),
            new Evento(21,"Corrida Diabetes", 6, 5, 2023, 8000, 0, 50,0),
            new Evento(22,"Maratona POA Run", 7, 6, 2023, 25000, 1, 30,0),
            new Evento(23,"Corrida Contra Alcoolismo", 8, 7, 2023, 8000, 1, 10, 0),      
            new Evento(24,"Corrida Dia dos Pais", 9, 8, 2022, 8000, 0, 55, 30),     
            new Evento(25,"October Run", 31, 10, 2023, 8000, 0, 40, 30),
            new Evento(26, "Maratona Guaíba", 10, 9, 2022, 25000, 3, 15, 0)
        );

        when(eventoRepository.todos()).thenReturn(listaEventos);

        PerformanceDTO performanceDTO = servicoEstatistica.calculaAumentoPerformance(2023);

        assertEquals("Maratona POA Run", performanceDTO.getProva1());
        assertEquals("October Run", performanceDTO.getProva2());
        assertEquals(2970.0, performanceDTO.getReducao(), 0.001);
    }
}
