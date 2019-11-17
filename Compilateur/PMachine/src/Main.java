import org.antlr.v4.runtime.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String file = args.length==0 ? "toto.pas" : args[0];

        CharStream input = CharStreams.fromFileName(file);
        PascalLexer lexer = new PascalLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PascalParser parser = new PascalParser(tokens);
        PMachine pMachine = new PMachine(parser.lire());
        System.out.println(pMachine);
        pMachine.exec();
    }




    public static void main2(String[] args) throws Exception {
        String file = args.length==0 ? "toto.asm" : args[0];

        CharStream input = CharStreams.fromFileName(file);
        PCodeLexer lexer = new PCodeLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PCodeParser parser = new PCodeParser(tokens);
        PMachine pMachine = new PMachine(parser.lire());
        System.out.println(pMachine);
        pMachine.exec();
    }

}
