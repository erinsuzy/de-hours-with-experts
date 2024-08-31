package com.labs1904;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SecretRecipeDecoder {
    private static Map<String, String> ENCODING = new HashMap<String, String>() {
        {
            put("y", "a");
            put("h", "b");
            put("v", "c");
            put("x", "d");
            put("k", "e");
            put("p", "f");
            put("z", "g");
            put("s", "h");
            put("a", "i");
            put("b", "j");
            put("e", "k");
            put("w", "l");
            put("u", "m");
            put("q", "n");
            put("n", "o");
            put("l", "p");
            put("m", "q");
            put("f", "r");
            put("o", "s");
            put("i", "t");
            put("g", "u");
            put("j", "v");
            put("t", "w");
            put("d", "x");
            put("r", "y");
            put("c", "z");
            put("3", "0");
            put("8", "1");
            put("4", "2");
            put("0", "3");
            put("2", "4");
            put("7", "5");
            put("5", "6");
            put("9", "7");
            put("1", "8");
            put("6", "9");
        }
    };

    /**
     * Given a string named str, use the Caesar encoding above to return the decoded string.
     *
     * @param str
     * @return
     */

    public static String decodeString(String str) {
        StringBuilder decoded = new StringBuilder();
        for (char ch : str.toCharArray()) {
            String encodedChar = String.valueOf(ch);
            decoded.append(ENCODING.getOrDefault(encodedChar, encodedChar));
        }
        return decoded.toString();
    }

    /**
     * Given an ingredient, decode the amount and description, and return a new Ingredient
     *
     * @param line
     * @return
     */
    public static Ingredient decodeIngredient(String line) {
        String[] parts = line.split("#");
        String decodedAmount = decodeString(parts[0].trim());
        String decodedDescription = decodeString(parts[1].trim());
        return new Ingredient(decodedAmount, decodedDescription);
    }


       public static void decodeRecipe(String inputFilePath, String outputFilePath) {
           File outputFile = new File(outputFilePath);
           File parentDir = outputFile.getParentFile();
           if (!parentDir.exists()) {
               if (!parentDir.mkdirs()) {
                   System.err.println("Failed to create directory: " + parentDir.getAbsolutePath());
                   return;
               }
           }

           try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

               String line;
               while ((line = reader.readLine()) != null) {
                   Ingredient decodedIngredient = SecretRecipeDecoder.decodeIngredient(line);
                   String decodedLine = decodedIngredient.getAmount() + " " + decodedIngredient.getDescription();
                   writer.write(decodedLine);
                   writer.newLine();
               }

               System.out.println("Decoded recipe has been saved to " + outputFilePath);

           } catch (IOException e) {
               System.err.println("Error processing the file: " + e.getMessage());
           }
       }

       public static void main(String[] args) {
           String inputFilePath = "C:/Users/erins/IdeaProjects/hoursWithExperts/java/src/main/resources/secret_recipe.txt";  // Absolute path to your input file
           String outputFilePath = "C:/Users/erins/IdeaProjects/hoursWithExperts/java/src/main/resources/decoded_recipe.txt";  // Absolute path to your output file

           decodeRecipe(inputFilePath, outputFilePath);
       }
   }


