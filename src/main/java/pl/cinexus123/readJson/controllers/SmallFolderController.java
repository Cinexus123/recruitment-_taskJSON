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

    @GetMapping("/query={query}&skip={skip}&limit={limit}")
    public List<String> getAppropriateContentFolders(@PathVariable("query") String query,@PathVariable("skip") Integer skip,@PathVariable("limit") Integer limit) {
        log.info("Search word: " + query + "with skip: " + skip + "and limit: " + limit);
        return this.readJsonService.findAppropriateContentFolders(query, skip, limit);
    }

    @GetMapping("/listFolders")
    public Set<String> getListAvailableFolders() {
        log.info("Search list of available folders");
        return this.readJsonService.findAllAvailableFolders();
    }

}
