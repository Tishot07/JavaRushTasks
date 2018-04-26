package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private List<Path> foundFiles = new ArrayList<>();


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        // размер файла: content.length
        byte[] content = Files.readAllBytes(file);

        boolean isInMinBorder = true;
        boolean isInMaxBorder = true;
        boolean containsContent = true;
        boolean containsName = true;

        //Если в SearchFileVisitor задан критерий поиска partOfName, метод visitFile должен добавить файл в foundFiles, если в названии содержится строка partOfName.
        if(partOfName!=null && !file.getFileName().toString().contains(partOfName)) {
            containsName = false;
        }

        //Если в SearchFileVisitor задан критерий поиска partOfContent, метод visitFile должен добавить файл в foundFiles, если в содержимом встречается строка partOfContent.
        if(partOfContent!=null && !new String(Files.readAllBytes(file)).contains(partOfContent)) {
            containsContent = false;
        }

        //Если в SearchFileVisitor задан критерий поиска maxSize, метод visitFile должен добавить файл в foundFiles, если размер файла меньше maxSize
        if (maxSize != 0 && !(content.length < maxSize)) {
            isInMaxBorder = false;
        }

        //Если в SearchFileVisitor задан критерий поиска minSize, метод visitFile должен добавить файл в foundFiles, если размер файла больше minSize
        if (minSize != 0 && !(content.length > minSize)) {
            isInMinBorder = false;
        }

        //Метод visitFile должен быть реализован так, чтобы учитывать сразу несколько критериев поиска
        if (isInMinBorder && isInMaxBorder && containsContent && containsName) {
            foundFiles.add(file);
        }

        return FileVisitResult.CONTINUE;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
}
