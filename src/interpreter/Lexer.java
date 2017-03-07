package interpreter;

import nodes.MainNode;

public class Lexer {
  private Parser parser;
  private MainNode mainNode;

  public Lexer(String inputFile) {
    parser = new Parser();
    parser.parse(inputFile);
  }
  
  public void generateAST() {
    mainNode = new MainNode(null, parser.getTokens());
  }

  public MainNode getMainNode() {
    return mainNode;
  }
}
