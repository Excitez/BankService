package card;

public enum CardType {
    DEBUT("Дебютовая"),
    CREDIT("Кредитная");
    private String translation;

    CardType(String translation) {
        this.translation = translation;
    }
}
