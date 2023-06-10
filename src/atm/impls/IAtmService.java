package atm.impls;

import card.Card;
import user.User;

import java.util.Scanner;

public interface IAtmService {
    void startAtmOperations(User user, Scanner scanner);
    void selectCard(User user, Scanner scanner);
    void checkPin(User user, Card card, Scanner scanner);
    void startCardOperation(User user, Scanner scanner);
    void putCash(User user, Card card, Scanner scanner);
    void checkBalance(User user, Card card, Scanner scanner);
    void withdrawCash(User user, Card card, Scanner scanner);

}
