package net.brainfuck.interpreter.instruction;

import net.brainfuck.common.Logger;
import net.brainfuck.common.Memory;
import net.brainfuck.common.executable.Executable;
import net.brainfuck.exception.*;
import net.brainfuck.interpreter.Language;

/**
 * Abstraction of a instruction
 */
public abstract class AbstractInstruction implements InstructionInterface {

    private Language languageInstr;
    private Logger logger;

    /**
     * Instantiates a new abstract execute.
     *
     * @param languageInstr the language instr
     */
    protected AbstractInstruction(Language languageInstr) {
        this.languageInstr = languageInstr;
        logger = Logger.getInstance();
    }

    public AbstractInstruction() {
    }

    /**
     * Print the short syntax of the command which implement this interface.
     */
    @Override
    public String rewrite() {
        return languageInstr.getShortSyntax();
    }

    /**
     * Return the color (in hexa) which represent the instruction
     *
     * @return String hexa which represent the color of the current instruction
     */
    @Override
    public String translate() {
        return languageInstr.getColorSyntax();
    }

    @Override
    public String generate() {
        return languageInstr.getCSyntax();
    }

    /**
     * Execute the instruction and write the trace.
     *
     * @param memory the memory
     * @throws IOException                throw by inReader
     * @throws MemoryOutOfBoundsException throw by memory
     * @throws BracketsParseException     throw by interpreter
     * @throws MemoryOverFlowException    throw by memory
     * @throws FileNotFoundIn             throw by writer
     * @throws SegmentationFaultException throw by memory
     */
    @Override
    public void trace(Memory memory, Executable reader) throws IOException, MemoryOutOfBoundsException, BracketsParseException, MemoryOverFlowException, FileNotFoundIn, SegmentationFaultException {
        execute(memory);
        logger.write(reader.getName(), reader.getExecutionPointer(), memory);
    }

    @Override
    public abstract void execute(Memory memory) throws MemoryOutOfBoundsException, MemoryOverFlowException, IOException, FileNotFoundIn, BracketsParseException, SegmentationFaultException;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractInstruction)) return false;

        AbstractInstruction that = (AbstractInstruction) o;

        if (languageInstr != that.languageInstr) return false;
        return logger != null ? logger.equals(that.logger) : that.logger == null;

    }

    @Override
    public int hashCode() {
        int result = languageInstr != null ? languageInstr.hashCode() : 0;
        result = 31 * result + (logger != null ? logger.hashCode() : 0);
        return result;
    }
}
