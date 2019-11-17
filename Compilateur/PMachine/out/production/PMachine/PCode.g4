grammar PCode;

@header {
import java.util.*;
}

@parser::members {
    private ArrayList<Instruction> pcode;

    public  Instruction[] lire() throws Exception {
        pcode = new ArrayList<Instruction>();
        program();
        return pcode.toArray(new Instruction[]{});
    }
}

program : ((INT)? instruction)*;

instruction : 'ADD' {pcode.add(new Instruction.ADD());} |
    'SUB' {pcode.add(new Instruction.SUB());} |
    'MUL' {pcode.add(new Instruction.MUL());} |
    'DIV' {pcode.add(new Instruction.DIV());} |
    'EQL' {pcode.add(new Instruction.EQL());} |
    'NEQ' {pcode.add(new Instruction.NEQ());}|
    'GTR' {pcode.add(new Instruction.GTR());}|
    'LSS' {pcode.add(new Instruction.LSS());}|
    'GEQ' {pcode.add(new Instruction.GEQ());}|
    'LEQ' {pcode.add(new Instruction.LEQ());}|
    'PRN' {pcode.add(new Instruction.PRN());}|
    'INN' {pcode.add(new Instruction.INN());}|
    'INC' '-' INT {pcode.add(new Instruction.INC(-$INT.int));} |
    'INC' INT {pcode.add(new Instruction.INC($INT.int));} |
    'LDI' '-' INT {pcode.add(new Instruction.LDI(-$INT.int));}|
    'LDI' INT {pcode.add(new Instruction.LDI($INT.int));}|
    'LDA' INT {pcode.add(new Instruction.LDA($INT.int));}|
    'LDV' {pcode.add(new Instruction.LDV());} |
    'STO' {pcode.add(new Instruction.STO());} |
    'BRN' INT {pcode.add(new Instruction.BRN($INT.int));}|
    'BZE' INT {pcode.add(new Instruction.BZE($INT.int));}|
    'HLT' {pcode.add(new Instruction.HLT());}|
    'SLE' {pcode.add(new Instruction.SLE());} |
    'ROB' x=INT y=INT {pcode.add(new Instruction.ROB($x.int,$y.int));}
    ;

INT:[0-9]+;
WS : [ \r\t\n]+ -> skip;