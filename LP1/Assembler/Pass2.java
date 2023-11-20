package LP1.Assembler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pass2 {
    private List<Integer> symtab = new ArrayList<>();
    private List<Integer> littab = new ArrayList<>();

    public void readFiles() throws IOException {
        try (BufferedReader symtabFile = new BufferedReader(
                new FileReader("/home/rohandoshi21/Development/LP1/Assembler/pass2/Symtab.txt"));
                BufferedReader littabFile = new BufferedReader(
                        new FileReader("/home/rohandoshi21/Development/LP1/Assembler/pass2/Littab.txt"))) {

            String line;
            while ((line = symtabFile.readLine()) != null) {
                String[] word = line.split(" ");
                symtab.add(Integer.parseInt(word[2].substring(0, word[2].length() - 1)));
            }

            while ((line = littabFile.readLine()) != null) {
                String[] word = line.split(" ");
                littab.add(Integer.parseInt(word[2].substring(0, word[2].length() - 1)));
            }
        }
    }

    public int literalOrSymbol(String word) {
        int index = Integer.parseInt(word.substring(3, word.length() - 1));
        if (word.contains("L")) {
            return littab.get(index - 1);
        } else {
            return symtab.get(index);
        }
    }

    public void process() throws IOException {
        try (BufferedReader inputFile = new BufferedReader(
                new FileReader("/home/rohandoshi21/Development/LP1/Assembler/pass2/input.txt"))) {

            String line;
            while ((line = inputFile.readLine()) != null) {
                String[] word = line.replace("\n", "").trim().split(" ");

                if (word[0].contains("AD") || word[0].contains("DL,02")) {
                    System.out.println();
                } else if (word[0].contains("DL,01")) {
                    System.out.println("00\t0\t" + word[1].substring(3, word[1].length() - 1));
                } else if (word[0].contains("IS,00")) {
                    System.out.println("00\t0\t000");
                } else if (word[0].contains("IS")) {
                    String code1 = word[0].substring(4, 6);
                    String code2 = word[1].substring(1, 2);
                    int code3 = literalOrSymbol(word[2]);
                    System.out.println(code1 + "\t" + code2 + "\t" + code3);
                } else {
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Pass2 test = new Pass2();
        test.readFiles();
        test.process();
    }
}
