package pl.cinexus123.readJson.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.cinexus123.readJson.ReadJsonService;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("")
public class FullFolderIdController {

    private ReadJsonService readJsonService;

    public FullFolderIdController(ReadJsonService readJsonService) {this.readJsonService = readJsonService;}

    @GetMapping("/GET/{folderId}")
    public List<String> getFullIdFolder(@PathVariable("folderId") String folderId) {
        log.info("Search folder with ID: " + folderId);
        return this.readJsonService.getFullIdFolder(folderId);
    }
    // change path to endpoint
    @GetMapping("/GET/name/{path}")
    public List<String> getFullPathFolder(@PathVariable("path") String path) {
        log.info("Search folder with path: " + path);
        return this.readJsonService.getFullPathFolder(path);
    }
}
