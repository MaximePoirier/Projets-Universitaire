// Generated from /home/maxou/Documents/Cours/M1/S2/Compilation/PMachine/src/Pascal.g4 by ANTLR 4.7.2

import java.util.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PascalParser}.
 */
public interface PascalListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PascalParser#declarations}.
	 * @param ctx the parse tree
	 */
	void enterDeclarations(PascalParser.DeclarationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#declarations}.
	 * @param ctx the parse tree
	 */
	void exitDeclarations(PascalParser.DeclarationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(PascalParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(PascalParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#declavar}.
	 * @param ctx the parse tree
	 */
	void enterDeclavar(PascalParser.DeclavarContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#declavar}.
	 * @param ctx the parse tree
	 */
	void exitDeclavar(PascalParser.DeclavarContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#declafunc}.
	 * @param ctx the parse tree
	 */
	void enterDeclafunc(PascalParser.DeclafuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#declafunc}.
	 * @param ctx the parse tree
	 */
	void exitDeclafunc(PascalParser.DeclafuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#typi}.
	 * @param ctx the parse tree
	 */
	void enterTypi(PascalParser.TypiContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#typi}.
	 * @param ctx the parse tree
	 */
	void exitTypi(PascalParser.TypiContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(PascalParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(PascalParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#fields}.
	 * @param ctx the parse tree
	 */
	void enterFields(PascalParser.FieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#fields}.
	 * @param ctx the parse tree
	 */
	void exitFields(PascalParser.FieldsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(PascalParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(PascalParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#instructions}.
	 * @param ctx the parse tree
	 */
	void enterInstructions(PascalParser.InstructionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#instructions}.
	 * @param ctx the parse tree
	 */
	void exitInstructions(PascalParser.InstructionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(PascalParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(PascalParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#fonction}.
	 * @param ctx the parse tree
	 */
	void enterFonction(PascalParser.FonctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#fonction}.
	 * @param ctx the parse tree
	 */
	void exitFonction(PascalParser.FonctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(PascalParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(PascalParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(PascalParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(PascalParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(PascalParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(PascalParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#simple_expression}.
	 * @param ctx the parse tree
	 */
	void enterSimple_expression(PascalParser.Simple_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#simple_expression}.
	 * @param ctx the parse tree
	 */
	void exitSimple_expression(PascalParser.Simple_expressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PascalParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(PascalParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PascalParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(PascalParser.ProgramContext ctx);
}