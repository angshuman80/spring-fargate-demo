package com.demo.app.controller;

import com.demo.app.model.Tweet;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@AllArgsConstructor
public class WebClientController {
    private static final Logger log = LogManager.getLogger(WebClientController.class);

    @GetMapping("/slow-service-tweets")
    private List<Tweet> getAllTweets() throws InterruptedException {
        Thread.sleep(2000L); // delay
        return Arrays.asList(
                new Tweet("RestTemplate rules", "@user1"),
                new Tweet("WebClient is better", "@user2"),
                new Tweet("OK, both are useful", "@user1"));
    }

    @GetMapping("/tweets-blocking")
    public List<Tweet> getTweetsBlocking() {
        long startTime = System.currentTimeMillis();
        log.info("Starting BLOCKING Controller!  " + System.currentTimeMillis());
        final String uri = getSlowServiceUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Tweet>> response = restTemplate.exchange(
                uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Tweet>>(){});

        List<Tweet> result = response.getBody();
        result.forEach(tweet -> log.info(tweet.toString()));
        log.info("Exiting BLOCKING Controller!  " + (System.currentTimeMillis()-startTime));
        return result;
    }

    @GetMapping(value = "/tweets-non-blocking")
    public Flux<Tweet> getTweetsNonBlocking() {
        long startTime = System.currentTimeMillis();
        log.info("Starting NON-BLOCKING Controller! "+startTime);
        Flux<Tweet> tweetFlux = WebClient.create()
                .get()
                .uri(getSlowServiceUri())
                .retrieve()
                .bodyToFlux(Tweet.class);

        tweetFlux.subscribe(tweet -> log.info(tweet.toString()));
        log.info("Exiting NON-BLOCKING Controller! "+ (System.currentTimeMillis()-startTime));
        return tweetFlux;
    }

    private String getSlowServiceUri(){
        return "http://localhost:8089/api/slow-service-tweets";
    }
}
