package etf.unibl.org.ip.ipbackend.controllers;

import etf.unibl.org.ip.ipbackend.services.StorageAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${base-url}/storage")
public class StorageController {
    private final StorageAccessService storageAccessService;

    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
        Resource resource = storageAccessService.getFileAsResource(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Content-Disposition").body(resource);
    }
}
