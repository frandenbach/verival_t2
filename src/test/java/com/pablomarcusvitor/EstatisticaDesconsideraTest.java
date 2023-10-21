package com.pablomarcusvitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class EstatisticaDesconsideraTest {

    @Test
    public void testCalculaEstatisticas() {

        IEventoRepository evRep = mock(IEventoRepository.class);

        List<Evento> listaEventos = Arrays.asList(
            new Evento(10,"POA Day RUN", 10, 3, 2021, 5000, 0, 43, 0),
            new Evento(12,"POA Night RUN", 15, 5, 2021, 5000, 0, 42,0),
            new Evento(11,"NIKE RUN", 17, 6, 2021, 21000, 1, 22,0),
            new Evento(14,"SUMMER RUN", 22, 8, 2021, 5000, 0, 41, 0),      
            new Evento(16,"SPRING RUN", 22, 8, 2021, 5000, 0, 41, 30),      
            new Evento(18,"WINTER RUN", 2, 8, 2021, 5000, 0, 42, 30),
            new Evento(20, "FALL RUN", 11, 5, 2022, 21000, 1, 15, 20)
        );

        when(evRep.todos()).thenReturn(listaEventos);

        EstatisticaDesconsidera estatistica = new EstatisticaDesconsidera(evRep);
        EstatisticasDTO resultado5000 = estatistica.calculaEstatisticas(5000);

        assertEquals(2520, resultado5000.getMedia(), 0.001);
        assertEquals(2520, resultado5000.getMediana(), 0.001);
        assertEquals(42.4264068, resultado5000.getDesvioPadrao(), 0.001);
    }

    @Test
    public void testCalculaEstatisticas21000() {

        IEventoRepository evRep = mock(IEventoRepository.class);

        List<Evento> listaEventos = Arrays.asList(
            new Evento(10,"POA Day RUN", 10, 3, 2021, 5000, 0, 43, 0),
            new Evento(12,"POA Night RUN", 15, 5, 2021, 5000, 0, 42,0),
            new Evento(11,"NIKE RUN", 17, 6, 2021, 21000, 1, 22,0),
            new Evento(14,"SUMMER RUN", 22, 8, 2021, 5000, 0, 41, 0),      
            new Evento(16,"SPRING RUN", 22, 8, 2021, 5000, 0, 41, 30),      
            new Evento(18,"WINTER RUN", 2, 8, 2021, 5000, 0, 42, 30),
            new Evento(13, "FALL RUN", 11, 5, 2022, 21000, 1, 15, 20)
        );

        when(evRep.todos()).thenReturn(listaEventos);

        EstatisticaDesconsidera estatistica = new EstatisticaDesconsidera(evRep);
        EstatisticasDTO resultado21000 = estatistica.calculaEstatisticas(21000);

        assertEquals(4720, resultado21000.getMedia(), 0.001);
        assertEquals(4720, resultado21000.getMediana(), 0.001);
        assertEquals(200, resultado21000.getDesvioPadrao(), 0.001);
    }
}