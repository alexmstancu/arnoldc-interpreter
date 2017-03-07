package nodes;

import java.util.LinkedList;

import interpreter.Pair;
import visitors.NodeVisitor;

public class AndNode extends OperationNode {
  public AndNode(NodeWithChildren p, LinkedList<Pair<String, String>> tokens,
      ASTNode left) {
    super(p, tokens, left);
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }
}