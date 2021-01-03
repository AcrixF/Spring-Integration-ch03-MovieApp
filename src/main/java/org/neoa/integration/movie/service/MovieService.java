package org.neoa.integration.movie.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.neoa.integration.movie.model.Movie;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class MovieService {

    public String format(String contents) {
        log.info("Formatting to Json...");
        String json = "{}";

        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(parse(contents));
            log.info("Formatting finished...");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private List<Movie> parse(String contents) {
        List<Movie> movies = new ArrayList<>();
        String[] record = null;
        for (String line: contents.split(System.getProperty("line.separator"))) {
            record = Arrays.asList(line.split(","))
                    .stream()
                    .map(String::trim)
                    .toArray(String[]::new);

            movies.add(new Movie(record[0], record[1], Integer.parseInt(record[2])));
        }
        return movies;
    }

    public void locateParsedMoviesOutputFile(String fileName, String content) {
        try(FileOutputStream outputStream = new FileOutputStream("src/main/resources/output/" + fileName + ".processed");){
            byte [] contentInBytes = content.getBytes(StandardCharsets.UTF_8);
            outputStream.write(contentInBytes);
        } catch (IOException e) {
            log.info("Error when location Parsed Movies in output directory. File name: "+  fileName);
            e.printStackTrace();
        } 
    }

}
