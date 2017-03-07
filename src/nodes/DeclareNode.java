package nodes;

import java.util.LinkedList;

import interpreter.Pair;
import visitors.NodeVisitor;

public class DeclareNode implements ASTNode {
  private LvalNode lval;
  private ConstantNode constant;
  private NodeWithChildren parent;

  public DeclareNode(NodeWithChildren parent, 
      LinkedList<Pair<String, String>> tokens) {
    this.parent = parent;
    parse(tokens);
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);

  }
  
  public LvalNode getLval() {
    return lval;
  }
  
  public ConstantNode getConstant() {
    return constant;
  }
  
  public NodeWithChildren getParent() {
    return parent;
  }

  @Override
  public void parse(LinkedList<Pair<String, String>> tokens) {
    String varName = tokens.getFirst().getValue();
    tokens.removeFirst();
    int value =  Integer.parseInt(tokens.removeFirst().getValue());
    lval = new LvalNode(varName);
    constant = new ConstantNode(value);
  }
  
}
