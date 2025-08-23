package com.nerdev.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nerdev.dslist.dto.GameListDTO;
import com.nerdev.dslist.entities.Game;
import com.nerdev.dslist.entities.GameList;
import com.nerdev.dslist.projections.GameMinProjection;
import com.nerdev.dslist.repositories.GameListRepository;
import com.nerdev.dslist.repositories.GameRepository;

@Service
public class GameListService {

	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private GameListRepository gameListRepository;
	
	public List<GameListDTO> findAll(){
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList();
	}
	
	@Transactional
	public void move(Long listId, int sorceIndex, int destinationIndex){
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		GameMinProjection obj = list.remove(sorceIndex);
		list.add(destinationIndex, obj);
		
		int min = sorceIndex < destinationIndex ? sorceIndex : destinationIndex;
		int max = sorceIndex < destinationIndex ? destinationIndex : sorceIndex;
		
		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
		
	}
}
