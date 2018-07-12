package rs.fon.inteligentni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rs.fon.inteligentni.spotifyapi.SpotifyApiManager;

@SpringBootApplication
public class SpotifyProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpotifyProxyApplication.class, args);
    }
}

	


