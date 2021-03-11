package com.epam.task.sixth.data;

import com.epam.task.sixth.entity.Wagon;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class JSONReaderTest {
    private final JSONReader reader = new JSONReader();

    private static final String INPUT = "./src/test/resources/input.json";

    @Test
    public void testReadShouldReadWhenValidApplied() throws JSONReaderException {
        List<Wagon> expected = Arrays.asList(
                new Wagon(1, false, true),
                new Wagon(2, false, false),
                new Wagon(3, true, false),
                new Wagon(4, true, true),
                new Wagon(5, true, false),
                new Wagon(6, false, false),
                new Wagon(7, true, false),
                new Wagon(8, false, true),
                new Wagon(9, false, false),
                new Wagon(10, false, true)
        );

        List<Wagon> actual = reader.readData(INPUT);

        Assert.assertEquals(10, actual.size());

        for (int index = 0; index < 10; index++) {
            Wagon expectedWagon = expected.get(index);
            Wagon actualWagon = actual.get(index);

            Assert.assertEquals(expectedWagon.getId(), actualWagon.getId());
            Assert.assertEquals(expectedWagon.isHasPerishableFood(), actualWagon.isHasPerishableFood());
            Assert.assertEquals(expectedWagon.isLoaded(), actualWagon.isLoaded());
        }
    }

}