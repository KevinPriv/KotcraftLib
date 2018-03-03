import com.google.common.base.Stopwatch;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.TestOnly;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Test {


        private static final float PI = 3.1415927f;
        private static final float MINUS_PI = -PI;
        private static final float DOUBLE_PI = PI * 2f;
        private static final float PI_2 = PI / 2f;

        private static final float CONST_1 = 4f / PI;
        private static final float CONST_2 = 4f / (PI * PI);

        @Contract(pure = true)
        @TestOnly
        public static final float sin(float x) {
            if (x < MINUS_PI) {
                x += DOUBLE_PI;
            } else if (x > PI) {
                x -= DOUBLE_PI;
            }

            return (x < 0f) ? (CONST_1 * x + CONST_2 * x * x)
                    : (CONST_1 * x - CONST_2 * x * x);
        }

        @Contract(pure = true)
        @TestOnly
        public static final float cos(float x) {
            if (x < MINUS_PI) {
                x += DOUBLE_PI;
            } else if (x > PI) {
                x -= DOUBLE_PI;
            }

            x += PI_2;

            if (x > PI) {
                x -= DOUBLE_PI;
            }

            return (x < 0f) ? (CONST_1 * x + CONST_2 * x * x)
                    : (CONST_1 * x - CONST_2 * x * x);
        }


    public static void main(String[] args) {
        System.gc();
    }

}