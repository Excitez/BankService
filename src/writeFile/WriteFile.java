package writeFile;

import card.Card;
import card.CardType;
import user.User;
import writeFile.impls.IWriteFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class WriteFile implements IWriteFile {
    @Override
    public void writeFile(User user) throws FileNotFoundException {
        File file = new File("dateBase.txt");
        PrintWriter pw = new PrintWriter(file);
        pw.println("Имя:" + user.getName());
        pw.println("Фамилия: " + user.getSurName());
        if(user.getCards().isEmpty()) {
            pw.println("Банковских карт нет!");
        }
        else {
            pw.println("Банковские карты:");
            for (Card card: user.getCards()) {
                pw.println(card.toString());
            }
        }
        pw.close();
    }
}
