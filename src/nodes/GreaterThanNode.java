package nodes;

import java.util.LinkedList;

import interpreter.Pair;
import visitors.NodeVisitor;

public class GreaterThanNode extends OperationNode {
  public GreaterThanNode(NodeWithChildren p, 
      LinkedList<Pair<String, String>> tokens,
      ASTNode left) {
    super(p, tokens, left);
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }
}
