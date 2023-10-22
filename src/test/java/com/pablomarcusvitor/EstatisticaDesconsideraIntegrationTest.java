package com.pablomarcusvitor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class EstatisticaDesconsideraIntegrationTest {
    
    @Test
    public void testEstatisticaDesconsideraIntegration() {

        IEventoRepository eventoRepository = mock(IEventoRepository.class);

        List<Evento> listaEventos = Arrays.asList(
            new Evento(10, "POA Day RUN", 10, 3, 2022, 5000, 0, 43, 0),
            new Evento(12, "POA Night RUN", 15, 5, 2021, 5000, 0, 42, 0),
            new Evento(11, "NIKE RUN", 17, 6, 2021, 21000, 1, 22, 0),
            new Evento(14, "SUMMER RUN", 22, 8, 2021, 5000, 0, 41, 0),
            new Evento(16, "SPRING RUN", 22, 8, 2022, 5000, 0, 41, 30),
            new Evento(18, "WINTER RUN", 2, 8, 2021, 5000, 0, 42, 30),
            new Evento(20, "FALL RUN", 11, 5, 2022, 21000, 1, 15, 20)
        );

        when(eventoRepository.todos()).thenReturn(listaEventos);

        EstatisticaDesconsidera estatisticaDesconsidera = new EstatisticaDesconsidera(eventoRepository);
        ServicoEstatistica servicoEstatistica = new ServicoEstatistica(eventoRepository, estatisticaDesconsidera);

        EstatisticasDTO estatisticasDTO = servicoEstatistica.calculaEstatisticas(5000);

        assertEquals(2490, estatisticasDTO.getMedia(), 0.001);
        assertEquals(2490, estatisticasDTO.getMediana(), 0.001);
        assertEquals(24.494897, estatisticasDTO.getDesvioPadrao(), 0.001);
    }
}
