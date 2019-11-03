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
        log.info("Search word: " + folderId);
        return this.readJsonService.findAppropriateContentFolders(folderId);
    }
}
