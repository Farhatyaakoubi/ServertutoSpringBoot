package io.getarrays.server.enumeration.ressource;

import io.getarrays.server.Service.implementation.ServerserviceImplementation;
import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Response;
import io.getarrays.server.model.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import static io.getarrays.server.enumeration.Status.SERVER_UP;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerRessource {
    private final ServerserviceImplementation serverService;
@GetMapping("/list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers", serverService.List(30)))
                        .message("Servers retreived")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/ping/ipAdress")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress")String ipAddress) throws IOException {
    Server server =serverService.ping(ipAddress);
    return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", server))
                        .message(server.getStatut()==  SERVER_UP ? "Ping Success" : "Ping failed")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );

    }




    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", serverService.create(server)))
                        .message("Server created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );

    }



    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id){
                return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", serverService.get(id)))
                        .message("server retreived")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );

    }




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("deleted", serverService.delete(id)))
                        .message("server deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );

    }


    @GetMapping(path="/images/{filename}",produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage (@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user")+" D:/SPRING.BOOT-PROJECTS/server/images" + filename));

    }

}
