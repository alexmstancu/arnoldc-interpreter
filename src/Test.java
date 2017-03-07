
import java.io.IOException;

import interpreter.Lexer;
import visitors.InterpretNodeVisitor;
import visitors.PrintNodeVisitor;

public class Test {

  public static void main(String[] args) {
    String inputFile = args[0];
    Lexer lexer = new Lexer(inputFile);
    lexer.generateAST();
    try {
      PrintNodeVisitor printVisitor = new PrintNodeVisitor("out.ast");
      lexer.getMainNode().accept(printVisitor);
      
      InterpretNodeVisitor interpretVisitor = new InterpretNodeVisitor("out.txt");
      lexer.getMainNode().accept(interpretVisitor);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
