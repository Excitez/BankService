package startApp;

import atm.AtmService;
import atm.impls.IAtmService;
import bank.BankService;
import bank.impls.IBankService;
import card.Card;
import readFile.ReadFile;
import readFile.impls.IReadFile;
import user.User;
import writeFile.WriteFile;
import writeFile.impls.IWriteFile;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StartApp {

    static IAtmService atmService = new AtmService();
    static IBankService bankService = new BankService();
    static IWriteFile writeFile = new WriteFile();
    static IReadFile readFile = new ReadFile();
    static User user = new User();
    static Scanner scanner = new Scanner(System.in);
    public StartApp() {}
    public static void main(String[] args) {startOperations();}

    public static void startOperations() {
        String[] action = {"0 - Зайти в банк", "1 - Подойти к банкомату", "2 - Завершение программы"};
        String[] answerAction = {"Вы зашли в банк.", "Вы подошли к банкомату.", "Вы завершили программу."};
        actionSelect(3, action, answerAction);
    }
    public static void actionSelect (int valueOperation, String[] value, String[] answerAction)  {
        System.out.println("Выберите действие: ");
        for (int i = 0; i < valueOperation; i++) {
            System.out.println(value[i]);
        }
        while (true) {
            try {
                int number = scanner.nextInt();
                switch (number){
                    case 0:
                        System.out.println(answerAction[number]);
                        bankService.startOperations(user, scanner);
                        break;
                    case 1:
                        System.out.println(answerAction[number]);
                        if(!user.getCards().isEmpty()) {
                            atmService.startAtmOperations(user, scanner);
                        }
                        else {
                            System.out.println("У вас нет карты! Создайте ее в банке! \n" + value[0]);
                            continue;
                        }

                        break;
                    case 2:
                        System.out.println(answerAction[number]);
                        if(user.getName() != null && user.getSurName() != null) {
                            writeFile.writeFile(user);
                            readFile.readFile();
                        }
                        else {
                            System.out.println("Пакеда!");
                        }
                        break;
                    default:
                        System.out.println("Вы ввели число не из списка, попробуйте еще раз.");
                        continue;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Вы ввели некорректные символы, попробуйте еще раз. Перезапустите программу!");
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Данные отсутствуют!");
            }
            break;
        }

    }
}
