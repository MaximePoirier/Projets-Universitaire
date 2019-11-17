// Generated from /home/maxou/Documents/Cours/M1/S2/Compilation/PMachine/src/Pascal.g4 by ANTLR 4.7.2

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
public class PascalParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, ID=38, INT=39, 
		WS=40;
	public static final int
		RULE_declarations = 0, RULE_declaration = 1, RULE_declavar = 2, RULE_declafunc = 3, 
		RULE_typi = 4, RULE_variable = 5, RULE_fields = 6, RULE_field = 7, RULE_instructions = 8, 
		RULE_instruction = 9, RULE_fonction = 10, RULE_expression = 11, RULE_factor = 12, 
		RULE_term = 13, RULE_simple_expression = 14, RULE_program = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"declarations", "declaration", "declavar", "declafunc", "typi", "variable", 
			"fields", "field", "instructions", "instruction", "fonction", "expression", 
			"factor", "term", "simple_expression", "program"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'var'", "','", "':'", "'function'", "'('", "')'", "'begin'", 
			"'end'", "'integer'", "'array'", "'['", "']'", "'of'", "'record'", "'.'", 
			"':='", "'if'", "'then'", "'else'", "'while'", "'do'", "'read'", "'write'", 
			"'colorLed'", "'sleepLed'", "'switchLightZumo'", "'='", "'<>'", "'<'", 
			"'>'", "'<='", "'>='", "'*'", "'/'", "'+'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "ID", "INT", "WS"
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
	public String getGrammarFileName() { return "Pascal.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    private ArrayList<Instruction> pcode;
	    private SymboleTable symbtable;
	    private HashMap<String, Integer> vars;

	    public  Instruction[] lire() throws Exception {
	        pcode = new ArrayList<Instruction>();
	        symbtable = new SymboleTable();
	        vars = new HashMap<String, Integer>();
	        program();
	        return pcode.toArray(new Instruction[]{});
	    }

	public PascalParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class DeclarationsContext extends ParserRuleContext {
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public DeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).enterDeclarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).exitDeclarations(this);
		}
	}

	public final DeclarationsContext declarations() throws RecognitionException {
		DeclarationsContext _localctx = new DeclarationsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			declaration();
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(33);
				match(T__0);
				setState(34);
				declaration();
				}
				}
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			            pcode.add(0, new Instruction.INC(symbtable.getSize()));
			    
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

	public static class DeclarationContext extends ParserRuleContext {
		public DeclavarContext declavar() {
			return getRuleContext(DeclavarContext.class,0);
		}
		public DeclafuncContext declafunc() {
			return getRuleContext(DeclafuncContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		try {
			setState(44);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				declavar();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				declafunc();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class DeclavarContext extends ParserRuleContext {
		public Token ID;
		public TypiContext typi;
		public List<TerminalNode> ID() { return getTokens(PascalParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PascalParser.ID, i);
		}
		public TypiContext typi() {
			return getRuleContext(TypiContext.class,0);
		}
		public DeclavarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declavar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).enterDeclavar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).exitDeclavar(this);
		}
	}

	public final DeclavarContext declavar() throws RecognitionException {
		DeclavarContext _localctx = new DeclavarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declavar);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			ArrayList<String> ids = new ArrayList<String>();
			setState(47);
			match(T__1);
			setState(53);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(48);
					((DeclavarContext)_localctx).ID = match(ID);

					        ids.add((((DeclavarContext)_localctx).ID!=null?((DeclavarContext)_localctx).ID.getText():null));
					    
					setState(50);
					match(T__2);
					}
					} 
				}
				setState(55);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(56);
			((DeclavarContext)_localctx).ID = match(ID);

			            ids.add((((DeclavarContext)_localctx).ID!=null?((DeclavarContext)_localctx).ID.getText():null));
			        
			setState(58);
			match(T__3);
			setState(59);
			((DeclavarContext)_localctx).typi = typi();

			                symbtable.putListVar(ids,((DeclavarContext)_localctx).typi.type);
			            
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

	public static class DeclafuncContext extends ParserRuleContext {
		public Token idFunc;
		public TypiContext t1;
		public Token i1;
		public TypiContext t2;
		public Token i2;
		public TypiContext t3;
		public InstructionsContext instructions() {
			return getRuleContext(InstructionsContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(PascalParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PascalParser.ID, i);
		}
		public List<TypiContext> typi() {
			return getRuleContexts(TypiContext.class);
		}
		public TypiContext typi(int i) {
			return getRuleContext(TypiContext.class,i);
		}
		public List<DeclarationsContext> declarations() {
			return getRuleContexts(DeclarationsContext.class);
		}
		public DeclarationsContext declarations(int i) {
			return getRuleContext(DeclarationsContext.class,i);
		}
		public DeclafuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declafunc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).enterDeclafunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).exitDeclafunc(this);
		}
	}

	public final DeclafuncContext declafunc() throws RecognitionException {
		DeclafuncContext _localctx = new DeclafuncContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declafunc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(T__4);
			setState(63);
			((DeclafuncContext)_localctx).idFunc = match(ID);

			            System.out.println("--declafunc--");
			            //definition de fonction
			            vars.put((((DeclafuncContext)_localctx).idFunc!=null?((DeclafuncContext)_localctx).idFunc.getText():null), pcode.size()); //memorise la ligne de définion de la fonction
			            pcode.add(new Instruction.BRN(-4)); //instruction factice a changer
			            //instanciation des variables
			            ArrayList<Type> types = new ArrayList<Type>();
			            ArrayList<String> noms = new ArrayList<String>();
			            symbtable.setLocal(); //pour memoriser les instructions en local
			        
			setState(65);
			match(T__5);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__14))) != 0)) {
				{
				setState(66);
				((DeclafuncContext)_localctx).t1 = typi();
				setState(67);
				((DeclafuncContext)_localctx).i1 = match(ID);
				types.add(((DeclafuncContext)_localctx).t1.type);noms.add((((DeclafuncContext)_localctx).i1!=null?((DeclafuncContext)_localctx).i1.getText():null));symbtable.putVar((((DeclafuncContext)_localctx).i1!=null?((DeclafuncContext)_localctx).i1.getText():null), ((DeclafuncContext)_localctx).t1.type);
				}
			}

			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(72);
				match(T__2);
				setState(73);
				((DeclafuncContext)_localctx).t2 = typi();
				setState(74);
				((DeclafuncContext)_localctx).i2 = match(ID);
				types.add(((DeclafuncContext)_localctx).t2.type);noms.add((((DeclafuncContext)_localctx).i2!=null?((DeclafuncContext)_localctx).i2.getText():null));symbtable.putVar((((DeclafuncContext)_localctx).i2!=null?((DeclafuncContext)_localctx).i2.getText():null), ((DeclafuncContext)_localctx).t2.type);
				}
				}
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(82);
			match(T__6);
			setState(83);
			match(T__3);
			setState(84);
			((DeclafuncContext)_localctx).t3 = typi();
			setState(85);
			match(T__0);
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1 || _la==T__4) {
				{
				{
				setState(86);
				declarations();
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(92);
			match(T__7);
			setState(93);
			instructions();
			setState(94);
			match(T__8);
			setState(95);
			match(T__0);

			                symbtable.setGlobal(); //retour en global

			                Type.Fonction f = new Type.Fonction(((DeclafuncContext)_localctx).t3.type, types, noms);
			                System.out.println("declafunc : f="+f);
			                symbtable.putVar((((DeclafuncContext)_localctx).idFunc!=null?((DeclafuncContext)_localctx).idFunc.getText():null), f); //memorisation de la fonction dans table des symboles

			                System.out.println("declafunc : RET");
			                pcode.add(new Instruction.RET(types.size()));
			                int sizeFunc = pcode.size() - vars.get((((DeclafuncContext)_localctx).idFunc!=null?((DeclafuncContext)_localctx).idFunc.getText():null));
			                pcode.set(vars.get((((DeclafuncContext)_localctx).idFunc!=null?((DeclafuncContext)_localctx).idFunc.getText():null)), new Instruction.BRN(sizeFunc + 1)); //saut de la fonction
			                System.out.println("fin declafunc");
			            
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

	public static class TypiContext extends ParserRuleContext {
		public Type type;
		public Token INT;
		public TypiContext typi;
		public FieldsContext fields;
		public TerminalNode INT() { return getToken(PascalParser.INT, 0); }
		public TypiContext typi() {
			return getRuleContext(TypiContext.class,0);
		}
		public FieldsContext fields() {
			return getRuleContext(FieldsContext.class,0);
		}
		public TypiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typi; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).enterTypi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).exitTypi(this);
		}
	}

	public final TypiContext typi() throws RecognitionException {
		TypiContext _localctx = new TypiContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_typi);
		try {
			setState(113);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				match(T__9);
				((TypiContext)_localctx).type =  new Type.Int();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				match(T__10);
				setState(101);
				match(T__11);
				setState(102);
				((TypiContext)_localctx).INT = match(INT);
				setState(103);
				match(T__12);
				setState(104);
				match(T__13);
				setState(105);
				((TypiContext)_localctx).typi = typi();
				((TypiContext)_localctx).type =  new Type.Tab((((TypiContext)_localctx).INT!=null?Integer.valueOf(((TypiContext)_localctx).INT.getText()):0),((TypiContext)_localctx).typi.type);
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 3);
				{
				setState(108);
				match(T__14);
				setState(109);
				((TypiContext)_localctx).fields = fields();
				setState(110);
				match(T__8);
				((TypiContext)_localctx).type =  new Type.Record(((TypiContext)_localctx).fields.ids,((TypiContext)_localctx).fields.types);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class VariableContext extends ParserRuleContext {
		public Type type;
		public Token ID;
		public List<TerminalNode> ID() { return getTokens(PascalParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PascalParser.ID, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			((VariableContext)_localctx).ID = match(ID);

			        System.out.println("--variable--");
			        Donnee d = symbtable.getDonnee((((VariableContext)_localctx).ID!=null?((VariableContext)_localctx).ID.getText():null));
			        System.out.println("ID.text = "+(((VariableContext)_localctx).ID!=null?((VariableContext)_localctx).ID.getText():null)+" " +"d="+d);
			        Type restype =d.getType();
			        ((VariableContext)_localctx).type = restype;

			        System.out.println("variable debutif");
			        if(symbtable.estGlobal){
			            //LDA dans le cas global
			            pcode.add(new Instruction.LDA(symbtable.getAdresse((((VariableContext)_localctx).ID!=null?((VariableContext)_localctx).ID.getText():null))));
			        }else{
			            //LDL dans le cas local
			            pcode.add(new Instruction.LDL(symbtable.getAdresse((((VariableContext)_localctx).ID!=null?((VariableContext)_localctx).ID.getText():null))));
			        }
			        System.out.println("--fin variable--");
			    
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11 || _la==T__15) {
				{
				setState(125);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__11:
					{
					setState(117);
					match(T__11);
					setState(118);
					expression();
					setState(119);
					match(T__12);

					                             restype = ((Type.Tab) restype).type;
					                             ((VariableContext)_localctx).type =  restype;
					                             pcode.add(new Instruction.LDI(((Type.Tab) d.type).getType().size()));
					                             pcode.add(new Instruction.MUL());
					                             pcode.add(new Instruction.ADD());
					                           
					}
					break;
				case T__15:
					{
					setState(122);
					match(T__15);
					setState(123);
					((VariableContext)_localctx).ID = match(ID);

					        restype = (Type.Record) restype;
					        pcode.add(new Instruction.LDI(((Type.Record) restype).adresse((((VariableContext)_localctx).ID!=null?((VariableContext)_localctx).ID.getText():null))));
					        pcode.add(new Instruction.ADD());
					        restype = ((Type.Record) restype).type((((VariableContext)_localctx).ID!=null?((VariableContext)_localctx).ID.getText():null));
					        ((VariableContext)_localctx).type =  restype;
					    
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(129);
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

	public static class FieldsContext extends ParserRuleContext {
		public ArrayList<String> ids;
		public ArrayList<Type> types;
		public FieldContext field;
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public FieldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fields; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).enterFields(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).exitFields(this);
		}
	}

	public final FieldsContext fields() throws RecognitionException {
		FieldsContext _localctx = new FieldsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fields);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			ArrayList<String> ids = new ArrayList<String>();ArrayList<Type> types = new ArrayList<Type>();
			setState(131);
			((FieldsContext)_localctx).field = field();
			ids.add(((FieldsContext)_localctx).field.id);types.add(((FieldsContext)_localctx).field.type);
			setState(139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(133);
				match(T__0);
				setState(134);
				((FieldsContext)_localctx).field = field();
				ids.add(((FieldsContext)_localctx).field.id);types.add(((FieldsContext)_localctx).field.type);
				}
				}
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			((FieldsContext)_localctx).ids =  ids;((FieldsContext)_localctx).types =  types;
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

	public static class FieldContext extends ParserRuleContext {
		public String id;
		public Type type;
		public Token ID;
		public TypiContext typi;
		public TerminalNode ID() { return getToken(PascalParser.ID, 0); }
		public TypiContext typi() {
			return getRuleContext(TypiContext.class,0);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).exitField(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			((FieldContext)_localctx).ID = match(ID);
			setState(145);
			match(T__3);
			setState(146);
			((FieldContext)_localctx).typi = typi();
			((FieldContext)_localctx).id =  (((FieldContext)_localctx).ID!=null?((FieldContext)_localctx).ID.getText():null);((FieldContext)_localctx).type =  ((FieldContext)_localctx).typi.type;
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

	public static class InstructionsContext extends ParserRuleContext {
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public InstructionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instructions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).enterInstructions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).exitInstructions(this);
		}
	}

	public final InstructionsContext instructions() throws RecognitionException {
		InstructionsContext _localctx = new InstructionsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_instructions);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(149);
					instruction();
					setState(150);
					match(T__0);
					}
					} 
				}
				setState(156);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			setState(157);
			instruction();
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
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public FonctionContext fonction() {
			return getRuleContext(FonctionContext.class,0);
		}
		public InstructionsContext instructions() {
			return getRuleContext(InstructionsContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).exitInstruction(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_instruction);
		try {
			setState(189);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(159);
				variable();
				setState(160);
				match(T__16);
				setState(161);
				expression();

				        pcode.add(new Instruction.STO());
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
				match(T__17);
				setState(165);
				expression();

				        Instruction.BZE instBZE = new Instruction.BZE(0);pcode.add(instBZE);
				    
				setState(167);
				match(T__18);
				setState(168);
				instruction();

				            instBZE.setParam(pcode.size()+1);
				            Instruction.BRN instBRN = new Instruction.BRN(pcode.size()+1);
				            pcode.add(instBRN);
				        
				setState(174);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(170);
					match(T__19);
					setState(171);
					instruction();

					                instBRN.setParam(pcode.size());
					            
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(176);
				match(T__20);

				        int inst = pcode.size();
				    
				setState(178);
				expression();

				            Instruction.BZE instBZE2 = new Instruction.BZE(0);
				            pcode.add(instBZE2);
				        
				setState(180);
				match(T__21);
				setState(181);
				instruction();

				                instBZE2.setParam(pcode.size()+1);
				                pcode.add(new Instruction.BRN(inst));
				            
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(184);
				fonction();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(185);
				match(T__7);
				setState(186);
				instructions();
				setState(187);
				match(T__8);
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

	public static class FonctionContext extends ParserRuleContext {
		public Token ID;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ID() { return getToken(PascalParser.ID, 0); }
		public List<TerminalNode> INT() { return getTokens(PascalParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(PascalParser.INT, i);
		}
		public FonctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fonction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).enterFonction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).exitFonction(this);
		}
	}

	public final FonctionContext fonction() throws RecognitionException {
		FonctionContext _localctx = new FonctionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_fonction);
		int _la;
		try {
			setState(247);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__8:
			case T__19:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 2);
				{
				setState(192);
				match(T__22);
				setState(193);
				match(T__5);
				setState(194);
				variable();
				setState(195);
				match(T__6);

				        pcode.add(new Instruction.INN());
				        pcode.add(new Instruction.STO());
				    
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 3);
				{
				setState(198);
				match(T__23);
				setState(199);
				match(T__5);
				setState(200);
				expression();
				setState(201);
				match(T__6);

				        System.out.println("--write--");
				        pcode.add(new Instruction.PRN());
				    
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 4);
				{
				setState(204);
				match(T__24);

				     pcode.add(new Instruction.LDI(3)); //fonction a appeler
				     
				setState(206);
				match(T__5);
				setState(207);
				expression();
				setState(208);
				match(T__2);
				setState(209);
				expression();
				setState(210);
				match(T__2);
				setState(211);
				expression();
				setState(212);
				match(T__6);

				        //changement de couleur
				        pcode.add(new Instruction.ROB(4,0));
				    
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 5);
				{
				setState(215);
				match(T__25);
				setState(216);
				match(T__5);
				setState(217);
				expression();
				setState(218);
				match(T__6);

				        //dormir
				        pcode.add(new Instruction.SLE());
				    
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 6);
				{
				setState(221);
				match(T__26);
				pcode.add(new Instruction.LDI(0));
				setState(223);
				match(T__5);
				setState(224);
				expression();
				setState(225);
				match(T__2);
				setState(226);
				expression();
				setState(227);
				match(T__6);

				        pcode.add(new Instruction.ROB(3,0));
				    
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 7);
				{
				setState(230);
				((FonctionContext)_localctx).ID = match(ID);

				            System.out.println("appel func");
				            Type.Fonction fonction = (Type.Fonction) symbtable.getDonnee((((FonctionContext)_localctx).ID!=null?((FonctionContext)_localctx).ID.getText():null)).getType();
				            symbtable.setLocal();
				            int i = 0;
				        
				setState(232);
				match(T__5);
				setState(235);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INT) {
					{
					setState(233);
					match(INT);

					                        //if(fonction.typesParam[i] == int.class){
					                            System.out.println("r de merde = "+fonction.nomsParam.get(i)+" "+fonction.typesParam.get(i));
					                            symbtable.putVar(fonction.nomsParam.get(i), fonction.typesParam.get(i));

					                            i++;
					                        //}else{
					                            //throw new IllegalArgumentException();
					                        //}

					                        
					}
				}

				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(237);
					match(T__2);
					setState(238);
					match(INT);

					                        //if(fonction.typesParam[i] == int.class){
					                            symbtable.putVar(fonction.nomsParam.get(i), fonction.typesParam.get(i));

					                            i++;
					                        //}else{
					                            //throw new IllegalArgumentException();
					                        //}
					                        
					}
					}
					setState(244);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(245);
				match(T__6);

				            //appel de fonction
				            System.out.println("function="+fonction);
				            pcode.add(new Instruction.CAL(vars.get((((FonctionContext)_localctx).ID!=null?((FonctionContext)_localctx).ID.getText():null)) + 2)); //les 2 lignes ajoutees par dessus
				            symbtable.setGlobal();
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ExpressionContext extends ParserRuleContext {
		public Type type;
		public Simple_expressionContext simple_expression;
		public List<Simple_expressionContext> simple_expression() {
			return getRuleContexts(Simple_expressionContext.class);
		}
		public Simple_expressionContext simple_expression(int i) {
			return getRuleContext(Simple_expressionContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expression);
		int _la;
		try {
			setState(309);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(249);
				((ExpressionContext)_localctx).simple_expression = simple_expression();
				setState(256);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__27) {
					{
					{
					setState(250);
					match(T__27);
					setState(251);
					((ExpressionContext)_localctx).simple_expression = simple_expression();
					pcode.add(new Instruction.EQL());((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).simple_expression.type;
					}
					}
					setState(258);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(259);
				((ExpressionContext)_localctx).simple_expression = simple_expression();
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__28) {
					{
					{
					setState(260);
					match(T__28);
					setState(261);
					((ExpressionContext)_localctx).simple_expression = simple_expression();
					pcode.add(new Instruction.NEQ());((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).simple_expression.type;
					}
					}
					setState(268);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(269);
				((ExpressionContext)_localctx).simple_expression = simple_expression();
				setState(276);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__29) {
					{
					{
					setState(270);
					match(T__29);
					setState(271);
					((ExpressionContext)_localctx).simple_expression = simple_expression();
					pcode.add(new Instruction.LSS());((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).simple_expression.type;
					}
					}
					setState(278);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(279);
				((ExpressionContext)_localctx).simple_expression = simple_expression();
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__30) {
					{
					{
					setState(280);
					match(T__30);
					setState(281);
					((ExpressionContext)_localctx).simple_expression = simple_expression();
					pcode.add(new Instruction.GTR());((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).simple_expression.type;
					}
					}
					setState(288);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(289);
				((ExpressionContext)_localctx).simple_expression = simple_expression();
				setState(296);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__31) {
					{
					{
					setState(290);
					match(T__31);
					setState(291);
					((ExpressionContext)_localctx).simple_expression = simple_expression();
					pcode.add(new Instruction.LEQ());((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).simple_expression.type;
					}
					}
					setState(298);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(299);
				((ExpressionContext)_localctx).simple_expression = simple_expression();
				setState(306);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__32) {
					{
					{
					setState(300);
					match(T__32);
					setState(301);
					((ExpressionContext)_localctx).simple_expression = simple_expression();
					pcode.add(new Instruction.GEQ());((ExpressionContext)_localctx).type =  ((ExpressionContext)_localctx).simple_expression.type;
					}
					}
					setState(308);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class FactorContext extends ParserRuleContext {
		public Type type;
		public Token INT;
		public ExpressionContext expression;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode INT() { return getToken(PascalParser.INT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_factor);
		try {
			setState(321);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(311);
				variable();

				        pcode.add(new Instruction.LDV());
				    
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(314);
				((FactorContext)_localctx).INT = match(INT);

				        ((FactorContext)_localctx).type =  new Type.Int();
				        pcode.add(new Instruction.LDI((((FactorContext)_localctx).INT!=null?Integer.valueOf(((FactorContext)_localctx).INT.getText()):0)));
				    
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(316);
				match(T__5);
				setState(317);
				((FactorContext)_localctx).expression = expression();

				        ((FactorContext)_localctx).type =  ((FactorContext)_localctx).expression.type;
				    
				setState(319);
				match(T__6);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class TermContext extends ParserRuleContext {
		public Type type;
		public FactorContext factor;
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_term);
		int _la;
		try {
			setState(343);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(323);
				((TermContext)_localctx).factor = factor();
				setState(330);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__33) {
					{
					{
					setState(324);
					match(T__33);
					setState(325);
					((TermContext)_localctx).factor = factor();
					((TermContext)_localctx).type =  ((TermContext)_localctx).factor.type;pcode.add(new Instruction.MUL());
					}
					}
					setState(332);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(333);
				((TermContext)_localctx).factor = factor();
				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__34) {
					{
					{
					setState(334);
					match(T__34);
					setState(335);
					((TermContext)_localctx).factor = factor();
					((TermContext)_localctx).type =  ((TermContext)_localctx).factor.type;pcode.add(new Instruction.DIV());
					}
					}
					setState(342);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class Simple_expressionContext extends ParserRuleContext {
		public Type type;
		public TermContext term;
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public Simple_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).enterSimple_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).exitSimple_expression(this);
		}
	}

	public final Simple_expressionContext simple_expression() throws RecognitionException {
		Simple_expressionContext _localctx = new Simple_expressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_simple_expression);
		int _la;
		try {
			setState(379);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				boolean negation=false;
				setState(349);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__35:
					{
					setState(346);
					match(T__35);
					}
					break;
				case T__36:
					{
					setState(347);
					match(T__36);
					negation = true;
					}
					break;
				case T__5:
				case ID:
				case INT:
					break;
				default:
					break;
				}
				setState(351);
				((Simple_expressionContext)_localctx).term = term();
				if(negation){pcode.add(new Instruction.LDI(-1));pcode.add(new Instruction.MUL());}
				setState(359);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__35) {
					{
					{
					setState(353);
					match(T__35);
					setState(354);
					((Simple_expressionContext)_localctx).term = term();
					((Simple_expressionContext)_localctx).type =  ((Simple_expressionContext)_localctx).term.type;pcode.add(new Instruction.ADD());
					}
					}
					setState(361);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				boolean negation=false;
				setState(366);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__35:
					{
					setState(363);
					match(T__35);
					}
					break;
				case T__36:
					{
					setState(364);
					match(T__36);
					negation = true;
					}
					break;
				case T__5:
				case ID:
				case INT:
					break;
				default:
					break;
				}
				setState(368);
				((Simple_expressionContext)_localctx).term = term();
				if(negation){pcode.add(new Instruction.LDI(-1));pcode.add(new Instruction.MUL());}
				setState(376);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__36) {
					{
					{
					setState(370);
					match(T__36);
					setState(371);
					((Simple_expressionContext)_localctx).term = term();
					((Simple_expressionContext)_localctx).type =  ((Simple_expressionContext)_localctx).term.type;pcode.add(new Instruction.SUB());
					}
					}
					setState(378);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class ProgramContext extends ParserRuleContext {
		public DeclarationsContext declarations() {
			return getRuleContext(DeclarationsContext.class,0);
		}
		public InstructionsContext instructions() {
			return getRuleContext(InstructionsContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PascalListener ) ((PascalListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			declarations();
			setState(382);
			match(T__7);
			setState(383);
			instructions();
			setState(384);
			match(T__8);
			setState(385);
			match(T__15);

			        pcode.add(new Instruction.HLT());
			        System.out.println(symbtable);
			    
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3*\u0187\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\7\2&\n\2\f\2\16\2)\13\2\3\2\3\2\3\3\3\3\5\3/\n\3\3\4\3\4\3\4\3\4\3"+
		"\4\7\4\66\n\4\f\4\16\49\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\5\5I\n\5\3\5\3\5\3\5\3\5\3\5\7\5P\n\5\f\5\16\5S\13\5\3"+
		"\5\3\5\3\5\3\5\3\5\7\5Z\n\5\f\5\16\5]\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6t\n\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u0080\n\7\f\7\16\7\u0083\13\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u008c\n\b\f\b\16\b\u008f\13\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\7\n\u009b\n\n\f\n\16\n\u009e\13\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\5\13\u00b1\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\5\13\u00c0\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\5\f\u00ee\n\f\3\f\3\f\3\f\7\f\u00f3\n\f\f\f\16\f\u00f6\13\f\3\f\3\f"+
		"\5\f\u00fa\n\f\3\r\3\r\3\r\3\r\3\r\7\r\u0101\n\r\f\r\16\r\u0104\13\r\3"+
		"\r\3\r\3\r\3\r\3\r\7\r\u010b\n\r\f\r\16\r\u010e\13\r\3\r\3\r\3\r\3\r\3"+
		"\r\7\r\u0115\n\r\f\r\16\r\u0118\13\r\3\r\3\r\3\r\3\r\3\r\7\r\u011f\n\r"+
		"\f\r\16\r\u0122\13\r\3\r\3\r\3\r\3\r\3\r\7\r\u0129\n\r\f\r\16\r\u012c"+
		"\13\r\3\r\3\r\3\r\3\r\3\r\7\r\u0133\n\r\f\r\16\r\u0136\13\r\5\r\u0138"+
		"\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0144\n\16"+
		"\3\17\3\17\3\17\3\17\3\17\7\17\u014b\n\17\f\17\16\17\u014e\13\17\3\17"+
		"\3\17\3\17\3\17\3\17\7\17\u0155\n\17\f\17\16\17\u0158\13\17\5\17\u015a"+
		"\n\17\3\20\3\20\3\20\3\20\5\20\u0160\n\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\7\20\u0168\n\20\f\20\16\20\u016b\13\20\3\20\3\20\3\20\3\20\5\20\u0171"+
		"\n\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0179\n\20\f\20\16\20\u017c\13"+
		"\20\5\20\u017e\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\2\2\22\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\2\2\u01a6\2\"\3\2\2\2\4.\3\2\2"+
		"\2\6\60\3\2\2\2\b@\3\2\2\2\ns\3\2\2\2\fu\3\2\2\2\16\u0084\3\2\2\2\20\u0092"+
		"\3\2\2\2\22\u009c\3\2\2\2\24\u00bf\3\2\2\2\26\u00f9\3\2\2\2\30\u0137\3"+
		"\2\2\2\32\u0143\3\2\2\2\34\u0159\3\2\2\2\36\u017d\3\2\2\2 \u017f\3\2\2"+
		"\2\"\'\5\4\3\2#$\7\3\2\2$&\5\4\3\2%#\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3"+
		"\2\2\2(*\3\2\2\2)\'\3\2\2\2*+\b\2\1\2+\3\3\2\2\2,/\5\6\4\2-/\5\b\5\2."+
		",\3\2\2\2.-\3\2\2\2/\5\3\2\2\2\60\61\b\4\1\2\61\67\7\4\2\2\62\63\7(\2"+
		"\2\63\64\b\4\1\2\64\66\7\5\2\2\65\62\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2"+
		"\678\3\2\2\28:\3\2\2\29\67\3\2\2\2:;\7(\2\2;<\b\4\1\2<=\7\6\2\2=>\5\n"+
		"\6\2>?\b\4\1\2?\7\3\2\2\2@A\7\7\2\2AB\7(\2\2BC\b\5\1\2CH\7\b\2\2DE\5\n"+
		"\6\2EF\7(\2\2FG\b\5\1\2GI\3\2\2\2HD\3\2\2\2HI\3\2\2\2IQ\3\2\2\2JK\7\5"+
		"\2\2KL\5\n\6\2LM\7(\2\2MN\b\5\1\2NP\3\2\2\2OJ\3\2\2\2PS\3\2\2\2QO\3\2"+
		"\2\2QR\3\2\2\2RT\3\2\2\2SQ\3\2\2\2TU\7\t\2\2UV\7\6\2\2VW\5\n\6\2W[\7\3"+
		"\2\2XZ\5\2\2\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\^\3\2\2\2][\3"+
		"\2\2\2^_\7\n\2\2_`\5\22\n\2`a\7\13\2\2ab\7\3\2\2bc\b\5\1\2c\t\3\2\2\2"+
		"de\7\f\2\2et\b\6\1\2fg\7\r\2\2gh\7\16\2\2hi\7)\2\2ij\7\17\2\2jk\7\20\2"+
		"\2kl\5\n\6\2lm\b\6\1\2mt\3\2\2\2no\7\21\2\2op\5\16\b\2pq\7\13\2\2qr\b"+
		"\6\1\2rt\3\2\2\2sd\3\2\2\2sf\3\2\2\2sn\3\2\2\2t\13\3\2\2\2uv\7(\2\2v\u0081"+
		"\b\7\1\2wx\7\16\2\2xy\5\30\r\2yz\7\17\2\2z{\b\7\1\2{\u0080\3\2\2\2|}\7"+
		"\22\2\2}~\7(\2\2~\u0080\b\7\1\2\177w\3\2\2\2\177|\3\2\2\2\u0080\u0083"+
		"\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\r\3\2\2\2\u0083\u0081"+
		"\3\2\2\2\u0084\u0085\b\b\1\2\u0085\u0086\5\20\t\2\u0086\u008d\b\b\1\2"+
		"\u0087\u0088\7\3\2\2\u0088\u0089\5\20\t\2\u0089\u008a\b\b\1\2\u008a\u008c"+
		"\3\2\2\2\u008b\u0087\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d"+
		"\u008e\3\2\2\2\u008e\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091\b\b"+
		"\1\2\u0091\17\3\2\2\2\u0092\u0093\7(\2\2\u0093\u0094\7\6\2\2\u0094\u0095"+
		"\5\n\6\2\u0095\u0096\b\t\1\2\u0096\21\3\2\2\2\u0097\u0098\5\24\13\2\u0098"+
		"\u0099\7\3\2\2\u0099\u009b\3\2\2\2\u009a\u0097\3\2\2\2\u009b\u009e\3\2"+
		"\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009f\3\2\2\2\u009e"+
		"\u009c\3\2\2\2\u009f\u00a0\5\24\13\2\u00a0\23\3\2\2\2\u00a1\u00a2\5\f"+
		"\7\2\u00a2\u00a3\7\23\2\2\u00a3\u00a4\5\30\r\2\u00a4\u00a5\b\13\1\2\u00a5"+
		"\u00c0\3\2\2\2\u00a6\u00a7\7\24\2\2\u00a7\u00a8\5\30\r\2\u00a8\u00a9\b"+
		"\13\1\2\u00a9\u00aa\7\25\2\2\u00aa\u00ab\5\24\13\2\u00ab\u00b0\b\13\1"+
		"\2\u00ac\u00ad\7\26\2\2\u00ad\u00ae\5\24\13\2\u00ae\u00af\b\13\1\2\u00af"+
		"\u00b1\3\2\2\2\u00b0\u00ac\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00c0\3\2"+
		"\2\2\u00b2\u00b3\7\27\2\2\u00b3\u00b4\b\13\1\2\u00b4\u00b5\5\30\r\2\u00b5"+
		"\u00b6\b\13\1\2\u00b6\u00b7\7\30\2\2\u00b7\u00b8\5\24\13\2\u00b8\u00b9"+
		"\b\13\1\2\u00b9\u00c0\3\2\2\2\u00ba\u00c0\5\26\f\2\u00bb\u00bc\7\n\2\2"+
		"\u00bc\u00bd\5\22\n\2\u00bd\u00be\7\13\2\2\u00be\u00c0\3\2\2\2\u00bf\u00a1"+
		"\3\2\2\2\u00bf\u00a6\3\2\2\2\u00bf\u00b2\3\2\2\2\u00bf\u00ba\3\2\2\2\u00bf"+
		"\u00bb\3\2\2\2\u00c0\25\3\2\2\2\u00c1\u00fa\3\2\2\2\u00c2\u00c3\7\31\2"+
		"\2\u00c3\u00c4\7\b\2\2\u00c4\u00c5\5\f\7\2\u00c5\u00c6\7\t\2\2\u00c6\u00c7"+
		"\b\f\1\2\u00c7\u00fa\3\2\2\2\u00c8\u00c9\7\32\2\2\u00c9\u00ca\7\b\2\2"+
		"\u00ca\u00cb\5\30\r\2\u00cb\u00cc\7\t\2\2\u00cc\u00cd\b\f\1\2\u00cd\u00fa"+
		"\3\2\2\2\u00ce\u00cf\7\33\2\2\u00cf\u00d0\b\f\1\2\u00d0\u00d1\7\b\2\2"+
		"\u00d1\u00d2\5\30\r\2\u00d2\u00d3\7\5\2\2\u00d3\u00d4\5\30\r\2\u00d4\u00d5"+
		"\7\5\2\2\u00d5\u00d6\5\30\r\2\u00d6\u00d7\7\t\2\2\u00d7\u00d8\b\f\1\2"+
		"\u00d8\u00fa\3\2\2\2\u00d9\u00da\7\34\2\2\u00da\u00db\7\b\2\2\u00db\u00dc"+
		"\5\30\r\2\u00dc\u00dd\7\t\2\2\u00dd\u00de\b\f\1\2\u00de\u00fa\3\2\2\2"+
		"\u00df\u00e0\7\35\2\2\u00e0\u00e1\b\f\1\2\u00e1\u00e2\7\b\2\2\u00e2\u00e3"+
		"\5\30\r\2\u00e3\u00e4\7\5\2\2\u00e4\u00e5\5\30\r\2\u00e5\u00e6\7\t\2\2"+
		"\u00e6\u00e7\b\f\1\2\u00e7\u00fa\3\2\2\2\u00e8\u00e9\7(\2\2\u00e9\u00ea"+
		"\b\f\1\2\u00ea\u00ed\7\b\2\2\u00eb\u00ec\7)\2\2\u00ec\u00ee\b\f\1\2\u00ed"+
		"\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f4\3\2\2\2\u00ef\u00f0\7\5"+
		"\2\2\u00f0\u00f1\7)\2\2\u00f1\u00f3\b\f\1\2\u00f2\u00ef\3\2\2\2\u00f3"+
		"\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f7\3\2"+
		"\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00f8\7\t\2\2\u00f8\u00fa\b\f\1\2\u00f9"+
		"\u00c1\3\2\2\2\u00f9\u00c2\3\2\2\2\u00f9\u00c8\3\2\2\2\u00f9\u00ce\3\2"+
		"\2\2\u00f9\u00d9\3\2\2\2\u00f9\u00df\3\2\2\2\u00f9\u00e8\3\2\2\2\u00fa"+
		"\27\3\2\2\2\u00fb\u0102\5\36\20\2\u00fc\u00fd\7\36\2\2\u00fd\u00fe\5\36"+
		"\20\2\u00fe\u00ff\b\r\1\2\u00ff\u0101\3\2\2\2\u0100\u00fc\3\2\2\2\u0101"+
		"\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0138\3\2"+
		"\2\2\u0104\u0102\3\2\2\2\u0105\u010c\5\36\20\2\u0106\u0107\7\37\2\2\u0107"+
		"\u0108\5\36\20\2\u0108\u0109\b\r\1\2\u0109\u010b\3\2\2\2\u010a\u0106\3"+
		"\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\u0138\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0116\5\36\20\2\u0110\u0111\7"+
		" \2\2\u0111\u0112\5\36\20\2\u0112\u0113\b\r\1\2\u0113\u0115\3\2\2\2\u0114"+
		"\u0110\3\2\2\2\u0115\u0118\3\2\2\2\u0116\u0114\3\2\2\2\u0116\u0117\3\2"+
		"\2\2\u0117\u0138\3\2\2\2\u0118\u0116\3\2\2\2\u0119\u0120\5\36\20\2\u011a"+
		"\u011b\7!\2\2\u011b\u011c\5\36\20\2\u011c\u011d\b\r\1\2\u011d\u011f\3"+
		"\2\2\2\u011e\u011a\3\2\2\2\u011f\u0122\3\2\2\2\u0120\u011e\3\2\2\2\u0120"+
		"\u0121\3\2\2\2\u0121\u0138\3\2\2\2\u0122\u0120\3\2\2\2\u0123\u012a\5\36"+
		"\20\2\u0124\u0125\7\"\2\2\u0125\u0126\5\36\20\2\u0126\u0127\b\r\1\2\u0127"+
		"\u0129\3\2\2\2\u0128\u0124\3\2\2\2\u0129\u012c\3\2\2\2\u012a\u0128\3\2"+
		"\2\2\u012a\u012b\3\2\2\2\u012b\u0138\3\2\2\2\u012c\u012a\3\2\2\2\u012d"+
		"\u0134\5\36\20\2\u012e\u012f\7#\2\2\u012f\u0130\5\36\20\2\u0130\u0131"+
		"\b\r\1\2\u0131\u0133\3\2\2\2\u0132\u012e\3\2\2\2\u0133\u0136\3\2\2\2\u0134"+
		"\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0138\3\2\2\2\u0136\u0134\3\2"+
		"\2\2\u0137\u00fb\3\2\2\2\u0137\u0105\3\2\2\2\u0137\u010f\3\2\2\2\u0137"+
		"\u0119\3\2\2\2\u0137\u0123\3\2\2\2\u0137\u012d\3\2\2\2\u0138\31\3\2\2"+
		"\2\u0139\u013a\5\f\7\2\u013a\u013b\b\16\1\2\u013b\u0144\3\2\2\2\u013c"+
		"\u013d\7)\2\2\u013d\u0144\b\16\1\2\u013e\u013f\7\b\2\2\u013f\u0140\5\30"+
		"\r\2\u0140\u0141\b\16\1\2\u0141\u0142\7\t\2\2\u0142\u0144\3\2\2\2\u0143"+
		"\u0139\3\2\2\2\u0143\u013c\3\2\2\2\u0143\u013e\3\2\2\2\u0144\33\3\2\2"+
		"\2\u0145\u014c\5\32\16\2\u0146\u0147\7$\2\2\u0147\u0148\5\32\16\2\u0148"+
		"\u0149\b\17\1\2\u0149\u014b\3\2\2\2\u014a\u0146\3\2\2\2\u014b\u014e\3"+
		"\2\2\2\u014c\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u015a\3\2\2\2\u014e"+
		"\u014c\3\2\2\2\u014f\u0156\5\32\16\2\u0150\u0151\7%\2\2\u0151\u0152\5"+
		"\32\16\2\u0152\u0153\b\17\1\2\u0153\u0155\3\2\2\2\u0154\u0150\3\2\2\2"+
		"\u0155\u0158\3\2\2\2\u0156\u0154\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u015a"+
		"\3\2\2\2\u0158\u0156\3\2\2\2\u0159\u0145\3\2\2\2\u0159\u014f\3\2\2\2\u015a"+
		"\35\3\2\2\2\u015b\u015f\b\20\1\2\u015c\u0160\7&\2\2\u015d\u015e\7\'\2"+
		"\2\u015e\u0160\b\20\1\2\u015f\u015c\3\2\2\2\u015f\u015d\3\2\2\2\u015f"+
		"\u0160\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u0162\5\34\17\2\u0162\u0169\b"+
		"\20\1\2\u0163\u0164\7&\2\2\u0164\u0165\5\34\17\2\u0165\u0166\b\20\1\2"+
		"\u0166\u0168\3\2\2\2\u0167\u0163\3\2\2\2\u0168\u016b\3\2\2\2\u0169\u0167"+
		"\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u017e\3\2\2\2\u016b\u0169\3\2\2\2\u016c"+
		"\u0170\b\20\1\2\u016d\u0171\7&\2\2\u016e\u016f\7\'\2\2\u016f\u0171\b\20"+
		"\1\2\u0170\u016d\3\2\2\2\u0170\u016e\3\2\2\2\u0170\u0171\3\2\2\2\u0171"+
		"\u0172\3\2\2\2\u0172\u0173\5\34\17\2\u0173\u017a\b\20\1\2\u0174\u0175"+
		"\7\'\2\2\u0175\u0176\5\34\17\2\u0176\u0177\b\20\1\2\u0177\u0179\3\2\2"+
		"\2\u0178\u0174\3\2\2\2\u0179\u017c\3\2\2\2\u017a\u0178\3\2\2\2\u017a\u017b"+
		"\3\2\2\2\u017b\u017e\3\2\2\2\u017c\u017a\3\2\2\2\u017d\u015b\3\2\2\2\u017d"+
		"\u016c\3\2\2\2\u017e\37\3\2\2\2\u017f\u0180\5\2\2\2\u0180\u0181\7\n\2"+
		"\2\u0181\u0182\5\22\n\2\u0182\u0183\7\13\2\2\u0183\u0184\7\22\2\2\u0184"+
		"\u0185\b\21\1\2\u0185!\3\2\2\2\"\'.\67HQ[s\177\u0081\u008d\u009c\u00b0"+
		"\u00bf\u00ed\u00f4\u00f9\u0102\u010c\u0116\u0120\u012a\u0134\u0137\u0143"+
		"\u014c\u0156\u0159\u015f\u0169\u0170\u017a\u017d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}