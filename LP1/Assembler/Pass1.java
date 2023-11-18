package LP1.Assembler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pass1 {

    // Intialize data structures
    // 1. Symbol table
    private List<String[]> symbolTab; // Eg: [["A", "5"], ["B", "10"]]
    private int lc; // Location counter

    // 2. Literal table
    private List<String[]> literalTab;
    private int literalIndex;

    // 3. Pool table
    private List<Integer> poolTab;
    private int poolIndex;

    // Create OPCODE table
    private final Map<String, String> OPTAB = new HashMap<>();

    // Note that we have to create a block to insert or do any operation on the Maps
    {
        OPTAB.put("STOP", "(IS,00)");
        OPTAB.put("ADD", "(IS,01)");
        OPTAB.put("SUB", "(IS,02)");
        OPTAB.put("MUL", "(IS,03)");
        OPTAB.put("MOVER", "(IS,04)");
        OPTAB.put("MOVEM", "(IS,05)");
        OPTAB.put("COMP", "(IS,06)");
        OPTAB.put("BC", "(IS,07)");
        OPTAB.put("DIV", "(IS,08)");
        OPTAB.put("READ", "(IS,09)");
        OPTAB.put("PRINT", "(IS,10)");
    }

    // Create Register table
    private final Map<String, String> REGISTER = new HashMap<>();

    {
        REGISTER.put("AREG", "(1)");
        REGISTER.put("BREG", "(2)");
        REGISTER.put("CREG", "(3)");
        REGISTER.put("DREG", "(4)");
    }

    // Create Condition Code table

    private final Map<String, String> CC = new HashMap<>();

    {
        CC.put("LT", "(1)");
        CC.put("LE", "(2)");
        CC.put("EQ", "(3)");
        CC.put("GT", "(4)");
        CC.put("GE", "(5)");
        CC.put("ANY", "(6)");
    }

    public Pass1() {

        symbolTab = new ArrayList<>();
        lc = 0;

        literalTab = new ArrayList<>();
        literalIndex = 0;

        poolTab = new ArrayList<>();
        poolTab.add(0);
        poolIndex = 0;

    }

    // This function updates the symbol table (symtab) with the information provided
    // in the pair parameter.
    // It iterates through the existing entries in the symbol table and checks if
    // there is an entry with the same symbol (represented by pair[0]).
    // If a matching symbol is found, it updates the associated location information
    // (i[1]) with the new value (pair[1]).

    public void updateSymbolTab(String[] pair) {
        for (String[] stringArray : symbolTab) {
            if (stringArray[0].equals(pair[0])) { // Note: use equals(), not "==" because we want to compare the content
                                                  // not the reference
                stringArray[1] = pair[1];
                break;
            }
        }
    }

    // This function retrieves the location counter (LC) associated with a given
    // symbol from the symbol table.
    // It iterates through the symbol table and checks if there is an entry with the
    // provided symbol.
    // If a matching symbol is found, it returns the corresponding location counter
    // as an integer.
    // If no matching symbol is found, it returns -1 to indicate that the symbol is
    // not in the symbol table.

    public int getSymbolLC(String symbol) {
        for (String[] stringArray : symbolTab) {
            if (stringArray[0].equals(symbol)) {
                return Integer.parseInt(stringArray[1]);
            }
        }
        return -1;
    }

    // This function retrieves the position (index) of a given symbol in the symbol
    // table.
    // It iterates through the symbol table and checks if there is an entry with the
    // provided symbol.
    // If a matching symbol is found, it returns the index (position) of that entry
    // in the symbol table.
    // If no matching symbol is found, it returns -1 to indicate that the symbol is
    // not in the symbol table.

    public int getSymbolIndex(String symbol) {
        for (int i = 0; i < symbolTab.size(); i++) {
            if (symbolTab.get(i)[0].equals(symbol)) {
                return i;
            }
        }
        return -1;
    }

    public void process() {
        try (BufferedReader br = new BufferedReader(
                new FileReader("/Users/piyushagarwal/Downloads/Piyush/TE-SEM1/LP1/Assembler/input.txt"))) {

            String line;
            while (true) {

                // Read a line from the input file
                line = br.readLine(); // readLine() automatically moves to the next line after execution
                if (line == null) {
                    break;
                }

                String words[] = line.split("\\s+");
                // trim() removes leading and trailing spaces
                // split("\\s+") splits the string at one or more spaces

                System.out.println(words.length);

                // Print the words array
                for (String word : words) {
                    if (word.equals("")) {
                        System.out.println("Empty String");
                    } else {
                        System.out.println(word);
                    }
                }

            }

        } catch (FileNotFoundException e) { // Catch block for BufferedReader
            System.out.println(e);
        } catch (IOException e) { // Catch block for readLine()
            System.out.println(e);
        } catch (Exception e) { // Catch block for general exceptions
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        // // Tests to write all the functions

        // // Test updateSymbolTab
        Pass1 pass1 = new Pass1();
        // String[] pair = { "A", "5" };
        // pass1.symbolTab.add(pair);
        // String[] pair2 = { "B", "10" };
        // pass1.symbolTab.add(pair2);
        // String[] pair3 = { "C", "15" };
        // pass1.symbolTab.add(pair3);
        // String[] pair4 = { "D", "20" };
        // pass1.symbolTab.add(pair4);
        // String[] pair5 = { "E", "25" };
        // pass1.symbolTab.add(pair5);

        // // Test getSymbolLC
        // System.out.println(pass1.getSymbolLC("G"));

        // // Test getSymbolIndex
        // System.out.println(pass1.getSymbolIndex("A"));

        pass1.process();

    }

}