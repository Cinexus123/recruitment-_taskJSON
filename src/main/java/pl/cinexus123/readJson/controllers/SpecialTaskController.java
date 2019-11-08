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
public class SpecialTaskController {

    private ReadJsonService readJsonService;

    public SpecialTaskController(ReadJsonService readJsonService) {
        this.readJsonService = readJsonService;
    }

    //Additional task(maybe new feature???)
    @GetMapping("/query/{query}&skip/{skip}&limit/{limit}")
    public String getAppropriateContentFolders(@PathVariable("query") String query,@PathVariable("skip") Integer skip,@PathVariable("limit") Integer limit) {
        log.info("Search word: " + query + "with skip: " + skip + " and limit: " + limit);
        return this.readJsonService.findAppropriateWordContent(query, skip, limit);
    }

    //Additional task(maybe new feature???)
    @GetMapping("/listFolders")
    public String getListAvailableFolders() {
        log.info("Search list of available folders");
        return this.readJsonService.findAllAvailableWords();
    }

}
