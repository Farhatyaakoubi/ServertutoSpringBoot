package io.getarrays.server.Service;

import io.getarrays.server.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    Server create(Server server);
    Server ping (String ipAdress) throws IOException;
    Collection<Server> List(int Limit);
    Server get(Long id);
    Server update(Server server);
    boolean delete(Long id);
}
