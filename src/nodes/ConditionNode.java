package nodes;

import java.util.LinkedList;

import interpreter.Pair;
import visitors.NodeVisitor;

public class ConditionNode implements ASTNode, PrintableNode {
  private String varName;
  
  public ConditionNode(LinkedList<Pair<String, String>> tokens) {
    parse(tokens);
  }
  
  public String getName() {
    return varName;
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public void parse(LinkedList<Pair<String, String>> tokens) {
    varName = tokens.getFirst().getValue();
    tokens.removeFirst();
  }

  @Override
  public String print() {
    return varName;
  }
}
