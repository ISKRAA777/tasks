package com.crud.tasks.trello.client;

import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {


    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);


    @Autowired
    private TrelloConfig trelloConfig;

    @Autowired
    private RestTemplate restTemplate;
    public List<TrelloBoardDto> getTrelloBoards() {
        URI url = getTrelloBoardsUri();
        try {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
            return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }


    private URI getTrelloBoardsUri() {
        return UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint()
                + "/members/" + trelloConfig.getTrelloUsername() + "/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build().encode().toUri();
    }


    public CreatedTrelloCardDto createNewCard(TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build().encode().toUri();
        return restTemplate.postForObject(url, null, CreatedTrelloCardDto.class);

    }
}

//import com.crud.tasks.domain.TrelloBoardDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class TrelloClient {
//    @Value("${trello.api.endpoint.prod}")
//    private String trelloApiEndpoint;
//    @Value("${trello.app.key}")
//    private String trelloAppKey;
//    @Value("${trello.app.token}")
//    private String trelloAppToken;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    public List<TrelloBoardDto> getTrelloBoards() {
//        URI url = getTrelloBoardsUri();

//        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(
//                url,
//                TrelloBoardDto[].class);
//        if (boardsResponse != null) {
//            return Arrays.asList(boardsResponse);
//        }
//        return new ArrayList<>();
//        try {
//            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
//            return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));
//        } catch (RestClientException e) {
//            return new ArrayList<>();
//        }
//    }
//
//    private URI getTrelloBoardsUri() {
//        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/alexander07592062/boards")
//                .queryParam("key", trelloAppKey)
//                .queryParam("token", trelloAppToken)
//                .queryParam("fields", "name,id")
//                .queryParam("lists", "all")
//                .build().encode().toUri();
//    }
//}
