package com.codedifferently.MovieApiV3.domain.actor.service;

import com.codedifferently.MovieApiV3.domain.actor.models.Actor;

public interface ActorService  {
    Actor create(Actor item);
    Actor findById(Long id);
    Actor findByAlias(String alias);
    Iterable<Actor> findAll();
    Actor update(Actor item);
    void delete(Long id);
}
