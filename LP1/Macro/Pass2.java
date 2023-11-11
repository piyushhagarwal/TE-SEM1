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
                String[] words = line.replace(",", "").replace("&", "").split("\\s+");

                currentMacroIndex++;
                argumentTable.clear();
                int[] macroInfo = macroNameTable.get(words[0]);
                int positionalParameterCount = macroInfo[0];
                int keywordParameterCount = macroInfo[1];

                for (int i = 1; i <= positionalParameterCount; i++) {
                    argumentTable.put(i, words[i]);
                }

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
                        output += macroDefinitionWords[0] + "\t";

                        for (int i = 1; i < macroDefinitionWords.length; i++) {
                            if (!macroDefinitionWords[i].contains("=")) {
                                int parameterIndex = Integer.parseInt(macroDefinitionWords[i].substring(3, 4));
                                output += argumentTable.get(parameterIndex) + "\t";
                            } else {
                                output += macroDefinitionWords[i] + "\t";
                            }
                        }

                        output += "\n";
                    } else {
                        break;
                    }
                }
            }

            System.out.println(output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Pass2 pass2 = new Pass2();
        pass2.readTables();
        pass2.process();
    }
}
