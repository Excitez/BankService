package atm;

import atm.impls.IAtmService;
import card.Card;
import card.CardType;
import startApp.StartApp;
import user.User;

import java.util.Scanner;


public class AtmService implements IAtmService {


    Card card = new Card();
    @Override
    public void startAtmOperations(User user, Scanner scanner) {
        System.out.println("Выберите одну из карты:");
        int i = 0;
        for(Card card: user.getCards()) {
            System.out.println(i + " - " + card.toString());
            i++;
        }
        selectCard(user, scanner);
    }


    public void selectCard(User user, Scanner scanner) {

        while(true) {
            int number = scanner.nextInt();
            System.out.println(user.getCards().size());
            if (number >= user.getCards().size() || number < 0) {
                System.out.println("Вы ввели некорректное число");
            } else {
                card = user.getCards().get(number);
                System.out.println("Вы выбрали карту: " + card);
                startCardOperation(user, scanner);
                break;
            }
        }
    }

    @Override
    public void startCardOperation(User user, Scanner scanner) {
        String[] operations = {"0 - Положить деньги на баланс", "1 - Снять деньги с баланса", "2 - Узнать баланс на карте","3 - Вернуться назад"};
        String[] answerAction = {"Чтобы положить деньги, введите PIN: ", "Чтобы снять деньги, введите PIN: ", "Чтобы узнать баланс, введите PIN: ", "Вы вернулись назад"};
        actionSelect(4, operations, answerAction, user, scanner);
    }

    public void actionSelect(int valueOperation, String[] value, String[] answerAction, User user, Scanner scanner)  {
        System.out.println("Выберите действие: ");
        for (int i = 0; i < valueOperation; i++) {
            System.out.println(value[i]);
        }
        while (true) {
            int number = scanner.nextInt();
            switch (number){
                case 0:
                    System.out.println(answerAction[number]);
                    putCash(user, card, scanner);
                    break;
                case 1:
                    System.out.println(answerAction[number]);
                    withdrawCash(user, card, scanner);
                    break;
                case 2:
                    System.out.println(answerAction[number]);
                    checkBalance(user, card, scanner);
                    break;
                case 3:
                    System.out.println(answerAction[number]);
                    StartApp.startOperations();
                    break;
                default:
                    System.out.println("Вы ввели число не из списка, попробуйте еще раз.");
                    continue;
            }
            break;
        }
    }

    @Override
    public void checkPin(User user, Card card, Scanner scanner) {
        while (true) {
            String pin = scanner.next();
            if (card.getPin().equals(pin)) {
                System.out.println("Вы ввели верный PIN.");
                break;
            } else {
                System.out.println("Неправильный PIN");
            }
        }
    }

    @Override
    public void putCash(User user, Card card, Scanner scanner) {
        checkPin(user, card, scanner);
        System.out.println("Введите сумму, которую хотите положить на карту: ");
        while(true) {
            int cash = scanner.nextInt();
            if (cash <= 0) {
                System.out.println("Вы ввели некорректное число! Попробуйте снова.");
            }
            else {
                card.balance += cash;
                System.out.println("Вы успешно пополнили баланс! Ваш новый баланс: " + card.getBalance());
                user.updateCard(card, card);
                startCardOperation(user, scanner);
                break;
            }
        }
    }

    @Override
    public void checkBalance(User user, Card card, Scanner scanner) {
        checkPin(user, card, scanner);
        System.out.println("Ваш баланс на карте: " + card.getBalance());
        startCardOperation(user,scanner);
    }

    @Override
    public void withdrawCash(User user, Card card, Scanner scanner) {
        checkPin(user, card, scanner);
        System.out.println("Введите сумму, которую хотите снять с карту: ");
        while(true) {
            int cash = scanner.nextInt();
            if(card.getType().equals(CardType.DEBUT)) {
                if (cash > card.getBalance()) {
                    System.out.println("Вы ввели некорректное число! Введите сумму, которую хотите снять: .");
                } else {
                    card.balance -= cash;
                    System.out.println("Вы успешно сняли с баланса! Ваш новый баланс: " + card.getBalance());
                    user.updateCard(card, card);
                    startCardOperation(user, scanner);
                    break;
                }
            }
            else if(card.getType().equals(CardType.CREDIT)) {
                card.balance -= cash;
                System.out.println("Вы успешно сняли с баланса! Ваш новый баланс: " + card.getBalance());
                user.updateCard(card, card);
                startCardOperation(user, scanner);
                break;
            }
        }
    }
}
