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

    @GetMapping("/GET/{folderId}&type={type}&skip={skip}&limit={limit}")
    public String getFullIdFolder(@PathVariable("folderId") String folderId,@PathVariable("type") String type,
                                        @PathVariable("skip") Integer skip,@PathVariable("limit") Integer limit) {
        log.info("Search folder with ID: " + folderId + " type: " + type + " skip: " + skip +" limit: " + limit);
        return this.readJsonService.getFullIdFolder(folderId,type,skip,limit);
    }

}
