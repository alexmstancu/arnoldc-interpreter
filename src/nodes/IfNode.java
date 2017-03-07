package nodes;

import java.util.LinkedList;

import interpreter.Construction;
import interpreter.Pair;
import visitors.NodeVisitor;

public class IfNode extends NodeWithChildren implements ASTNode {
  private ConditionNode condition;
  private IfBodyNode ifBody;
  private ElseBodyNode elseBody;
  
  public IfNode(NodeWithChildren parent, 
      LinkedList<Pair<String, String>> tokens) {
    super(parent);
    parse(tokens);
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public void parse(LinkedList<Pair<String, String>> tokens) {
    // initialize components of if and let them parse their childs
    condition = new ConditionNode(tokens);
    ifBody = new IfBodyNode(this, tokens);
    if (tokens.getFirst().getKey().equals(Construction.Else.name()))
      elseBody = new ElseBodyNode(this, tokens);
    tokens.removeFirst();
  }
  
  public ConditionNode getCondition() {
    return condition;
  }
  
  public ElseBodyNode getElseBody() {
    return elseBody;
  }
  
  public IfBodyNode getIfBody() {
    return ifBody;
  }
}
