package user;

import card.Card;

import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    String surName;
    List<Card> cards = new ArrayList<>();

    public void setName(String name) { this.name = name;}
    public String getName() {return name;}
    public void setSurName(String surName) {this.surName = surName;}
    public String getSurName() {return surName;}
    public List<Card> getCards() {return cards;}
    public void addCard(Card card) {this.cards.add(card);}
    public void updateCard(Card card, Card newCard) {
        int idx = this.cards.indexOf(card);
        this.cards.set(idx, newCard);
    }
}
