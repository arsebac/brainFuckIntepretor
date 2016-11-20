package net.brainfuck.executer;

import net.brainfuck.common.ArgumentExecuter;
import net.brainfuck.common.BfImageWriter;
import net.brainfuck.common.Memory;
import net.brainfuck.common.Reader;
import net.brainfuck.exception.*;
import net.brainfuck.interpreter.InstructionInterface;

// TODO: Auto-generated Javadoc
/**
 * The Class TraceExecuter.
 *
 * @author Alexandre
 */
public class TraceExecuter implements ContextExecuter{

    /**
	 * Execute the AbstractExecute command according to the "--trace" context.
	 *
	 * @param i
	 *            the AbstractCommand to execute
	 * @param argumentExecuter
	 *            the argument executer
	 * @throws MemoryOverFlowException
	 *             the memory over flow exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws MemoryOutOfBoundsException
	 *             the memory out of bounds exception
	 * @throws FileNotFoundIn
	 *             the file not found in
	 * @throws BracketsParseException
	 *             the brackets parse exception
	 */
    @Override
    public void execute(InstructionInterface i, ArgumentExecuter argumentExecuter) throws MemoryOverFlowException, IOException, MemoryOutOfBoundsException, FileNotFoundIn, BracketsParseException {
        i.trace(argumentExecuter);
    }
}
