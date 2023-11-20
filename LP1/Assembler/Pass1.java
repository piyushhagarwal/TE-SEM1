package LP1.Assembler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

class Pass1 {

    // Note that we have to create a block to insert or do any operation on the Maps
    static HashMap<String, String> OPTAB = new HashMap<>() { // Create OPCODE table
        {
            put("STOP", "(IS, 00)");
            put("ADD", "(IS, 01)");
            put("SUB", "(IS, 02)");
            put("MUL", "(IS, 03)");
            put("MOVER", "(IS, 04)");
            put("MOVEM", "(IS, 05)");
            put("COMP", "(IS, 06)");
            put("BC", "(IS, 07)");
            put("DIV", "(IS, 08)");
            put("READ", "(IS, 09)");
            put("PRINT", "(IS, 10)");
        }
    };

    // Create Register table
    static HashMap<String, String> REG = new HashMap<>() {
        {
            put("AREG", "(1)");
            put("BREG", "(2)");
            put("CREG", "(3)");
            put("DREG", "(4)");
        }
    };

    // Create Condition Code table
    static HashMap<String, String> CC = new HashMap<>() {
        {
            put("LT", "(1)");
            put("LE", "(2)");
            put("EQ", "(3)");
            put("GT", "(4)");
            put("GE", "(5)");
            put("ANY", "(6)");
        }
    };

    public static int lc = 0; // Location counter

    // Intialize data structures

    // 1. Symbol table
    public static ArrayList<String[]> symbolTable = new ArrayList<>(); // Eg: [["A", "5"], ["B", "10"]]

    // 2. Literal table
    public static ArrayList<String[]> literalTable = new ArrayList<>();
    public static int literalIndex = 0;

    // 3. Pool table
    public static ArrayList<Integer> poolTable = new ArrayList<>();
    public static int poolIndex = 0;

    // This function updates the symbol table (symtab) with the information provided
    // in the pair parameter.
    // It iterates through the existing entries in the symbol table and checks if
    // there is an entry with the same symbol (represented by pair[0]).
    // If a matching symbol is found, it updates the associated location information
    // (i[1]) with the new value (pair[1]) otherwise add the new symbol to the
    // table.
    public static void updateSymtab(String[] pair) {
        for (String[] i : symbolTable) {
            if (i[0].equals(pair[0])) {
                i[1] = pair[1];
                return;
            }
        }
        // If the symbol is not already present
        symbolTable.add(pair);
    }

    // This function retrieves the position (index) of a given symbol in the symbol
    // table.
    // It iterates through the symbol table and checks if there is an entry with the
    // provided symbol.
    // If a matching symbol is found, it returns the index (position) of that entry
    // in the symbol table.
    // If no matching symbol is found, it returns -1 to indicate that the symbol is
    // not in the symbol table.
    public static int getSymPosition(String symbol) {
        for (int i = 0; i < symbolTable.size(); i++) {
            if (symbolTable.get(i)[0].equals(symbol)) {
                return i;
            }
        }
        return -1;
    }

    public static void process() {
        try (BufferedReader br = new BufferedReader(
                new FileReader("/Users/piyushagarwal/Downloads/Piyush/TE-SEM1/LP1/Assembler/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) { // Read a line from the input file
                                                     // readLine() automatically moves to the next line after execution

                // Split the line into words (separated by spaces)
                String[] words = line.split("\\s+");

                // If the line includes START
                if (words[1].equals("START")) {
                    lc = Integer.parseInt(words[2]); // Set the location counter to the value provided in the input
                                                     // file
                    System.out.println("(AD, 01)\t" + "(C, " + lc + ")");
                }

                // If the line includes DS
                if (words[1].equals("DS")) {
                    int constant = Integer.parseInt(words[2]); // Get the value of the constant
                    lc += constant; // Increment the location counter by the value of the
                                    // constant
                    System.out.println("(DS, 02)\t" + "(C, " + constant + ")");
                }

                // If the line includes DC
                if (words[1].equals("DC")) {
                    lc++;
                    int constant = Integer.parseInt(words[2].replace("'", "")); // Get the value of the constant
                    System.out.println("(DS, 01)\t" + "(C, " + constant + ")");
                }

                // If the line includes a symbol/label
                if (!words[0].equals("")) {
                    String[] pair = { words[0], Integer.toString(lc) };
                    updateSymtab(pair);
                }

                // If the line includes ORIGIN
                if (words[1].equals("ORIGIN")) {
                    String opreations[] = words[2].split("\\+");
                    int symbolIndex = getSymPosition(opreations[0]);
                    lc = Integer.parseInt(symbolTable.get(symbolIndex)[1]) + Integer.parseInt(opreations[1]);
                    System.out.println("(AD, 03)\t" + "(C, " + lc + ")");
                }

                // If the line includes EQU
                if (words[1].equals("EQU")) {
                    String opreations[] = words[2].split("\\+");
                    int symbolIndex = getSymPosition(opreations[0]); // Get the index of the symbol in the symbol table
                    int symbolLC = Integer.parseInt(symbolTable.get(symbolIndex)[1]); // Get the location counter
                    int updatedLC = symbolLC + Integer.parseInt(opreations[1]); // Update the location counter
                    symbolTable.get(symbolIndex)[1] = Integer.toString(updatedLC); // Update the symbol table
                    System.out.println("(AD, 04)\t");

                }

                // If the line includes LTORG
                if (words[1].equals("LTORG")) {
                    for (int i = poolIndex; i < literalIndex; i++) {
                        literalTable.get(i)[1] = Integer.toString(lc);
                        lc++;

                        int literalValue = Integer.parseInt(literalTable.get(i)[0].replace("=", "").replace("'", ""));
                        System.out.println("(AD, 05)\t(DL,01)\t(C, " + literalValue + ")");
                    }

                    // Update Pool Table
                    poolTable.add(literalIndex);
                    poolIndex = literalIndex;
                }

                // If the line includes OPCODE
                if (OPTAB.containsKey(words[1])) {
                    String code = OPTAB.get(words[1]) + "\t";

                    int j = 2;
                    while (j < words.length) {
                        String word = words[j].replace(",", "");

                        // If the word is a Register
                        if (REG.containsKey(word)) {
                            code += REG.get(word) + "\t";
                        } else if (CC.containsKey(word)) {
                            code += CC.get(word) + "\t";
                        } else if (word.contains("=")) {
                            String[] pair = { word, "-1" };
                            literalTable.add(pair);
                            code += "(L, " + literalIndex + ")";
                            literalIndex++;
                        } else {
                            if (getSymPosition(word) == -1) {
                                String[] pair = { word, "-1" };
                                symbolTable.add(pair);
                            }

                            int symbolIndex = getSymPosition(word);
                            code += "(S, " + symbolIndex + ")";
                        }

                        j++;
                    }

                    lc++;
                    System.out.println(code);
                }

                // If the line includes END
                if (words[1].equals("END")) {
                    for (int i = poolIndex; i < literalIndex; i++) {
                        literalTable.get(i)[1] = Integer.toString(lc);
                        lc++;

                    }

                    // Update Pool Table
                    poolTable.add(literalIndex);
                    poolIndex = literalIndex;

                    System.out.println("(AD, 02)");
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        poolTable.add(0);// Add the first entry to the pool table

        process();

        System.out.println();
        // Print symbol table
        System.out.println("Symbol Table: ");
        for (String[] i : symbolTable) {
            System.out.println(i[0] + "\t" + i[1]);
        }

        System.out.println();
        // Print literal table
        System.out.println("Literal Table: ");
        for (String[] i : literalTable) {
            System.out.println(i[0] + "\t" + i[1]);
        }

        System.out.println();
        // Print pool table
        System.out.println("Pool Table: ");
        for (int i : poolTable) {
            System.out.println(i);
        }

    }

}