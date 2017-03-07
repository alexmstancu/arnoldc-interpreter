package nodes;

import java.util.LinkedList;

import interpreter.Pair;
import visitors.NodeVisitor;

public class OperationNode extends NodeWithChildren implements ASTNode {
  private ASTNode right;
  private ASTNode left;

  public OperationNode(NodeWithChildren p, 
      LinkedList<Pair<String, String>> tokens,
      ASTNode left) {
    super(p);
    parse(tokens);
    this.left = left;
  }

  @Override
  public void accept(NodeVisitor visitor) {}

  @Override
  public void parse(LinkedList<Pair<String, String>> tokens) {
    Pair<String, String> token = tokens.removeFirst();
    String value = token.getValue();
    // determine which kind of node is right node
    if(value.charAt(0) >= '0' && value.charAt(0) <= '9') {
      right = new ConstantNode(Integer.parseInt(value));
    } else {
      right = new RvalNode(value);
    }
  }
  
  public ASTNode getRight() { return right; }
  public ASTNode getLeft() { return left; }
}
