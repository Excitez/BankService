package bank;

import bank.impls.IBankService;
import card.Card;
import card.CardType;
import startApp.StartApp;
import user.User;
import writeFile.WriteFile;
import writeFile.impls.IWriteFile;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BankService implements IBankService {

    @Override
    public void startOperations(User user, Scanner scanner) {
        String[] operations = {"0 - Регистрация пользователя", "1 - Создание карты", "2 - Вернуться назад"};
        String[] answerAction = {"Для регистрации следуйте указаниями ниже.", "Для создания карты следуйте указаниями ниже.", "Вы вернулись назад"};
        actionSelect(3, operations, answerAction, user, scanner);
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
                    if(user.getName() == null && user.getSurName() == null)
                        registerUser(user, scanner);
                    else {
                        System.out.println("Вы уже зарегистрированы! Для Вас доступно: \n" + value[1] + "\n" + value[2]);
                        continue;
                    }
                    break;
                case 1:
                    System.out.println(answerAction[number]);
                    if(user.getName() != null && user.getSurName() != null)
                        createCard(user, scanner);
                    else {
                        System.out.println("Сначала зарегистрируйтесь! \n" + value[0]);
                        continue;
                    }
                    break;
                case 2:
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
    public void registerUser(User user, Scanner scanner) {
        scanner.nextLine();

        while (true) {
            System.out.println("Введите имя: ");
            String name = scanner.nextLine();
            System.out.println("Введите фамилию: ");
            String surName = scanner.nextLine();
            if(name.matches("\\p{L}+") && surName.matches("\\p{L}+")) {
                user.setName(name);
                user.setSurName(surName);
                System.out.println("Добро пожаловать " + name + " " + surName);
                startOperations(user, scanner);
                break;
            }
            else {
                System.out.println("Некорректное имя или фамилия! Попробуйте еще раз.");
            }
        }
    }

    @Override
    public void createCard(User user, Scanner scanner) {

        Card card = new Card();
        scanner.nextLine();

        while(true) {
            System.out.println("Выберите тип карты:");
            System.out.println("0 - Дебютовая");
            System.out.println("1 - Кредитная");

            int number = scanner.nextInt();
            switch (number) {
                case 0:
                    System.out.println("Вы выбрали дебютовую карту!");
                    card.setType(CardType.DEBUT);
                    break;
                case 1:
                    System.out.println("Вы выбрали кредитную карту!");
                    card.setType(CardType.CREDIT);
                    break;
                default:
                    System.out.println("Вы ввели некорректное число!");
                    continue;
            }

            while(true) {
                System.out.println("Введите 4-х значный PIN.");
                String pin = scanner.next();

                if(pin.length() == 4 && pin.matches("[0-9]*")) {
                    System.out.println("Вы ввели 4-х значный PIN");
                    card.setPin(pin);
                    break;
                }
                else {
                    System.out.println("Вы ввели некорректный PIN");
                }
            }

            while (true) {
                System.out.println("Введите первоначальный баланс:");
                long balance = scanner.nextLong();

                if(balance>0){
                    System.out.println("Ваш баланс на карте: " + balance);
                    card.setBalance(balance);
                    long idCard = (long) (4000000000000000L + Math.random() * 5999999999999999L);
                    System.out.println("Ваш ID карты: " + idCard);
                    card.setCardId(idCard);
                    user.addCard(card);
                    startOperations(user, scanner);
                    break;
                }
                else {
                    System.out.println("Баланс должен быть больше 0!");
                }
            }
            break;
        }
    }
}
