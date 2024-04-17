package etf.unibl.org.ip.ipbackend.services.implementations;

import etf.unibl.org.ip.ipbackend.services.LoggingService;
import etf.unibl.org.ip.ipbackend.services.StorageAccessService;
import io.jsonwebtoken.io.Encoders;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class StorageAccessServiceImpl implements StorageAccessService {
    @Value("${storage.path}")
    private String storagePath;

    @Value("${storage.name}")
    private String storageName;

    private Path rootPath;

    private final LoggingService loggingService;

    @PostConstruct
    private void postConstruct() {
        rootPath = Paths.get(storagePath, storageName).normalize().toAbsolutePath();
        File rootFolder = rootPath.toFile();
        if (!rootFolder.exists()) {
            try {
                Files.createDirectory(rootPath);
            } catch (IOException e) {
                loggingService.log(LogLevel.FATAL, "Couldn't create folder");
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public synchronized String saveToFile(String fileName, byte[] content) {
        String name = new File(fileName).getName();
        String extension = name.substring(name.lastIndexOf("."));
        try {
            Path filePath = getUniqueFilePath(extension);
            Files.write(filePath, content);
            return filePath.toFile().getName();
        } catch (IOException e) {
            loggingService.log(LogLevel.FATAL, "Couldn't write file");
            return null;
        }
    }

    @Override
    public Resource getFileAsResource(String fileName) {
        try {
            Path path = getFilePath(fileName);
            if (!Files.exists(path)) {
                throw new IOException("File doesn't exits");
            }
            return new UrlResource(path.toUri());
        } catch (IOException ex) {
            loggingService.log(LogLevel.INFO, "File not found");
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    private Path getFilePath(String fileName) throws IOException {
        Path destination = rootPath.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
        if (!destination.getParent().equals((rootPath.toAbsolutePath())))
            throw new IOException("cannot access files outside storage");
        return destination;
    }

    private Path getUniqueFilePath(String extension) {
        String uniqueFileName = new Date(System.currentTimeMillis()).getTime() + "_IP";
        String encodedFileName = Encoders.BASE64.encode(uniqueFileName.getBytes());
        return Path.of(rootPath.toFile().getPath(), encodedFileName + extension);
    }


}
