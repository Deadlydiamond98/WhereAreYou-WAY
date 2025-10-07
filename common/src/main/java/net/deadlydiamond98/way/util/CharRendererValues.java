package net.deadlydiamond98.way.util;

import java.util.HashMap;

public class CharRendererValues {

    public static final HashMap<Character, int[]> CHAR_VALUES = new HashMap<>();

    private static final char[] CHARS = new char[] {' ', '!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~'};

    private static final char[][] SP = new char[][] {
            {'!', '\'', ',', '.', ':', ';', 'i', '|'},
            {'`', 'l'},
            {'\"', '(', ')', '*', 'I', '[', ']', 't', '{', '}'},
            {'<', '>', 'f', 'k'}
    };

    static {
        for (int i = 0; i < CHARS.length; i++) {
            int x = i % 16;
            int y = i / 16;
            CHAR_VALUES.put(CHARS[i], new int[] {x, y, getCharPxlLength(CHARS[i])});
        }
    }

    private static int getCharPxlLength(char letter) {
        if (letter != '@' && letter != '~') {
            for (int i = 0; i < SP.length; i++) {
                for (int j = 0; j < SP[i].length; j++) {
                    if (letter == SP[i][j]) {
                        return i + 3;
                    }
                }
            }

            return 7;
        }
        return 8;
    }
}
