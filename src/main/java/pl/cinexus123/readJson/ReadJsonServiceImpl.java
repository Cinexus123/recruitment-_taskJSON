package pl.cinexus123.readJson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Slf4j
@Service
public class ReadJsonServiceImpl implements  ReadJsonService {

    @Override
    public List<String> findAppropriateContentFolders(String query, Integer skip, Integer limit) {
        String JsonContent = readLineByLineJava8();

        //find appropriate name folders
        Set<String> listFolders = new TreeSet<>();
        Pattern p = Pattern.compile("(\\w+)[\\w-](\\w+)\\W+\\[" );
        Matcher m = p.matcher(JsonContent);
        while (m.find())
            listFolders.add(m.group(0));

        List<String> content = new ArrayList<>();
        int counter = 0; //variable to observe save limit
        for(String name : listFolders) {
            if((name.trim().contains(query))) {
                int skipCount = 0; //
                for (int i = 0; i < JsonContent.length() ; i++) {
                    i = JsonContent.indexOf(query, i); //what look for and where to start find this phrase
                    if(i < 0)
                        break;
                    String contentFolder = (JsonContent.substring(i));
                    if(!query.equals("assets")) {
                        String link = contentFolder.split("],")[0];
                        skipCount++;
                        if(skipCount > skip)
                        {
                            String add = "],";
                            link = link + add;
                            content.add(link);
                            counter++;
                            if(counter >= limit)
                                return content;
                        }

                    }
                    if(query.equals("assets")) {
                       String link = contentFolder.split("]\n")[0];
                       skipCount++;
                       if(skipCount > skip)
                       {
                           String add = "]";
                           link = link + add;
                           content.add(link);
                           counter++;
                           if(counter >= limit)
                               return content;
                       }

                    }

                }
            }

        }
        return content;
    }

    //TO DO
    @Override
    public List<String> getFullIdFolder(String folderId) {
        String JsonContent = readLineByLineJava8();
        List<String> content = new ArrayList<>();

        for (int i = 0; i < JsonContent.length() ; i++) {
            i = JsonContent.indexOf(folderId,i);
            if(i < 0)
                break;
            String contentFolder = (JsonContent.substring(i));
            String link = contentFolder.split("],")[0];
            content.add(link);

        }
        return null;
    }

    @Override
    public Set<String> findAllAvailableFolders() {
        String JsonContent = readLineByLineJava8();
        Set<String> listFolders = new TreeSet<>();


        Pattern p = Pattern.compile("(\\w+)[\\w-](\\w+)\\W+\\[" );
        Matcher m = p.matcher(JsonContent);
        while (m.find())
            listFolders.add(m.group(0));

        return listFolders;
    }

    private static String readLineByLineJava8()
    {
        StringBuilder contentBuilder = new StringBuilder();
        String filePath = "C:/Users/Marcin/Downloads/readJson/readJson/src/main/resources/json/data.json/";

        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
