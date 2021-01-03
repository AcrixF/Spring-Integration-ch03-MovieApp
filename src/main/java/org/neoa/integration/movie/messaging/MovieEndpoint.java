package org.neoa.integration.movie.messaging;

import lombok.extern.slf4j.Slf4j;
import org.neoa.integration.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

@Slf4j
@MessageEndpoint
public class MovieEndpoint {
    private MovieService service;

    @Autowired
    public MovieEndpoint(MovieService service) {
        this.service = service;
    }

    @ServiceActivator
    public void process(File input, @Headers Map<String, Object> headers) throws IOException {
        FileInputStream in = new FileInputStream(input);
        String movies = service.format(new String(StreamUtils.copyToByteArray(in)));
        in.close();
        log.info("Received: \n" + movies);
    }
}
