package nodes;

import java.util.LinkedList;

import interpreter.Pair;
import visitors.NodeVisitor;

public class ConstantNode implements ASTNode, PrintableNode {
  private int value;

  public ConstantNode(int v) {
    value = v;
  }

  public int getValue() {
    return value;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);

  }

  @Override
  public String print() {
    return Integer.toString(value);
  }

  @Override
  public void parse(LinkedList<Pair<String, String>> tokens) {
    
  }

}
