package readFile;

import readFile.impls.IReadFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile implements IReadFile {
    @Override
    public void readFile() throws FileNotFoundException {
        File file = new File("dateBase.txt");
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()) {
           System.out.println(scanner.nextLine());
        }

        scanner.close();
    }
}
