package com.epam.task.sixth.data;

import com.epam.task.sixth.entity.Wagon;
import com.epam.task.sixth.entity.Wagons;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONReader {

    public List<Wagon> readData(String fileName) throws JSONReaderException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            File file = new File(fileName);

            Wagons wagons = objectMapper.readValue(file, Wagons.class);

            return wagons.getWagons();

        } catch (IOException e) {
            throw new JSONReaderException(e);
        }
    }
}
