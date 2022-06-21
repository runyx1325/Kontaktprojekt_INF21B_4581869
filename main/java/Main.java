import services.DataImport;
import services.Service;

import java.io.File;
import java.time.LocalDateTime;

public class Main{

    public static void main(String[] args) {
        Service service = new Service();
        DataImport.dataImportFromFile(new File("D:\\Studium\\DHBW\\2. Theoriesemester\\Programmieren\\Projekt\\contacts2021.db.txt"), service);              //Filepath on PC
        //services.DataImport.dataImportFromFile(new File("C:\\Studium\\ProgrammierenJava\\Projekt\\contacts2021.db.txt"));                                  //Filepath on LapTop

        //handling with arguments
        if(args.length == 1){                                                                                                                                  //If arguments are given
            String arg = args[0];
            arg = arg.replace("--","");                                                                                                                        //delete -- of arguments with replace

            //check first character of argument
            switch (arg.charAt(0)) {
                case 'p' -> {
                    //delete redundant stuff with replace and start personSearch
                    arg = arg.replace("personensuche=", "");
                    System.out.println(service.personSearch(arg));
                }
                case 'o' -> {
                    //delete redundant stuff with replace and start locationSearch
                    arg = arg.replace("ortssuche=", "");
                    System.out.println(service.locationSearch(arg));
                }
                case 'k' -> {
                    //delete redundant stuff with replace and start showContactPersons
                    arg = arg.replace("kontaktpersonen=", "");
                    System.out.println(service.showContactPersons(Integer.parseInt(arg)));
                }
                case 'b' -> {
                    //delete redundant stuff with replace and split argument
                    arg = arg.replace("besucher=", "");
                    String[] argSplit;
                    argSplit = arg.split(",");
                    argSplit[1] = argSplit[1].replace("\"","");
                    //start visitor to find alle visitors of location at time
                    System.out.println(service.visitorOfLocation(Integer.parseInt(argSplit[0]), LocalDateTime.parse(argSplit[1])));
                }
            }
        }else {
            //Some output-information for mistakes
            if(args.length > 1)  System.out.println("Too many arguments.");
            if(args.length == 0) System.out.println("No arguments given.");
        }
    }
}
