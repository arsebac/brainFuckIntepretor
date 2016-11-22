package net.brainfuck.interpreter;

import net.brainfuck.common.ArgumentInstruction;
import net.brainfuck.exception.MemoryOutOfBoundsException;
import net.brainfuck.exception.MemoryOverFlowException;

// TODO: Auto-generated Javadoc
/**
 * The Class IncrementInstruction.
 *
 * @author davidLANG
 */
public class IncrementInstruction extends AbstractExecute {

    /**
	 * Instantiates a new incremante instruction.
	 */
    public IncrementInstruction() {
		super(Language.INCR);
	}

	/**
	 * Execute the "incr" method of Memory Class.
	 *
	 * @param argumentInstruction
	 *            the argument instruction
	 * @throws MemoryOverFlowException
	 *             the memory over flow exception
	 * @throws MemoryOutOfBoundsException
	 *             the memory out of bounds exception
	 */
    @Override
    public void execute(ArgumentInstruction argumentInstruction) throws MemoryOverFlowException, MemoryOutOfBoundsException {
        argumentInstruction.getMemory().incr();
    }

}