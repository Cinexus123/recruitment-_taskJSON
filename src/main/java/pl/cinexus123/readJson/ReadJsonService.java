package pl.cinexus123.readJson;

import java.util.List;
import java.util.Set;

public interface ReadJsonService {
    String findAppropriateWordContent(String query, Integer skip, Integer limit);
    String findAllAvailableWords();

    String getFullIdFolder(String folderId, String type, Integer skip, Integer limit);

    String getFullListFolderInformation(String query, Integer skip, Integer limit);
    String getFullListFolders();
}
