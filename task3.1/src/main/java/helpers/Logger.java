package helpers;

public class Logger {

    private static org.apache.log4j.Logger Log = org.apache.log4j.Logger.getLogger(Logger.class.getName());

    public static void startTestCase(String sTestCaseName){
        Log.info("Starting test:                  "+sTestCaseName);
    }

    public static void endTestCase(String sTestCaseName){
        Log.info("Test "+sTestCaseName+" is over");
    }


    public static void info(String message) {
        Log.info(message);
    }
}
