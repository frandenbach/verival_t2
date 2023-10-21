package com.pablomarcusvitor;

import java.util.List;

public interface IEventoRepository {
    List<Evento> todos();
    boolean cadastra(Evento evento);
}
