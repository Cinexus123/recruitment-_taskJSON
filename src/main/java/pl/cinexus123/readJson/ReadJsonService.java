package pl.cinexus123.readJson;

import java.util.List;
import java.util.Set;

public interface ReadJsonService {
    List<String> findAppropriateContentFolders(String query, Integer skip, Integer limit);
    List<String> getFullIdFolder(String folderId);
    Set<String> findAllAvailableFolders();
}
