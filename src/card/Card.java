package card;

public class Card {
    public long cardId;
    public String pin;
    public long balance;
    public CardType type;

    public void setCardId(long cardId) {this.cardId = cardId;}
    public long getCardId() {return cardId;}
    public void setPin(String pin) {this.pin = pin;}
    public String getPin() {return pin;}
    public void setBalance(long balance) {this.balance = balance;}
    public long getBalance() {return balance;}
    public void setType(CardType type) {this.type = type;}
    public CardType getType() {return type;}
    
    
    public String toString() {
        String s = getType().toString() + " " + "карта" + " " + "Номер карты: " + getCardId() + " " + "Баланс карты: " + getBalance() + " " + "PIN: " + getPin();
        return s;
    }

}
