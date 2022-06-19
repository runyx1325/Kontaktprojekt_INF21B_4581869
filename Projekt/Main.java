package Projekt;

import java.io.File;
import java.time.LocalDateTime;

public class Main{

    public static void main(String[] args) {

        Service service = new Service();

        DataImport.dataImportFromFile(new File("D:\\Studium\\DHBW\\2. Theoriesemester\\Programmieren\\Projekt\\contacts2021.db.txt"), service);     //Home
        //DataImport.dataImportFromFile(new File("C:\\Studium\\ProgrammierenJava\\Projekt\\contacts2021.db.txt"));                                  //Bosch

        //Umgang mit Argumenten
        //personensuche, wenn:              --personensuche="Ila"
        //ortssuche, wenn:                  --ortssuche="Markt"
        //kontaktpersonen anzeigen, wenn:   --kontaktpersonen=1
        //besucher, wenn:                   --besucher=1,"2021-05-15T14:16:00"

        if(args.length > 0){
            String arg = args[0];
            arg = arg.replace("--","");
            switch (arg.charAt(0)) {
                case 'p' -> {
                    arg = arg.replace("personensuche=", "");
                    arg = arg.substring(1, arg.length() - 1);
                    service.personSearch(arg);
                }
                case 'o' -> {
                    arg = arg.replace("ortssuche=", "");
                    arg = arg.substring(1, arg.length() - 1);
                    service.locationSearch(arg);
                }
                case 'k' -> {
                    arg = arg.replace("kontaktpersonen=", "");
                    System.out.println(service.showContactPersons(Integer.parseInt(arg)));
                }
                case 'b' -> {
                    arg = arg.replace("besucher=", "");
                    String[] argSplit;
                    argSplit = arg.split(",");
                    argSplit[1] = argSplit[1].substring(1, argSplit[1].length()-1);
                    System.out.println(service.visitor(Integer.parseInt(argSplit[0]), LocalDateTime.parse(argSplit[1])));
                }
                default -> System.out.println("This argument does not exist.");
            }
        }else {
            //System.out.println(service.showContactPersons(66));
            System.out.println(service.visitor(1,LocalDateTime.parse("2021-05-15T14:16:00")));
        }









    }
}
