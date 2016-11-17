import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloWorld {
    public static String setHelloWorld(ResourceBundle bundle, LocalTime time){
        String timeMorning = "morning",
                timeDay = "day",
                timeEvening = "evening",
                timeNight = "night";

        int hourNow = time.getHour();

        if (hourNow < 6 || hourNow == 23)
            return bundle.getString(timeNight);
        else if (hourNow < 9)
            return bundle.getString(timeMorning);
        else if (hourNow < 19)
            return bundle.getString(timeDay);
        else
            return bundle.getString(timeEvening);
    }


    public static void main(String[] args) throws Exception {
        //Получаем текст, зависящий от локали пользователя
        Locale defaultLocale = Locale.getDefault();
        ResourceBundle bundle = ResourceBundle.getBundle("hello_world", defaultLocale, new UTF8Control());

        LocalTime time = LocalTime.now();

        System.out.print(setHelloWorld(bundle,time));
    }
}
