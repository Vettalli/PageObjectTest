package helpers;

import java.util.Random;

public class RandomUtil {
    public static Integer getRandomInteger(){
        var random = new Random();
        var minNumber = 1;
        var maxNumber = 254;

        return random.nextInt(minNumber, maxNumber);
    }
}
