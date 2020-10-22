package DictionaryApplication;// File thao tac ham

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {

    Scanner scanner = new Scanner(System.in);
    // Nhap tu dien bang ban phim
    public void insertFromCommandline() {
        System.out.print("\n    Nhap so luong tu vung: ");
        String N = scanner.nextLine().trim();
        while (checkInt(String.valueOf(N)) == false) {
            System.out.print("\nNhap khong hop le.Nhap lai so luong tu vung: \n");
            N = scanner.nextLine().trim();
        }
        int n = Integer.parseInt(N);
        int i = 0;
        while (i < n) {
            System.out.print("\n    Nhap word_target: ");
            String word_target = scanner.nextLine().trim();
            for (int j = 0; j < wordList.size(); j++) {
                while (word_target.equalsIgnoreCase(wordList.get(j).word_target)) {
                    System.out.print("Them tu that bai. Tu da ton tai, moi ban nhap lai: ");
                    word_target = scanner.nextLine().trim();
                }
            }
            System.out.print("\n    Nhap word_explain: ");
            String word_explain = scanner.nextLine().trim();
            Word newWord = new Word(properCase(word_target), properCase(word_explain));
            wordList.add(newWord);
            i++;
        }
    }

    //chen 1 tu vao cuoi tu dien
    public void insertWord(Word word) {
        int count = 0;
        for (int j = 0; j < wordList.size(); j++) {
            if (word.word_target.trim().equalsIgnoreCase(wordList.get(j).word_target)) {
                count++;
            }
        }
        if (count == 0) wordList.add(word);
        System.out.print("Succesful\n");
    }

    // Sua 1 tu trong tu dien
    public void changeWord(int i, Word word2) {
        wordList.set(i, word2);

        System.out.print("\nSuccesful\n");
    }

    // Xoa 1 tu trong tu dien
    public void removeWord(Word word) {

        int Index = 0;
        for (int i = 0; i < wordList.size(); i++) {
            if (word.word_target.equalsIgnoreCase(wordList.get(i).word_target)) {
                Index = i;
                break;
            }
        }
        wordList.remove(Index);
        System.out.print("\nSuccesful\n");
    }

    //truyen du lieu tu file
    public void insertFromFile() throws IOException {
        Scanner scanner = new Scanner(Paths.get("dictionaries.txt"), "UTF-8");
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            int index = Index(word, '\t');
            String word_target = word.substring(0, index);
            String word_explain = word.substring(index + "\t".length(), word.length());
            Word newWord = new Word(word_target, word_explain);
            wordList.add(newWord);
        }
        scanner.close();
    }

    // Xuat du lieu tu dien ra file
    public void dictionaryExportToFile() throws IOException {
        PrintWriter printWriter = new PrintWriter("dictionaries.txt", "UTF-8");
        for (int i = 0; i < wordList.size(); i++) {
            printWriter.print(wordList.get(i).word_target + "\t" + wordList.get(i).word_explain + "\n");
        }
        printWriter.close();
        System.out.print("Succesfull");
    }

    // Tra tu
    public void dictionaryLookup() {
        System.out.print("\nNhap tu can tra:");
        String word = scanner.nextLine().trim();
        int j = 0;
        do {
            for (int i = 0; i < wordList.size(); i++) {
                if (wordList.get(i).word_target.equalsIgnoreCase(word)) {
                    System.out.print("\nNghia cua tu la: " + wordList.get(i).word_explain + "\n\n");
                    j = 0;
                    break;
                } else j = 1;
            }
                if (j == 1) {
                    System.out.print("\n\nTu nhap sai hoac chua co trong tu dien.\nMoi ban nhap lai:");
                    word = scanner.nextLine();
                }
        } while (j ==1);
    }

// Ham tim vi tri dau tien cua 1 ki tu
    public static int Index(String s,char c) {
        int temp = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == c) temp = i;
        }
        return temp;
    }

// Ham chuyen doi chu cai dau tien của chuoi sang viet hoa:
    public static String properCase (String inputVal) {
        if (inputVal.length() == 0) return "";
        if (inputVal.length() == 1) return inputVal.toUpperCase();
        return inputVal.substring(0,1).toUpperCase() + inputVal.substring(1).toLowerCase();
    }

// Ham sap xep lai tu dien
    public void sortDictionary() {
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = i + 1; j < wordList.size(); j++) {
                if (properCase(wordList.get(i).word_target).compareTo(properCase(wordList.get(j).word_target)) > 0) {
                    Word temp = wordList.get(i);
                    wordList.set(i, wordList.get(j));
                    wordList.set(j, temp);
                }
            }
        }
    }

    // ham kiem tra co phai kieu int khong:
    public static boolean checkInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}