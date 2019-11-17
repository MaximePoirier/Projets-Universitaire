grammar Pascal;

@header {
import java.util.*;
}

@parser::members {
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
}

declarations:
    declaration (';' declaration)*
    {
            pcode.add(0, new Instruction.INC(symbtable.getSize()));
    };

declaration : declavar | declafunc;

declavar:
    {ArrayList<String> ids = new ArrayList<String>();}
    'var' (ID
    {
        ids.add($ID.text);
    } ',')* ID
        {
            ids.add($ID.text);
        } ':' typi
            {
                symbtable.putListVar(ids,$typi.type);
            };

declafunc:
    'function' idFunc = ID
    {
            System.out.println("--declafunc--");
            //definition de fonction
            vars.put($idFunc.text, pcode.size()); //memorise la ligne de définion de la fonction
            pcode.add(new Instruction.BRN(-4)); //instruction factice a changer
            //instanciation des variables
            ArrayList<Type> types = new ArrayList<Type>();
            ArrayList<String> noms = new ArrayList<String>();
            symbtable.setLocal(); //pour memoriser les instructions en local
        }

        '(' (t1 = typi i1 = ID {types.add($t1.type);noms.add($i1.text);symbtable.putVar($i1.text, $t1.type);})?
        (',' t2 = typi i2 = ID {types.add($t2.type);noms.add($i2.text);symbtable.putVar($i2.text, $t2.type);})*
        ')' ':' t3 = typi ';'

            declarations* 'begin' instructions 'end' ';'
            {
                symbtable.setGlobal(); //retour en global

                Type.Fonction f = new Type.Fonction($t3.type, types, noms);
                System.out.println("declafunc : f="+f);
                symbtable.putVar($idFunc.text, f); //memorisation de la fonction dans table des symboles

                System.out.println("declafunc : RET");
                pcode.add(new Instruction.RET(types.size()));
                int sizeFunc = pcode.size() - vars.get($idFunc.text);
                pcode.set(vars.get($idFunc.text), new Instruction.BRN(sizeFunc + 1)); //saut de la fonction
                System.out.println("fin declafunc");
            };


typi returns [Type type]: 'integer'{$type = new Type.Int();}
                        | 'array' '['INT']' 'of' typi {$type = new Type.Tab($INT.int,$typi.type);}
                        | 'record' fields 'end' {$type = new Type.Record($fields.ids,$fields.types);};

variable returns[Type type] :
    ID
    {
        System.out.println("--variable--");
        Donnee d = symbtable.getDonnee($ID.text);
        System.out.println("ID.text = "+$ID.text+" " +"d="+d);
        Type restype =d.getType();
        $type =restype;

        System.out.println("variable debutif");
        if(symbtable.estGlobal){
            //LDA dans le cas global
            pcode.add(new Instruction.LDA(symbtable.getAdresse($ID.text)));
        }else{
            //LDL dans le cas local
            pcode.add(new Instruction.LDL(symbtable.getAdresse($ID.text)));
        }
        System.out.println("--fin variable--");
    } ('[' expression ']' {
                             restype = ((Type.Tab) restype).type;
                             $type = restype;
                             pcode.add(new Instruction.LDI(((Type.Tab) d.type).getType().size()));
                             pcode.add(new Instruction.MUL());
                             pcode.add(new Instruction.ADD());
                           }
    | '.' ID {
        restype = (Type.Record) restype;
        pcode.add(new Instruction.LDI(((Type.Record) restype).adresse($ID.text)));
        pcode.add(new Instruction.ADD());
        restype = ((Type.Record) restype).type($ID.text);
        $type = restype;
    })*;

/*parameter returns[Type type]:
    typi ID
    {
        //recup dans le tableau global
        Donnee d = symbtable.getDonnee($ID.text);

    };*/

fields returns [ArrayList<String> ids, ArrayList<Type> types]:{ArrayList<String> ids = new ArrayList<String>();ArrayList<Type> types = new ArrayList<Type>();}
                                            field {ids.add($field.id);types.add($field.type);}
                                            (';' field {ids.add($field.id);types.add($field.type);})*{$ids = ids;$types = types;};

field returns [String id, Type type]: ID ':' typi {$id = $ID.text;$type = $typi.type;};

instructions : (instruction ';')* instruction ;

instruction :
    variable ':=' expression
    {
        pcode.add(new Instruction.STO());
    }
    |'if' expression
    {
        Instruction.BZE instBZE = new Instruction.BZE(0);pcode.add(instBZE);
    }   'then' instruction
        {
            instBZE.setParam(pcode.size()+1);
            Instruction.BRN instBRN = new Instruction.BRN(pcode.size()+1);
            pcode.add(instBRN);
        }   ('else' instruction {
                instBRN.setParam(pcode.size());
            })?
    |'while'
    {
        int inst = pcode.size();
    } expression {
            Instruction.BZE instBZE2 = new Instruction.BZE(0);
            pcode.add(instBZE2);
        }   'do' instruction {
                instBZE2.setParam(pcode.size()+1);
                pcode.add(new Instruction.BRN(inst));
            }
    | fonction
    |'begin' instructions 'end';


fonction :
    |'read' '(' variable ')'
    {
        pcode.add(new Instruction.INN());
        pcode.add(new Instruction.STO());
    }
    |'write' '(' expression ')'
    {
        System.out.println("--write--");
        pcode.add(new Instruction.PRN());
    }
    | 'colorLed'
     {
     pcode.add(new Instruction.LDI(3)); //fonction a appeler
     }'(' expression ',' expression ',' expression ')'
    {
        //changement de couleur
        pcode.add(new Instruction.ROB(4,0));
    }
    | 'sleepLed' '(' expression ')'
    {
        //dormir
        pcode.add(new Instruction.SLE());
    }
    | 'switchLightZumo'{pcode.add(new Instruction.LDI(0));} '(' expression ',' expression')'
    {
        pcode.add(new Instruction.ROB(3,0));
    }
    | ID
        {
            System.out.println("appel func");
            Type.Fonction fonction = (Type.Fonction) symbtable.getDonnee($ID.text).getType();
            symbtable.setLocal();
            int i = 0;
        }
        '(' ( INT {
                        //if(fonction.typesParam[i] == int.class){
                            System.out.println("r de merde = "+fonction.nomsParam.get(i)+" "+fonction.typesParam.get(i));
                            symbtable.putVar(fonction.nomsParam.get(i), fonction.typesParam.get(i));

                            i++;
                        //}else{
                            //throw new IllegalArgumentException();
                        //}

                        })?
        (',' INT {
                        //if(fonction.typesParam[i] == int.class){
                            symbtable.putVar(fonction.nomsParam.get(i), fonction.typesParam.get(i));

                            i++;
                        //}else{
                            //throw new IllegalArgumentException();
                        //}
                        })*
        ')'
        {
            //appel de fonction
            System.out.println("function="+fonction);
            pcode.add(new Instruction.CAL(vars.get($ID.text) + 2)); //les 2 lignes ajoutees par dessus
            symbtable.setGlobal();
        }
    ;


expression returns [Type type]: simple_expression ('=' simple_expression {pcode.add(new Instruction.EQL());$type = $simple_expression.type;})*
    |simple_expression ('<>' simple_expression{pcode.add(new Instruction.NEQ());$type = $simple_expression.type;})*
    |simple_expression ('<' simple_expression {pcode.add(new Instruction.LSS());$type = $simple_expression.type;})*
    |simple_expression ('>' simple_expression {pcode.add(new Instruction.GTR());$type = $simple_expression.type;})*
    |simple_expression ('<=' simple_expression {pcode.add(new Instruction.LEQ());$type = $simple_expression.type;})*
    |simple_expression ('>=' simple_expression {pcode.add(new Instruction.GEQ());$type = $simple_expression.type;})*;

factor returns [Type type]:
    variable
    {
        pcode.add(new Instruction.LDV());
    }
    | INT
    {
        $type = new Type.Int();
        pcode.add(new Instruction.LDI($INT.int));
    }
    | '(' expression
    {
        $type = $expression.type;
    }')';

term returns [Type type]:
    factor ('*' factor {$type = $factor.type;pcode.add(new Instruction.MUL());})*
    | factor ('/' factor {$type = $factor.type;pcode.add(new Instruction.DIV());})*;


simple_expression returns [Type type]:
    {boolean negation=false;} ('+'|'-' {negation = true;})?
    term {if(negation){pcode.add(new Instruction.LDI(-1));pcode.add(new Instruction.MUL());}}
    ('+' term {$type = $term.type;pcode.add(new Instruction.ADD());})*
    |
    {boolean negation=false;} ('+'|'-' {negation = true;})?
    term {if(negation){pcode.add(new Instruction.LDI(-1));pcode.add(new Instruction.MUL());}}
    ('-' term {$type = $term.type;pcode.add(new Instruction.SUB());})* ;

program :
    declarations

    'begin' instructions 'end' '.'
    {
        pcode.add(new Instruction.HLT());
        System.out.println(symbtable);
    }
;


ID :[a-zA-Z] ([a-zA-Z] | [0-9])*;
INT : ('-')?[0-9]+;
WS : [ \r\t\n]+ -> skip;