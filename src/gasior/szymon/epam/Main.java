package gasior.szymon.epam;

import gasior.szymon.epam.Exceptions.BadFormatException;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {


        // demo
        // test6.txt has got bad extension

        try {
            FileTypeCheckImpl fileTypeCheck = new FileTypeCheckImpl(new File("resources/test1.jpg"));
            System.out.println("Is Extension is correct for test1.jpg: " + fileTypeCheck.isExtensionCorrect());
            System.out.println("\n");


            FileTypeCheckImpl fileTypeCheck2 = new FileTypeCheckImpl(new File("resources/test2.gif"));
            System.out.println("Is Extension is correct for test2.gif: " + fileTypeCheck2.isExtensionCorrect());
            System.out.println("\n");

            FileTypeCheckImpl fileTypeCheck3 = new FileTypeCheckImpl(new File("resources/test3.png"));
            System.out.println("Is Extension is correct for test3.png: " + fileTypeCheck3.isExtensionCorrect());
            System.out.println("\n");

            FileTypeCheckImpl fileTypeCheck4 = new FileTypeCheckImpl(new File("resources/test4.docx"));
            System.out.println("Is Extension is correct for test4.docx: " + fileTypeCheck4.isExtensionCorrect());
            System.out.println("\n");

            FileTypeCheckImpl fileTypeCheck5 = new FileTypeCheckImpl(new File("resources/test5.rtf"));
            System.out.println("Is Extension is correct for test5.rtf: " + fileTypeCheck5.isExtensionCorrect());
            System.out.println("\n");


            System.out.println("tesint test6.txt");
            fileTypeCheck5.setFile(new File("resources/test6.txt"));
            System.out.println("Is Extension is correct for test6.txt: " + fileTypeCheck5.isExtensionCorrect());
            System.out.println("\n");


        } catch (BadFormatException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
