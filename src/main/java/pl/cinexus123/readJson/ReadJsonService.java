package pl.cinexus123.readJson;

import java.util.List;
import java.util.Set;

public interface ReadJsonService {
    List<String> findAppropriateContentFolders(String query, Integer skip, Integer limit);
    Set<String> findAllAvailableFolders();

    String getFullIdFolder(String folderId, String type, Integer skip, Integer limit);

    List<String> getFullListFolderInformation(String query, Integer skip, Integer limit);
    List<String> getFullListFolders();
}
