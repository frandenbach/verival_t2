package com.pablomarcusvitor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class EstatisticaDesconsideraTest {

    @Test
    public void testCalculaEstatisticasDescMaisDe2() {

        IEventoRepository eventoRep = mock(IEventoRepository.class);

        List<Evento> listaEventos = Arrays.asList(
            new Evento(20,"Corrida Pela Vida", 5, 4, 2023, 8000, 0, 45, 20),
            new Evento(21,"Corrida Diabetes", 6, 5, 2023, 8000, 0, 50,0),
            new Evento(22,"Maratona POA Run", 7, 6, 2023, 25000, 1, 30,0),
            new Evento(23,"Corrida Contra Alcoolismo", 8, 7, 2023, 8000, 1, 10, 0),      
            new Evento(24,"Corrida Dia dos Pais", 9, 8, 2022, 8000, 0, 55, 30),     
            new Evento(25,"October Run", 31, 10, 2023, 8000, 0, 40, 30),
            new Evento(26, "Maratona Guaíba", 10, 9, 2022, 25000, 3, 15, 0)
        );

        when(eventoRep.todos()).thenReturn(listaEventos);

        EstatisticaDesconsidera estatistica = new EstatisticaDesconsidera(eventoRep);
        EstatisticasDTO resultadoMaisDe2 = estatistica.calculaEstatisticas(8000);

        assertEquals(3510, resultadoMaisDe2.getMedia(), 0.001);
        assertEquals(3330, resultadoMaisDe2.getMediana(), 0.001);
        assertEquals(506.162029, resultadoMaisDe2.getDesvioPadrao(), 0.001);
    }

    @Test
    public void testCalculaEstatisticasDescMenosDe3() {
        IEventoRepository eventoRep = mock(IEventoRepository.class);

        List<Evento> listaEventos = Arrays.asList(
            new Evento(20,"Corrida Pela Vida", 5, 4, 2023, 8000, 0, 45, 20),
            new Evento(21,"Corrida Diabetes", 6, 5, 2023, 8000, 0, 50,0),
            new Evento(22,"Maratona POA Run", 7, 6, 2023, 25000, 1, 30,0),
            new Evento(23,"Corrida Contra Alcoolismo", 8, 7, 2023, 8000, 1, 10, 0),      
            new Evento(24,"Corrida Dia dos Pais", 9, 8, 2022, 8000, 0, 55, 30),     
            new Evento(25,"October Run", 31, 10, 2023, 8000, 0, 40, 30),
            new Evento(26, "Maratona Guaíba", 10, 9, 2022, 25000, 3, 15, 0)
        );

        when(eventoRep.todos()).thenReturn(listaEventos);

        EstatisticaDesconsidera estatistica = new EstatisticaDesconsidera(eventoRep);
        EstatisticasDTO resultadoMenosDe3 = estatistica.calculaEstatisticas(25000);

        assertEquals(8550, resultadoMenosDe3.getMedia(), 0.001);
        assertEquals(8550, resultadoMenosDe3.getMediana(), 0.001);
        assertEquals(3150, resultadoMenosDe3.getDesvioPadrao(), 0.001);
    }

}