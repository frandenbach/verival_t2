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

        when(calculoEstatistica.calculaEstatisticas(5000)).thenReturn(new EstatisticasDTO(4720, 4720, 200));

        EstatisticasDTO estatisticas = servicoEstatistica.calculaEstatisticas(5000);

        assertEquals(4720, estatisticas.getMedia(), 0.001);
        assertEquals(4720, estatisticas.getMediana(), 0.001);
        assertEquals(200, estatisticas.getDesvioPadrao(), 0.001);
    }

    @Test
    public void testCalculaAumentoPerformance() {
        IEventoRepository eventoRepository = mock(IEventoRepository.class);
        ICalculoEstatistica calculoEstatistica = mock(ICalculoEstatistica.class);
        ServicoEstatistica servicoEstatistica = new ServicoEstatistica(eventoRepository, calculoEstatistica);

        List<Evento> listaEventos = Arrays.asList(
            new Evento(10,"POA Day RUN", 10, 3, 2022, 5000, 0, 43, 0),
            new Evento(12,"POA Night RUN", 15, 5, 2021, 5000, 0, 42,0),
            new Evento(11,"NIKE RUN", 17, 6, 2021, 21000, 1, 22,0),
            new Evento(14,"SUMMER RUN", 22, 8, 2021, 5000, 0, 41, 0),      
            new Evento(16,"SPRING RUN", 22, 8, 2022, 5000, 0, 41, 30),     
            new Evento(18,"WINTER RUN", 2, 8, 2021, 5000, 0, 42, 30),
            new Evento(20, "FALL RUN", 11, 5, 2022, 21000, 1, 15, 20)
        );

        when(eventoRepository.todos()).thenReturn(listaEventos);

        PerformanceDTO performanceDTO = servicoEstatistica.calculaAumentoPerformance(2022);

        assertEquals("SPRING RUN", performanceDTO.getProva1());
        assertEquals("FALL RUN", performanceDTO.getProva2());
        assertEquals(2030, performanceDTO.getReducao(), 0.001);
    }
}
