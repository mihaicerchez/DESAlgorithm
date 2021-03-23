package com.Mihai.UTM;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class Main {
    public static class DES {
        private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        private static Random random = new Random();
        int min = 1;
        int max = 16;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max);
        String randomHexString(int size) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < size; i++) {
                sb.append(hexDigits[random(hexDigits.length)]);
            }
            return sb.toString();
        }

        public static int random(int maxValue) {
            return random.nextInt(maxValue);
        }

        int choice;
        String fokenmessage;
        String KinBinary;
        // CONSTANTE

    /*  int[] PC1 = {57, 49, 41, 33, 25,
                17, 9, 1, 58, 50, 42, 34, 26,
                18, 10, 2, 59, 51, 43, 35, 27,
                19, 11, 3, 60, 52, 44, 36, 63,
                55, 47, 39, 31, 23, 15, 7, 62,
                54, 46, 38, 30, 22, 14, 6, 61,
                53, 45, 37, 29, 21, 13, 5, 28,
                20, 12, 4};*/

        int[] PC2 = {14, 17, 11, 24, 1, 5, 3,
                28, 15, 6, 21, 10, 23, 19, 12,
                4, 26, 8, 16, 7, 27, 20, 13, 2,
                41, 52, 31, 37, 47, 55, 30, 40,
                51, 45, 33, 48, 44, 49, 39, 56,
                34, 53, 46, 42, 50, 36, 29, 32};


        int[] shiftBits = {1, 1, 2, 2, 2, 2, 2, 2,
                1, 2, 2, 2, 2, 2, 2, 1};

        String hextoBin(String input) {
            int n = input.length() * 4;
            input = Long.toBinaryString(
                    Long.parseUnsignedLong(input, 16));
            while (input.length() < n)
                input = "0" + input;

            return input;
        }

        String binToHex(String input) {
            int n = (int) input.length() / 4;
            input = Long.toHexString(
                    Long.parseUnsignedLong(input, 2));
            while (input.length() < n)
                input = "0" + input;
            return input;
        }

        String permutation(int[] sequence, String input) {
            String output = "";
            input = hextoBin(input);
            for (int i = 0; i < sequence.length; i++)
                output += input.charAt(sequence[i] - 1);
            System.out.println(output);
            output = binToHex(output);
            return output;
        }

        String leftCircularShift(String input, int numBits) {
            int n = input.length() * 4;
            int perm[] = new int[n];
            for (int i = 0; i < n - 1; i++)
                perm[i] = (i + 2);
            //System.out.println("(Ci si Di)");
            perm[n - 1] = 1;
            while (numBits-- > 0)
                input = permutation(perm, input);
            return input;
        }

        // 16 chei in 16 runde
        String[] getKeys(String key) {
            //int choice = 0;
            //System.out.println(Arrays.toString(PC2));
            String keys[] = new String[16];
            //String Returned = null;
            String BetterKeyPart1 = java.util.Arrays.toString(hextoBin(key).split("(?<=\\G.{7})"));
            String BetterKeyPart2 = BetterKeyPart1.replace(',','\0');
            String BetterKeyPart3 = BetterKeyPart2.replace("[", "\0");
            String BetterKeyPart4 = BetterKeyPart3.replace("]","\0");
            //System.out.println("\nCheia K+ introdusa: "+key+"\n");
            KinBinary = ("K+ key written in binary:\n"+BetterKeyPart4+"\n");
            System.out.println("\nK+ key written in binary: "+BetterKeyPart4+"\n");
            for (int i = 0; i < 16; i++) {
                System.out.println("Left circular rotations: ");
                System.out.println("(C"+(i+1)+" si D"+(i+1)+")");
                key = leftCircularShift(
                        key.substring(0, 7), shiftBits[i])
                        + leftCircularShift(key.substring(7, 14),
                        shiftBits[i]);
                System.out.println("Concatenation Result C[i] si D[i]:\n"+hextoBin(key));
                System.out.println("Final permutation with the PC2 table: ");
                keys[i] = permutation(PC2, key);
                System.out.println("\n");
                String BetterKeysPart1 = java.util.Arrays.toString(hextoBin(keys[i]).split("(?<=\\G.{6})"));
                String BetterKeysPart2 = BetterKeysPart1.replace(',','\0');
                String BetterKeysPart3 = BetterKeysPart2.replace("[", "\0");
                String BetterKeysPart4 = BetterKeysPart3.replace("]","\0");
                if (i+1==choice){
                //System.out.println("C"+(i+1)+"D"+(i+1)+" = "+ BetterKeysPart4);
                    fokenmessage = ("C"+(i+1)+"D"+(i+1)+" = "+ BetterKeysPart4);}
            }
            //System.out.println(Returned);
            return keys;
        }
    }
        public static void main(String args[]) {
            UI f = new UI();
            f.CreateFrame();
            f.Button();
        }
    }

