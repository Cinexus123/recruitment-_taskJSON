package pl.cinexus123.readJson.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.cinexus123.readJson.ReadJsonService;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("")
public class ListAvailableFoldersController {

    ReadJsonService readJsonService;

    public ListAvailableFoldersController(ReadJsonService readJsonService) {this.readJsonService = readJsonService;}

    @GetMapping("/query={query}&skip={skip}&limit={limit}")
    public List<String> getFullIdFolder(@PathVariable("query") String query, @PathVariable("skip") Integer skip, @PathVariable("limit") Integer limit) {
        log.info("Search folder with query: " + query + " skip " + skip + " limit " +limit);
        return this.readJsonService.getFullListFolderInformation(query,skip,limit);
    }
    @GetMapping("/")
    public List<String> getFullIdFolder() {
        log.info("Return full list of folders");
        return this.readJsonService.getFullListFolders();
    }
}

