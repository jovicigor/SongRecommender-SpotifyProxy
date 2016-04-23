package rs.fon.inteligentni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import rs.fon.inteligentni.spotifyapi.SpotifyApiManager;
import rs.fon.inteligentni.spotifyapi.SpotifyApiManagerImpl;

@SpringBootApplication
@ComponentScan(basePackages={"rs.fon.inteligentni.rest.resource","rs.fon.inteligentni.rest.exception.mapper"})
@PropertySource(value={"classpath:application.properties","classpath:custom.properties"})
public class SpotifyProxyApplication{

	public static void main(String[] args) {
		SpringApplication.run(SpotifyProxyApplication.class, args);
	}
	
	@Bean
	public SpotifyApiManager spotifyApiManager(){
		return new SpotifyApiManagerImpl();
	}	
}

	


