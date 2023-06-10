package writeFile.impls;

import card.CardType;
import user.User;

import java.io.FileNotFoundException;

public interface IWriteFile {
    void writeFile(User user) throws FileNotFoundException;
}
