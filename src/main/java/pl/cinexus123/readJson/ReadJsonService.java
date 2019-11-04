package pl.cinexus123.readJson;

import java.util.List;
import java.util.Set;

public interface ReadJsonService {
    List<String> findAppropriateContentFolders(String query, Integer skip, Integer limit);
    Set<String> findAllAvailableFolders();

    List<String> getFullIdFolder(String folderId);
    List<String> getFullPathFolder(String path);

    List<String> getFullListFolderInformation(String query, Integer skip, Integer limit);
}
