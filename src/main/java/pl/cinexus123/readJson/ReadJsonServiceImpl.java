package pl.cinexus123.readJson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
                int skipCount = 0;
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

    //Second part of the task
    @Override
    public String getFullIdFolder(String folderId, String type, Integer skip, Integer limit) {
        String JsonContent = readLineByLineJava8();
        List<String> content = new ArrayList<>();
        List<String> foldersContent = new ArrayList<>();

        String pattern ="\"id\\\": \\\"[0-9]+\\\"";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(JsonContent);
        while(m.find())
        {
            String idValue = m.group(0).replaceAll("\\D+","");
                content.add(idValue);
        }

        for (int i = 0; i < JsonContent.length() ; i++) {
            i = JsonContent.indexOf("\n  \"//test-path", i); //what look for and where to start find this phrase
            if (i < 0)
                break;
            String contentFolder = (JsonContent.substring(i));
            String link ="{" + contentFolder.split(" },\n  \"//test-path/CloudUX1")[0];
            link = link + " },\n}";
            foldersContent.add(link);
        }
        //Small bug with }, } in index 16
        for (int i = 0; i < content.size() ; i++) {

            if(content.get(i).contains(folderId) && content.get(i).length() == folderId.length())
                return foldersContent.get(i);
        }
        return "folder with provided ID not found";
    }



    //Main functionality(first task)
    @Override
    public List<String> getFullListFolderInformation(String query, Integer skip, Integer limit) {
        String JsonContent = readLineByLineJava8();
        List<String> listFolders = new ArrayList<>();

        String add = "\"";
        String query1 = " \"id\": \"";
        if(query.matches("[0-9]+"))
        {
            query1= query + add;
        }
        int queryLen = query1.length();
        int counter = 0; // variable which count elements in list
        int skipCounter = 0; // variable which count skip element
        String code = ".*?\\W+\"id\\\": \\\"[0-9]+\\\"";
        Pattern p = Pattern.compile(code);
        Matcher m = p.matcher(JsonContent);
        while (m.find())
        {
            if (counter >= limit)
                return listFolders;
            int number = 0;
            if(query.matches("[0-9]+"))
            {
                if(m.group(0).contains(query1)) {
                 String zmienna = m.group(0);
                 Integer zmienna1 = zmienna.lastIndexOf(query);
                 if(Character.isDigit(zmienna.charAt(zmienna1 - 1)) || query.length() == 2)
                     number = 2;
                 else
                     number = 1;

                    skipCounter++;
                    if (skipCounter > skip && query.length() == number) {
                        listFolders.add(m.group(0));
                        counter++;
                    }
                }
            }
            if(query.length() >= 3)
            {
                if(m.group(0).contains(query)) {
                    skipCounter++;
                    if (skipCounter > skip) {
                        listFolders.add(m.group(0));
                        counter++;
                    }
                }
            }

        }
        return listFolders;
    }

    @Override
    public List<String> getFullListFolders() {
        String JsonContent = readLineByLineJava8();
        List<String> listFolders = new ArrayList<>();

        String code = ".*?\\W+\"id\\\": \\\"[0-9]+\\\"";
        Pattern p = Pattern.compile(code);
        Matcher m = p.matcher(JsonContent);
        while (m.find())
            listFolders.add(m.group(0));

        return listFolders;
    }


    //Function for both parts of the task
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
