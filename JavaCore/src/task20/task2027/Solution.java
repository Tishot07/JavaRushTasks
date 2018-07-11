package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };

        detectAllWords(crossword, "home", "same");


        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */

        //не проходит по последнему пункту


    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {

        List<Word> wordResult = new ArrayList<>();
        List<String> wordsList = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            wordsList.add(words[i]);
        }
        //перебираем все слова
        for (String w:
             wordsList) {
            boolean wordFind = false;
            //делаем из слова массив
            char[] wordArray = w.toCharArray();
            //первый цикл по слову
            for (int i = 0; i < crossword.length; i++) {
                if (wordFind) {break;}
                for (int j = 0; j < crossword[0].length; j++) {
                    if (wordFind) {break;}
                    if (wordArray[0] == crossword[i][j]) {
                        Word word = new Word(w);
                        //массив, слово, которое надо найти, координаты и индекс следующей буквы
                        if (findCharacter(crossword, wordArray, i, j, 1, word)) {
                            word.setStartPoint(j, i);
                            wordResult.add(word);
                            wordFind = true;
                        }
                    }
                }

            }
        }

        return wordResult;
    }

    private static boolean findCharacter(int[][] crossword, char[] word, int x, int y, int indexNext, Word result) {
        /*
         * Не работает: Обход сначала в одну сторону, если не получается слово, в следующем направлении
         */
        try {
            //обход
            //можем идти на три
            if (y < crossword[0].length - 1) {
                while (indexNext != word.length && crossword[x ][y + 1] == word[indexNext]) {
                    ++indexNext; ++y;
                }
            }

            //можем идти на пять
            if (x < crossword.length - 1 && y < crossword[0].length - 1) {
                while (indexNext != word.length && crossword[x + 1][y + 1] == word[indexNext]) {
                    ++indexNext; ++x; ++y;
                }
            }

            //можем идти на шесть
            if (x < crossword.length - 1) {
                while (indexNext != word.length && crossword[x + 1][y] == word[indexNext]) {
                    ++indexNext; ++x;
                }
            }

            //можем идти на семь
            if (x < crossword.length - 1 && y != 0) {
                while (indexNext != word.length && crossword[x + 1][y - 1] == word[indexNext]) {
                    ++indexNext; ++x; --y;
                }
            }

            //можем идти на девять
            if (y != 0) {
                while (indexNext != word.length && crossword[x][y - 1] == word[indexNext]) {
                    ++indexNext; --y;
                }
            }

            //можем идти на одиннадцать
            if (x != 0 && y != 0) {
                while (indexNext != word.length && crossword[x - 1][y - 1] == word[indexNext]) {
                    indexNext++; --x; --y;
                }
            }

            //можем идти на двенадцать?
            if (x != 0) {
                while (indexNext != word.length && crossword[x-1][y] == word[indexNext]) {
                    ++indexNext; --x;
                }
            }

            //можем идти на час
            if (x != 0 && y < crossword[0].length - 1) {
                while (indexNext != word.length && crossword[x - 1][y + 1] == word[indexNext]) {
                    ++indexNext; --x; ++y;
                }
            }

        } catch (Exception e) {}

        if (indexNext == word.length) {
            result.setEndPoint(y, x);
            return true;
        }
        else return false;


    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
