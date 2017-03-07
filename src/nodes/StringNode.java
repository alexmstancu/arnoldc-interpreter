package nodes;

import java.util.LinkedList;

import interpreter.Pair;
import visitors.NodeVisitor;

public class StringNode implements ASTNode, PrintableNode {
  private String content;

  public StringNode(String c) {
    content = c;
  }

  public String getContent() {
    return content;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public String print() {
    return content.substring(1, content.length() - 1);
  }

  @Override
  public void parse(LinkedList<Pair<String, String>> tokens) {
    
  }
}
