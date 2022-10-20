package io.getarrays.server.repo;

import io.getarrays.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo  extends JpaRepository<Server , Long> {
    Server findByIpAdress(String ipAdress);
    Server findByName(String name);
}
