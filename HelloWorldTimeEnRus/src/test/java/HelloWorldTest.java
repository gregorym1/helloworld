import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.*;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Vitalii on 17.11.2016.
 */
public class HelloWorldTest{
    static HelloWorld tester;
    static ResourceBundle bundle, bundle2;
    static Locale enLocale, ruLocale;
    static LocalTime timeMorning, timeDay, timeEvening, timeNight;
    static File logFile;
    static FileWriter logWriter;

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setUp() throws IOException  {
        tester = new HelloWorld();

        timeMorning = LocalTime.of(6, 0, 0, 0);
        timeDay = LocalTime.of(9, 0, 0, 0);
        timeEvening = LocalTime.of(19, 0, 0, 0);
        timeNight = LocalTime.of(23, 0, 0, 0);

        enLocale = new Locale("en", "EN");
        ruLocale = new Locale("ru", "RU");
        bundle = ResourceBundle.getBundle("hello_world", enLocale);
        bundle2 = ResourceBundle.getBundle("hello_world", ruLocale, new UTF8Control());

        logFile = new File("debug.log");
        logWriter = new FileWriter(logFile);
    }

    @AfterClass
    public static void tearDown() throws IOException {
        logWriter.flush();
    }

    @Test
    public void testSetHelloWorldByLang() throws IOException  {
        assertEquals(true,  tester.setHelloWorld(bundle,timeDay).equals("Good day, World!"));
        write("English bundle - OK");
        assertEquals(true,  tester.setHelloWorld(bundle2,timeDay).equals("Добрый день, Мир!"));
        write("Russian bundle - OK");
    }

    @Test
    public void testSetHelloWorldByTime() throws IOException{
        assertEquals(true, tester.setHelloWorld(bundle,timeMorning).equals("Good morning, World!"));
        write("Morning time - OK");
        assertEquals(true, tester.setHelloWorld(bundle,timeDay).equals("Good day, World!"));
        write("Day time - OK");
        assertEquals(true, tester.setHelloWorld(bundle,timeEvening).equals("Good evening, World!"));
        write("Evening time - OK");
        assertEquals(true, tester.setHelloWorld(bundle,timeNight).equals("Good night, World!"));
        write("Night time - OK");
    }

    public void write(String message) throws IOException {
        logWriter.write(message);
        logWriter.append(System.getProperty("line.separator"));
    }
}
