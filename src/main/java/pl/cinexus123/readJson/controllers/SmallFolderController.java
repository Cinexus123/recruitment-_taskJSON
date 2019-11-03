package pl.cinexus123.readJson.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.cinexus123.readJson.ReadJsonService;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("")
public class SmallFolderController {

    private ReadJsonService readJsonService;

    public SmallFolderController(ReadJsonService readJsonService) {
        this.readJsonService = readJsonService;
    }

    @GetMapping("/query={query}")
    public List<String> getAppropriateContentFolders(@PathVariable("query") String query) {
        log.info("Search word: " + query);
        return this.readJsonService.findAppropriateContentFolders(query);
    }

    @GetMapping("/listFolders")
    public Set<String> getListAvailableFolders() {
        log.info("Search list of available folders");
        return this.readJsonService.findAllAvailableFolders();
    }

}
