package etf.unibl.org.ip.ipbackend.services;

import org.springframework.core.io.Resource;

import java.io.IOException;

public interface StorageAccessService {
    String saveToFile(String fileName, byte[] content);

    Resource getFileAsResource(String fileName) throws IOException;
}
