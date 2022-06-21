import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

public class ProjektTester {
    // Konfiguration eurer Hauptklasse. Bitte vollqualifizierten Klassennamen plus Paket angeben.
    // Z.b. die Klasse MeinProjekt im Paket dhbw.java, muss lauten: 'dhbw.java.MeinProjekt'
    private static final String MAIN_CLASS = Main.class.getName();

    public static void main(String[] args) {
        // project.model.Tests are passing
        boolean passed = true;

        // Kontaktpersonen fuer Mia
        passed = passedTestNetzwerk("--kontaktpersonen=1", "Aaron, Amelie, Ben, Emil, Emilia, Emily, Felix, Hannah, Hannes, Julius, Leonard, Levi, Louis, Malia, Marlene, Ole, Rosalie, Sophia, Victoria");
        // Kontaktpersonen fuer Emilia
        passed &= passedTestNetzwerk("--kontaktpersonen=2", "Amelie, Carla, Carlotta, Charlotte, Emma, Eva, Hannah, Hannes, Jonas, Joshua, Malia, Maria, Mattis, Melina, Mia, Noah, Ole, Sophia, Tom");
        // Kontaktpersonen fuer Ole
        passed &= passedTestNetzwerk("--kontaktpersonen=158", "Ben, Carla, Emilia, Emily, Joshua, Malia, Maria, Mia, Sophia");

        // Besucher fuer BÃ¤ckerei am "2021-05-15T14:16:00"
        passed &= passedTestNetzwerk("--besucher=1,\"2021-05-15T14:16:00\"", "Adam, Amelie, Carla, Carlotta, Charlotte, Elli, Emil, Emilia, Emily, Emma, Eva, Fiona, Hannah, Hannes, Jonah, Jonas, Joshua, Konstantin, Lian, Lisa, Luisa, Malia, Mara, Maria, Mattis, Max, Melina, Mia, Mohammed, Noah, Ole, Sophia, Tim, Tom, Toni, Victoria");
        // Besucher fuer Supermarkt am "2021-05-15T14:16:00"
        passed &= passedTestNetzwerk("--besucher=2,\"2021-05-15T14:16:00\"", "Alma, Anni, Arthur, Jannik, Mats, Mika, Paula, Pia");
        // Besucher fuer Zoo am "2021-05-15T11:00:00"
        passed &= passedTestNetzwerk("--besucher=3,\"2021-05-15T11:00:00\"", "Anna, Charlotte, Emilia, Leonie, Marie, Mia");

        if (passed) {
            System.out.println("Alle Tests bestanden :-)");
        } else {
            System.out.println("Leider nicht alle Tests bestanden :-(");
        }
    }

    /**
     * Ueberprueft ob Aufruf den erwarteten Ausgabestring beinhaltet.
     *
     * @param arg          Programmargument
     * @param resultString String, welcher als Ausgabe erwartet wird
     * @return
     */
    private static boolean passedTestNetzwerk(String arg, String resultString) {
        // Der System.out Stream muss umgebogen werden, damit dieser spaeter ueberprueft werden kann.
        PrintStream normalerOutput = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        String[] args = {arg};
        try {
            // MainClass mittels Reflection bekommen und main Methode aufrufen
            Class<?> mainClass = Class.forName(MAIN_CLASS);
            Method mainMethod = mainClass.getDeclaredMethod("main", String[].class);
            mainMethod.invoke(null, (Object) args);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Main-Klasse konnte nicht geladen werden, bitte Konfiguration pruefen.");
            System.exit(1);
        } finally {
            // System.out wieder zuruecksetzen
            System.setOut(normalerOutput);
        }

        // Ergebnisse ueberpruefen.
        String output = baos.toString();
        String[] lines = output.split(System.lineSeparator());
        // Pryefe ob eine Zeile in der Ausgabe dem Format entspricht
        for (String line : lines) {
            // keine Leerzeichen beachten
            if (line.replace(" ", "").equals(resultString.replace(" ", ""))) {
                return true;
            }
        }
        System.err.println("Feher bei: '" + arg + "'. Erwartetes Ergebnis: '" + resultString + "', erhaltenes Ergebnis: '" + output.replace(System.lineSeparator(), "") + "'");
        return false;
    }

}