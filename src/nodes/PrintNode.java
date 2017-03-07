package nodes;

import java.util.LinkedList;

import interpreter.Pair;
import visitors.NodeVisitor;

public class PrintNode implements ASTNode {
  private ASTNode toBePrinted;

  public PrintNode(NodeWithChildren parent,
      LinkedList<Pair<String, String>> tokens) {
    parse(tokens);
  }

  public ASTNode getToBePrinted() {
    return toBePrinted;
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public void parse(LinkedList<Pair<String, String>> tokens) {
    String value = tokens.removeFirst().getValue();
    // determine which kind if node is toBePrinted
    if (value.charAt(0) == '\"') {
      toBePrinted = new StringNode(value);
    } else if (value.charAt(0) >= '0' && value.charAt(0) <= '9') {
      toBePrinted = new ConstantNode(Integer.parseInt(value));
    } else {
      toBePrinted = new VariableNode(value);
    }
  }

}
