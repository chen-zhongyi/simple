package com.chen.controllers;

import com.chen.model.person.Card;
import com.chen.service.person.CardService;
import com.chen.vos.CardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public Card add(@RequestBody CardVO vo) {
        Card card = cardService.add(vo);
        return card;
    }

}
