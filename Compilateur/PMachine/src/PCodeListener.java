// Generated from /home/maxou/Documents/Cours/M1/S2/Compilation/PMachine/src/PCode.g4 by ANTLR 4.7.2

import java.util.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PCodeParser}.
 */
public interface PCodeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PCodeParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(PCodeParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCodeParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(PCodeParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCodeParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(PCodeParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCodeParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(PCodeParser.InstructionContext ctx);
}