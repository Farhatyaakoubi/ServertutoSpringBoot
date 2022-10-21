package io.getarrays.server;

import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Server;
import io.getarrays.server.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServerApplication.class, args);
	}
	@Bean
CommandLineRunner run(ServerRepo serverRepo){
		return args -> {
			serverRepo.save(new Server(null,"191.161.1.161","Ubunto linux","16 GB","Personal PC","http://localhost:8080/server/images/s1.png", Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.162.2.162","Kali linux","32 GB","Public PC","http://localhost:8080/server/images/s2.png", Status.SERVER_UP));
			serverRepo.save(new Server(null,"193.163.3.163","MacOs","64 GB","Data","http://localhost:8080/server/images/s3.png", Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.168.1.160","Windows","128 GB","Personal PC","http://localhost:8080/server/images/s4.png", Status.SERVER_UP));
		} ;
}
}
