package net.brainfucktest.common;

import net.brainfuck.Main;

public class Test {

    public static void main(String[] args) {
        String[] args2 = {"-p", "Brainfuck/src/test/resources/assets/brainfucktest/common/TestIn.bf","-i","C:/Users/user/Desktop/b"};
        new Main(args2); // On lance sur un fichier au hasard
    }
}
