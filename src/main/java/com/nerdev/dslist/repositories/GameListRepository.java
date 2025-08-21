package com.nerdev.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nerdev.dslist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long>{

}
