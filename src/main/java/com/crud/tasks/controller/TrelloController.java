package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {


        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        return trelloBoards;
//        trelloBoards.forEach(trelloBoardDto -> {
//
//            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
//
//            System.out.println("This board contains lists: ");
//
//            trelloBoardDto.getLists().forEach(trelloList ->
//                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
//
//        });
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCardDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }

}
//      public CreatedTrelloCardDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
//           return trelloClient.createNewCard(trelloCardDto);

//        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
////        trelloBoards.stream().filter(dto -> dto.getName() != null).filter(dto -> dto.getId() != null).filter(dto -> dto.getName().contains("Kodilla")).forEach(trelloBoardDto -> {
////            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
////            System.out.println("This board contains lists: ");
////        });
////        trelloBoards.forEach(i -> System.out.println(i.getName() + " - " + i.getId()));
// GET request

