package net.brainfuck.interpreter.instruction.intoutinsruction;

import net.brainfuck.common.Memory;
import net.brainfuck.exception.IOException;
import net.brainfuck.exception.MemoryOutOfBoundsException;
import net.brainfuck.exception.MemoryOverFlowException;
import net.brainfuck.interpreter.Language;

import java.io.OutputStreamWriter;


/**
 * Representation of OUT instruction "." "OUT".
 *
 * @author FooBar Team
 */
public class OutInstruction extends InOutInstruction {
	OutputStreamWriter writer;

	/**
	 * Instantiates a new out instruction.
	 */
	public OutInstruction(OutputStreamWriter writer) {
		super(Language.OUT);
		this.writer = writer;
	}

	/**
	 * Print the value on current index, call "get" method from class Memory.
	 *
	 * @param memory the memory
	 * @throws MemoryOverFlowException    the memory over flow exception
	 * @throws MemoryOutOfBoundsException the memory out of bounds exception
	 */
	@Override
	public void execute(Memory memory) throws MemoryOverFlowException, MemoryOutOfBoundsException, IOException {
		try {
			writer.write((char) memory.get());
			writer.flush();
		} catch (java.io.IOException e) {
			throw new IOException();
		}
	}

}
