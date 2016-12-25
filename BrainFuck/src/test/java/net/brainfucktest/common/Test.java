package net.brainfucktest.common;

import net.brainfuck.Main;

/**
 * The Class Test.
 */
public class Test {

    /**
	 * The main method.
	 *
	 * @param args the arguments
	 */
    public static void main(String[] args) {
	    String[] args2 = {"-p", "BrainFuck/src/test/resources/assets/brainfucktest/common/Comment.bf","-i", "BrainFuck/src/test/resources/assets/brainfucktest/common/EmptyFile.bf"};
	    Main.main(args2); // On lance sur un fichier au hasard
    }
}
