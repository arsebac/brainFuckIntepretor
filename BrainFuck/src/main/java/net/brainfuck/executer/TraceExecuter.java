package net.brainfuck.executer;

import net.brainfuck.common.ExecutionReader;
import net.brainfuck.common.Memory;
import net.brainfuck.exception.*;
import net.brainfuck.interpreter.instruction.InstructionInterface;

/**
 * Execute the AbstractInstruction command according to the "--trace" context.
 *
 * @author Alexandre
 */
public class TraceExecuter implements ContextExecuter {

	/**
	 * Execute the AbstractInstruction command according to the "--trace" context.
	 *
	 * @param i                the AbstractCommand to execute
	 * @param memory the memory
	 * @throws MemoryOverFlowException    the memory over flow exception
	 * @throws IOException                Signals that an I/O exception has occurred.
	 * @throws MemoryOutOfBoundsException the memory out of bounds exception
	 * @throws FileNotFoundIn             the file not found in
	 * @throws BracketsParseException     the brackets parse exception
	 * @throws SegmentationFaultException 
	 */
	@Override
	public void execute(InstructionInterface i, Memory memory, ExecutionReader reader) throws MemoryOverFlowException,
			IOException, MemoryOutOfBoundsException, FileNotFoundIn, BracketsParseException, SegmentationFaultException {
		i.trace(memory, reader);
	}
}
