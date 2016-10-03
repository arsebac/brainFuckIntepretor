package net.brainfucktest.common;

import net.brainfuck.Main;
import net.brainfuck.exception.FileNotFoundException;

/**
 * Created by François Melkonian
 * Some test about errors
 */
public class TestErrors {
    public static void main(String[] args) throws FileNotFoundException, java.io.FileNotFoundException {
        new Main("/assets/brainfucktest/common/OutOfBoundLeft.bf");
        //new Main("/assets/brainfucktest/common/OutOfBoundRight.bf");
        // new Main("/assets/brainfucktest/common/OverflowDecr.bf");
        // new Main("/assets/brainfucktest/common/OverflowIncr.bf");
    }

}
