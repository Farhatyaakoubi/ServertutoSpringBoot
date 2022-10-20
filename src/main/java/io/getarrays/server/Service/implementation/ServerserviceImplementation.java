package io.getarrays.server.Service.implementation;

import io.getarrays.server.Service.ServerService;
import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Server;
import io.getarrays.server.repo.ServerRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerserviceImplementation  implements ServerService {
private final ServerRepo serverRepo;
    @Override
    public Server create(Server server) {
        log.info("Saving new server :{}",server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }



    @Override
    public Server ping(String ipAdress) throws IOException {
        log.info("Pinging server IP :{}",ipAdress);
        Server server = serverRepo.findByIpAdress(ipAdress);
        InetAddress address =InetAddress.getByName(ipAdress);
        server.setStatut(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN) ;
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> List(int Limit) {
        log.info("Fetching all servers");
        return serverRepo.findAll(PageRequest.of( page : 0, Limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching all server by ID : {}",id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(@NotNull Server server) {
        log.info("Updating server :{}",server.getName());
        return serverRepo.save(server);
    }

    @Override
    public boolean delete(Long id) {
        log.info("Deleting  server by ID : {}",id);
        serverRepo.deleteById(id);
        return Boolean.TRUE;
    }

    private String setServerImageUrl() {
        String[] imageNames = {"s1.jpg","s2.jpg","s3.jpg","s4.jpg"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/images" + imageNames [new Random().nextInt(4)]).toUriString();
    }
}
