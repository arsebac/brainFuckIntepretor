package net.brainfuck.common;

import net.brainfuck.Main;
import net.brainfuck.exception.*;
import net.brainfuck.exception.FileNotFoundException;
import net.brainfuck.exception.IOException;
import net.brainfuck.executer.Context;
import net.brainfuck.executer.Executer;
import net.brainfuck.interpreter.BfCompiler;
import net.brainfuck.interpreter.Interpreter;
import net.brainfuck.interpreter.JumpTable;
import net.brainfuck.interpreter.Language;

import java.io.*;
import java.util.List;

import static net.brainfuck.common.ArgumentConstante.PATH;

/**
 * @author Francois Melkonian
 * @date 30/11/2016
 */
public class Initialyzer {
	private Memory memory;
	private Reader reader;
	private Interpreter interpreter;
	private Executer executer;


	private ArgumentAnalyzer argumentAnalyzer;

	public Initialyzer(String[] args) {
		try {
			argumentAnalyzer = new ArgumentAnalyzer(args);
			init(argumentAnalyzer);

			Logger.getInstance().startExecTime();
			interpreter.interprate();
			System.out.println(Logger.getInstance().showResume(memory));
		} catch (IOException | SyntaxErrorException | FileNotFoundException | IncorrectArgumentException e) {
			// Exit code not set
			System.exit(5);
		} catch (MemoryOutOfBoundsException e) {
			System.exit(1);
		} catch (MemoryOverFlowException e) {
			System.exit(2);
		} catch (FileNotFoundIn e) {
			System.exit(3);
		} catch (BracketsParseException e) {
			System.exit(4);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		System.exit(0);

	}


	public void analyzeArg() throws IOException {
		if (argumentAnalyzer.getArgument(PATH) == null) {
			Main.printUsage();
			System.exit(0);
		}
		initLoggerFromContext(argumentAnalyzer);
	}

	/**
	 * Inits the.
	 *
	 * @param argumentAnalyzer
	 * @throws FileNotFoundException  the file not found exception
	 * @throws IOException            Signals that an I/O exception has occurred.
	 * @throws IOException            Signals that an I/O exception has occurred.
	 * @throws SyntaxErrorException   the syntax error exception
	 * @throws BracketsParseException the brackets parse exception
	 */
	private void init(ArgumentAnalyzer argumentAnalyzer) throws FileNotFoundException, IncorrectArgumentException, IOException, SyntaxErrorException, java.io.IOException, BracketsParseException {
		analyzeArg();
		initReader();
		BfImageWriter bfImageWriter = null;
		if (argumentAnalyzer.getFlags().contains(Context.TRANSLATE.getSyntax())) {
			bfImageWriter = new BfImageWriter(getOut());
		}
		Context.setExceuter(bfImageWriter);
		executer = new Executer(argumentAnalyzer);
		Language.setInstructions(getIn(), new OutputStreamWriter(getOut()), new JumpTable());
		BfCompiler compiler = new BfCompiler(reader, executer.getContextExecuters());
		memory = new Memory();
		Pair<List<Language>, JumpTable> readerAndJump = compiler.compile(executer.getContextExecuters());
		JumpTable jumpTable = readerAndJump.getSecond();
		Language.setJumpTabel(jumpTable);

		executer.setArgumentExecuter(memory, bfImageWriter, jumpTable);
		interpreter = new Interpreter(executer, new ExecutionReader(readerAndJump.getFirst()));
	}


	private void initReader() throws FileNotFoundException {
		if (argumentAnalyzer.getArgument(PATH).endsWith(".bmp")) {
			reader = new BfImageReader(argumentAnalyzer.getArgument(PATH));
		} else {
			reader = new BfReader(argumentAnalyzer.getArgument(PATH));
		}
	}

	public void analyzeFonctions() {
		// TODO : déplacer la méthode ici
	}

	public void analyzeSemantic() {
		// TODO : déplacer la méthode ici
	}

	/**
	 * Initialise the logger from context.
	 * Useful when --trace args is used
	 *
	 * @param argAnalizer the arg analizer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void initLoggerFromContext(ArgumentAnalyzer argAnalizer) throws IOException {
		if (argAnalizer.getFlags().contains(Context.TRACE.getSyntax())) {
			Logger.getInstance().setWriter(argAnalizer.getArgument(PATH));
		}
	}

	public InputStreamReader getIn() throws FileNotFoundException {
		String inputPath = null;
		try {
			inputPath = argumentAnalyzer.getArgument(ArgumentConstante.IN_PATH);
			if (inputPath == null)
				return new InputStreamReader(System.in);

			return new InputStreamReader(new FileInputStream(inputPath));

		} catch (java.io.FileNotFoundException e) {
			throw new FileNotFoundException(inputPath);
		}
	}

	public OutputStream getOut() throws FileNotFoundException {
		String inputPath = null;
		try {
			inputPath = argumentAnalyzer.getArgument(ArgumentConstante.IN_PATH);
			if (inputPath == null)
				return System.out;

			return new FileOutputStream(inputPath);

		} catch (java.io.FileNotFoundException e) {
			throw new FileNotFoundException(inputPath);
		}
	}


}