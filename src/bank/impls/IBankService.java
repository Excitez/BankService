package bank.impls;

import user.User;

import java.util.Scanner;

public interface IBankService {
    void startOperations(User user, Scanner scanner);
    void registerUser(User user, Scanner scanner);
    void createCard(User user, Scanner scanner);
}
