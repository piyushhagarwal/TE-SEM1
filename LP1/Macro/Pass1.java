package LP1.Macro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Pass1 {
    private int positionalParameterCount;
    private int keywordParameterCount;
    private String macroNameTable;
    private String parameterNameTable;
    private String macroDefinitionTable;
    private String keywordParameterDefaultTable;
    private int macroDefinitionTablePointer;
    private int keywordParameterTablePointer;
    private String instruction;

    public Pass1() {
        this.positionalParameterCount = 0;
        this.keywordParameterCount = 0;
        this.macroNameTable = "";
        this.parameterNameTable = "";
        this.macroDefinitionTable = "";
        this.keywordParameterDefaultTable = "";
        this.macroDefinitionTablePointer = 1;
        this.keywordParameterTablePointer = 1;
        this.instruction = "";
    }

    public void process() {
        boolean isMacroPending = false;
        boolean isCodeStart = false;

        try (BufferedReader file = new BufferedReader(
                new FileReader("/Users/piyushagarwal/Downloads/Piyush/TE-SEM1/LP1/Macro/Input.asm"))) {
            String line;
            while ((line = file.readLine()) != null) { // readLine() automatically moves to the next line after
                                                       // execution
                if (line.contains("START") || isCodeStart) {
                    instruction += line;
                    instruction += "\n";
                    isCodeStart = true;
                } else {
                    String[] words = line.replace("&", "").replace(",", "").split("\\s+");
                    if ("MACRO".equals(words[0])) {
                        isMacroPending = true;
                    } else if (isMacroPending) {
                        positionalParameterCount = 0;
                        keywordParameterCount = 0;
                        String macroName = words[0];
                        parameterNameTable += macroName + "\t";

                        for (int i = 1; i < words.length; i++) {
                            if (words[i].contains("=")) {
                                keywordParameterCount++;
                                String parameterName = words[i].split("=")[0];
                                String givenName = words[i].split("=")[1];
                                parameterNameTable += parameterName + "\t";
                                keywordParameterDefaultTable += parameterName + "\t" + givenName + "\n";
                            } else {
                                parameterNameTable += words[i] + "\t";
                                positionalParameterCount++;
                            }
                        }

                        parameterNameTable += "\n";
                        macroNameTable += macroName + "\t" + positionalParameterCount + "\t" + keywordParameterCount
                                + "\t" +
                                macroDefinitionTablePointer + "\t" + keywordParameterTablePointer + "\n";

                        keywordParameterTablePointer += keywordParameterCount;
                        isMacroPending = false;
                    } else {
                        String expandedCode = words[0] + "\t";
                        String[] parameters = parameterNameTable.split("\\s+");

                        for (int i = 1; i < words.length; i++) {
                            if (words[i].contains("=")) {
                                expandedCode += words[i] + "\t";
                            } else {
                                int index = -1;
                                for (int j = 0; j < parameters.length; j++) {
                                    if (parameters[j].equals(words[i])) {
                                        index = j;
                                        break;
                                    }
                                }
                                expandedCode += "(P," + index + ")\t";
                            }
                        }

                        macroDefinitionTablePointer++;
                        expandedCode += "\n";
                        macroDefinitionTable += expandedCode;
                    }
                }
            }

            System.out.println("*" + "Macro Definition Table (MDT)" + "*");
            System.out.println(macroDefinitionTable);
            System.out.println("*" + "Macro Name Table (MNT)" + "*");
            System.out.println(macroNameTable);
            System.out.println("*" + "Parameter Name Table (PNTAB)" + "*");
            System.out.println(parameterNameTable);
            System.out.println("*" + "Keyword Parameter Default Table (KPDT)" + "*");
            System.out.println(keywordParameterDefaultTable);
            System.out.println("*" + "Instruction Counter (IC)" + "*");
            System.out.println(instruction);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Pass1 assembler = new Pass1();
        assembler.process();
    }
}
