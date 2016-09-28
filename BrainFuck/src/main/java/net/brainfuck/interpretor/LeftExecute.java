package net.brainfuck.interpretor;

import net.brainfuck.common.Memory;
import net.brainfuck.exception.MemoryOutOfBoundsException;

/**
 * @author davidLANG
 */
class LeftExecute implements InterpretorInterface {
    /**
     * Execute the "left" method of Memory Class
     * @param machine Memory machine
     */
    @Override
    public void execute(Memory machine) throws MemoryOutOfBoundsException {
        machine.left();
    }
}
