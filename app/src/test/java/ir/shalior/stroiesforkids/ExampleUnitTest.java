package ir.shalior.stroiesforkids;

import org.junit.Test;

import ir.shalior.stroiesforkids.model.alefbagame.AlefbaBoxer;
import ir.shalior.stroiesforkids.util.Helpers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void split_isCorrect() {
        String string[] = {
                "2500", "4000", "8000"
        };

        assertEquals(string, "2500-4000-8000".split("\\-"));
    }

    @Test
    public void r_isCorrect() {
        assertEquals(1, Math.round(0 / 3) + 1);
    }

    @Test
    public void intTaghsim() {
        assertEquals(3, Math.round(7 / 2f));
    }

    @Test
    public void random() {
        assertEquals(Helpers.randomInt(1, 2), 1);
        Helpers.randomInt(1, 2);
    }

    @Test
    public void startWithCheck() {
        String media = AlefbaBoxer.imagesPath + "alefba1.jpg";
        assertTrue(media.startsWith(AlefbaBoxer.imagesPath));
    }
}