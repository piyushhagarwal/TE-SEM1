package LP1.Macro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Pass2 {
    private Map<String, int[]> macroNameTable;
    private Map<String, String[]> parameterNameTable;
    private Map<String, String> keywordParameterDefaultTable;
    private String output;
    private Map<Integer, String> argumentTable;
    private int currentMacroIndex;

    public Pass2() {
        this.macroNameTable = new HashMap<>();
        this.parameterNameTable = new HashMap<>();
        this.keywordParameterDefaultTable = new HashMap<>();
        this.output = "";
        this.argumentTable = new HashMap<>();
        this.currentMacroIndex = 0;
    }

    public void readTables() {
        // Populate Macro Name Table
        macroNameTable.put("M1", new int[] { 2, 2, 1, 1 });
        macroNameTable.put("M2", new int[] { 2, 2, 6, 3 });

        // Populate Parameter Name Table
        parameterNameTable.put("M1", new String[] { "X", "Y", "A", "B" });
        parameterNameTable.put("M2", new String[] { "P", "Q", "U", "V" });

        // Populate Keyword Parameter Default Table
        keywordParameterDefaultTable.put("A", "AREG");
        keywordParameterDefaultTable.put("B", "BREG");
        keywordParameterDefaultTable.put("U", "CREG");
        keywordParameterDefaultTable.put("V", "DREG");
    }

    public void process() {
        try (BufferedReader intermediateCodeFile = new BufferedReader(
                new FileReader("/Users/piyushagarwal/Downloads/Piyush/TE-SEM1/LP1/Macro/intermediate.txt"));
                BufferedReader macroDefinitionTableFile = new BufferedReader(
                        new FileReader("/Users/piyushagarwal/Downloads/Piyush/TE-SEM1/LP1/Macro/mdt.txt"))) {

            String line;
            while ((line = intermediateCodeFile.readLine()) != null) {
                // Tokenize the line to extract words
                String[] words = line.replace(",", "").replace("&", "").split("\\s+");

                if (!macroNameTable.containsKey(words[0])) {
                    // If not a macro, append the line to the output
                    output += line + "\n";
                } else {
                    currentMacroIndex++;
                    argumentTable.clear();
                    int[] macroInfo = macroNameTable.get(words[0]);
                    int positionalParameterCount = macroInfo[0];
                    int keywordParameterCount = macroInfo[1];

                    // Populate argument table with positional parameters
                    for (int i = 1; i <= positionalParameterCount; i++) {
                        argumentTable.put(i, words[i]);
                    }

                    // Populate argument table with keyword parameters and defaults
                    for (int i = positionalParameterCount + 1; i <= positionalParameterCount
                            + keywordParameterCount; i++) {
                        if (words[i].split("=").length == 1) {
                            argumentTable.put(i, keywordParameterDefaultTable.get(words[i].split("=")[0]));
                        } else {
                            argumentTable.put(i, words[i].split("=")[1]);
                        }
                    }

                    String macroDefinitionLine;
                    while ((macroDefinitionLine = macroDefinitionTableFile.readLine()) != null) {
                        String[] macroDefinitionWords = macroDefinitionLine.split("\\s+");

                        if (!macroDefinitionWords[0].equals("MEND")) {
                            // If not the end of the macro, process the macro definition
                            output += macroDefinitionWords[0] + "\t";

                            for (int i = 1; i < macroDefinitionWords.length; i++) {
                                if (!macroDefinitionWords[i].contains("=")) {
                                    // If not a keyword parameter, replace with corresponding argument
                                    int parameterIndex = Integer.parseInt(macroDefinitionWords[i].substring(3, 4));
                                    output += argumentTable.get(parameterIndex) + "\t";
                                } else {
                                    // If a keyword parameter, append as is
                                    output += macroDefinitionWords[i] + "\t";
                                }
                            }

                            output += "\n";
                        } else {
                            break;
                        }
                    }
                }
            }

            // Print the final output
            System.out.println(output);

        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Create an instance of Pass2
        Pass2 pass2 = new Pass2();

        // Read tables from files
        pass2.readTables();

        // Process the intermediate code
        pass2.process();
    }
}
