// Generated from /home/maxou/Documents/Cours/M1/S2/Compilation/PMachine/src/PCode.g4 by ANTLR 4.7.2

import java.util.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PCodeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, INT=22, WS=23;
	public static final int
		RULE_program = 0, RULE_instruction = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "instruction"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'ADD'", "'SUB'", "'MUL'", "'DIV'", "'EQL'", "'NEQ'", "'GTR'", 
			"'LSS'", "'GEQ'", "'LEQ'", "'PRN'", "'INN'", "'INC'", "'-'", "'LDI'", 
			"'LDA'", "'LDV'", "'STO'", "'BRN'", "'BZE'", "'HLT'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "INT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PCode.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    private ArrayList<Instruction> pcode;

	    public  Instruction[] lire() throws Exception {
	        pcode = new ArrayList<Instruction>();
	        program();
	        return pcode.toArray(new Instruction[]{});
	    }

	public PCodeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public List<TerminalNode> INT() { return getTokens(PCodeParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(PCodeParser.INT, i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCodeListener ) ((PCodeListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCodeListener ) ((PCodeListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << INT))) != 0)) {
				{
				{
				setState(5);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(4);
					match(INT);
					}
				}

				setState(7);
				instruction();
				}
				}
				setState(12);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionContext extends ParserRuleContext {
		public Token INT;
		public TerminalNode INT() { return getToken(PCodeParser.INT, 0); }
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCodeListener ) ((PCodeListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCodeListener ) ((PCodeListener)listener).exitInstruction(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instruction);
		try {
			setState(66);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(13);
				match(T__0);
				pcode.add(new Instruction.ADD());
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(15);
				match(T__1);
				pcode.add(new Instruction.SUB());
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(17);
				match(T__2);
				pcode.add(new Instruction.MUL());
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(19);
				match(T__3);
				pcode.add(new Instruction.DIV());
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(21);
				match(T__4);
				pcode.add(new Instruction.EQL());
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(23);
				match(T__5);
				pcode.add(new Instruction.NEQ());
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(25);
				match(T__6);
				pcode.add(new Instruction.GTR());
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(27);
				match(T__7);
				pcode.add(new Instruction.LSS());
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(29);
				match(T__8);
				pcode.add(new Instruction.GEQ());
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(31);
				match(T__9);
				pcode.add(new Instruction.LEQ());
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(33);
				match(T__10);
				pcode.add(new Instruction.PRN());
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(35);
				match(T__11);
				pcode.add(new Instruction.INN());
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(37);
				match(T__12);
				setState(38);
				match(T__13);
				setState(39);
				((InstructionContext)_localctx).INT = match(INT);
				pcode.add(new Instruction.INC(-(((InstructionContext)_localctx).INT!=null?Integer.valueOf(((InstructionContext)_localctx).INT.getText()):0)));
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(41);
				match(T__12);
				setState(42);
				((InstructionContext)_localctx).INT = match(INT);
				pcode.add(new Instruction.INC((((InstructionContext)_localctx).INT!=null?Integer.valueOf(((InstructionContext)_localctx).INT.getText()):0)));
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(44);
				match(T__14);
				setState(45);
				match(T__13);
				setState(46);
				((InstructionContext)_localctx).INT = match(INT);
				pcode.add(new Instruction.LDI(-(((InstructionContext)_localctx).INT!=null?Integer.valueOf(((InstructionContext)_localctx).INT.getText()):0)));
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(48);
				match(T__14);
				setState(49);
				((InstructionContext)_localctx).INT = match(INT);
				pcode.add(new Instruction.LDI((((InstructionContext)_localctx).INT!=null?Integer.valueOf(((InstructionContext)_localctx).INT.getText()):0)));
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(51);
				match(T__15);
				setState(52);
				((InstructionContext)_localctx).INT = match(INT);
				pcode.add(new Instruction.LDA((((InstructionContext)_localctx).INT!=null?Integer.valueOf(((InstructionContext)_localctx).INT.getText()):0)));
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(54);
				match(T__16);
				pcode.add(new Instruction.LDV());
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(56);
				match(T__17);
				pcode.add(new Instruction.STO());
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(58);
				match(T__18);
				setState(59);
				((InstructionContext)_localctx).INT = match(INT);
				pcode.add(new Instruction.BRN((((InstructionContext)_localctx).INT!=null?Integer.valueOf(((InstructionContext)_localctx).INT.getText()):0)));
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(61);
				match(T__19);
				setState(62);
				((InstructionContext)_localctx).INT = match(INT);
				pcode.add(new Instruction.BZE((((InstructionContext)_localctx).INT!=null?Integer.valueOf(((InstructionContext)_localctx).INT.getText()):0)));
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(64);
				match(T__20);
				pcode.add(new Instruction.HLT());
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\31G\4\2\t\2\4\3\t"+
		"\3\3\2\5\2\b\n\2\3\2\7\2\13\n\2\f\2\16\2\16\13\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3E\n\3\3\3\2\2\4"+
		"\2\4\2\2\2[\2\f\3\2\2\2\4D\3\2\2\2\6\b\7\30\2\2\7\6\3\2\2\2\7\b\3\2\2"+
		"\2\b\t\3\2\2\2\t\13\5\4\3\2\n\7\3\2\2\2\13\16\3\2\2\2\f\n\3\2\2\2\f\r"+
		"\3\2\2\2\r\3\3\2\2\2\16\f\3\2\2\2\17\20\7\3\2\2\20E\b\3\1\2\21\22\7\4"+
		"\2\2\22E\b\3\1\2\23\24\7\5\2\2\24E\b\3\1\2\25\26\7\6\2\2\26E\b\3\1\2\27"+
		"\30\7\7\2\2\30E\b\3\1\2\31\32\7\b\2\2\32E\b\3\1\2\33\34\7\t\2\2\34E\b"+
		"\3\1\2\35\36\7\n\2\2\36E\b\3\1\2\37 \7\13\2\2 E\b\3\1\2!\"\7\f\2\2\"E"+
		"\b\3\1\2#$\7\r\2\2$E\b\3\1\2%&\7\16\2\2&E\b\3\1\2\'(\7\17\2\2()\7\20\2"+
		"\2)*\7\30\2\2*E\b\3\1\2+,\7\17\2\2,-\7\30\2\2-E\b\3\1\2./\7\21\2\2/\60"+
		"\7\20\2\2\60\61\7\30\2\2\61E\b\3\1\2\62\63\7\21\2\2\63\64\7\30\2\2\64"+
		"E\b\3\1\2\65\66\7\22\2\2\66\67\7\30\2\2\67E\b\3\1\289\7\23\2\29E\b\3\1"+
		"\2:;\7\24\2\2;E\b\3\1\2<=\7\25\2\2=>\7\30\2\2>E\b\3\1\2?@\7\26\2\2@A\7"+
		"\30\2\2AE\b\3\1\2BC\7\27\2\2CE\b\3\1\2D\17\3\2\2\2D\21\3\2\2\2D\23\3\2"+
		"\2\2D\25\3\2\2\2D\27\3\2\2\2D\31\3\2\2\2D\33\3\2\2\2D\35\3\2\2\2D\37\3"+
		"\2\2\2D!\3\2\2\2D#\3\2\2\2D%\3\2\2\2D\'\3\2\2\2D+\3\2\2\2D.\3\2\2\2D\62"+
		"\3\2\2\2D\65\3\2\2\2D8\3\2\2\2D:\3\2\2\2D<\3\2\2\2D?\3\2\2\2DB\3\2\2\2"+
		"E\5\3\2\2\2\5\7\fD";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}