package net.brainfuck.executer;

import net.brainfuck.common.*;
import net.brainfuck.common.Reader;
import net.brainfuck.exception.*;
import net.brainfuck.exception.FileNotFoundException;
import net.brainfuck.exception.IOException;
import net.brainfuck.interpreter.AbstractExecute;
import net.brainfuck.interpreter.BfCompiler;
import net.brainfuck.interpreter.JumpTable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static net.brainfuck.common.ArgumentConstante.PATH;

// TODO: Auto-generated Javadoc
/**
 * The Class Executer.
 *
 * @author davidLANG
 */
public class Executer {
    private List<ContextExecuter> contextExecuters = new ArrayList<>();
    private ArgumentExecuter argumentExecuter;

    /**
	 * Initialize contextExecuters, memory and reader.
	 *
	 * @param argumentAnalyzer
	 *            the argument analyzer
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws BracketsParseException
	 *             the brackets parse exception
	 * @throws SyntaxErrorException
	 *             the syntax error exception
	 */
    public Executer(ArgumentAnalyzer argumentAnalyzer) throws IOException, FileNotFoundException, BracketsParseException, java.io.IOException, SyntaxErrorException {

		// Initialize context executer
		this.contextExecuters.add(Context.contextMap.get(Context.UNCHECK.getSyntax()));
		if (argumentAnalyzer.getFlags().size() > 0) {
			this.contextExecuters.remove(Context.contextMap.get(Context.UNCHECK.getSyntax()));
		}
		for (String argument : argumentAnalyzer.getFlags()) {
			this.contextExecuters.add(Context.contextMap.get(argument));
		}
		this.argumentExecuter = init(argumentAnalyzer);
	}

    /**
     * Execute the AbstractExecute command according to the context.
     *
     * @param i AbstractExecute command to execute
     * @throws MemoryOutOfBoundsException Throw by memory
     * @throws BracketsParseException     Throw by Interpreter
     * @throws MemoryOverFlowException    Throw by memory
     * @throws FileNotFoundIn             Throw by reader
     * @throws IOException                Throw by reader
     */
    public void execute(AbstractExecute i) throws MemoryOutOfBoundsException, BracketsParseException,
            MemoryOverFlowException, FileNotFoundIn, IOException {
        for (ContextExecuter c : contextExecuters) {
            c.execute(i, argumentExecuter);
            //
        }
    }

    /**
     * This function must be called when all instruction have been read and execute
     * She throw an error if the program has no enought parenthesis
     * She close the Reader.*
     * She close the imageWriter if the long argument "--translate" have been passed
     *
     * @throws BracketsParseException throw if the program have more "[" than "]"
     * @throws IOException            throw by reader.closeReader() and imageWrite.close()
     * @throws FileNotFoundException  throw by reader.closeReader() and imageWrite.close()
     */
    public void end() throws BracketsParseException, IOException, FileNotFoundException {
        argumentExecuter.getReader().closeReader();

        int index;
        if ((index = this.contextExecuters.indexOf(Context.contextMap.get(Context.CHECK.getSyntax()))) >= 0) {
            CheckExecuter checkExecuter = (CheckExecuter) this.contextExecuters.get(index);
            if (checkExecuter.getCpt() > 0) {
                throw new BracketsParseException();
            }
        }
        if (this.contextExecuters.indexOf(Context.contextMap.get(Context.TRANSLATE.getSyntax())) >= 0) {
            argumentExecuter.getImageWriter().close();
        }
        if(Logger.getInstance().isWriterOpen()){
            Logger.getInstance().closeWriter();
        }
    }


    /**
	 * Inits the argument executer.
	 *
	 * @param a
	 *            the a
	 * @param m
	 *            the m
	 * @param r
	 *            the r
	 * @param jumpTable
	 *            the jump table
	 * @return the argument executer
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
    private ArgumentExecuter initArgumentExecuter(ArgumentAnalyzer a, Memory m, Reader r, JumpTable jumpTable) throws IOException, FileNotFoundException {
        BfImageWriter bfImageWriter = null;

        if(a.getFlags().contains(Context.TRANSLATE.getSyntax())) {
            bfImageWriter = new BfImageWriter();
        }

        return new ArgumentExecuter(m, r, bfImageWriter, jumpTable);
    }


    /**
	 * Inits the.
	 *
	 * @param argAnalizer
	 *            the arg analizer
	 * @return the argument executer
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws SyntaxErrorException
	 *             the syntax error exception
	 * @throws BracketsParseException
	 *             the brackets parse exception
	 */
    private ArgumentExecuter init(ArgumentAnalyzer argAnalizer) throws FileNotFoundException, IOException, SyntaxErrorException, BracketsParseException, java.io.IOException {
        Reader r;
        if (argAnalizer.getArgument(PATH).endsWith(".bmp")) {
            r = new BfImageReader(argAnalizer.getArgument(PATH));
        } else {
            r = new BfReader(argAnalizer.getArgument(PATH));
        }

        Memory m = new Memory();
        /*Reader r = this.initReader(argAnalizer);
        JumpTable jumpTable = initJumpTable(argAnalizer);*/
        Pair <Reader, JumpTable> readerAndJump = new BfCompiler(r).compile(contextExecuters);
        return initArgumentExecuter(argAnalizer, m, readerAndJump.getFirst(), readerAndJump.getSecond());
    }

	/**
	 * Gets the argument executer.
	 *
	 * @return the argument executer
	 */
	public ArgumentExecuter getArgumentExecuter() {
		return argumentExecuter;
	}

	/**
	 * Gets the context executers.
	 *
	 * @return the context executers
	 */
	public List<ContextExecuter> getContextExecuters() {
		return contextExecuters;
	}
}
