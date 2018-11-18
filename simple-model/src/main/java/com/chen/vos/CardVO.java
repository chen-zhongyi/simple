package com.chen.vos;

import com.chen.model.person.Card;

public class CardVO extends OneData {
    private Long cardId;
    private Long personId;
    private String number;
    private Integer type;

    public CardVO() {}

    public CardVO(Card card) {
        this.cardId = card.getId();
        this.personId = card.getPerson() != null ? card.getPerson().getId() : null;
        this.number = card.getNumber();
        this.type = card.getType() != null ? card.getType().code() : null;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
