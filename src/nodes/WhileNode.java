package nodes;

import java.util.LinkedList;

import interpreter.Pair;
import visitors.NodeVisitor;

public class WhileNode extends NodeWithChildren implements ASTNode {
  private ConditionNode condition;
  private BodyNode body;
  
  public WhileNode(NodeWithChildren parent, 
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
    // create components of while and let them parse next tokens
    condition = new ConditionNode(tokens);
    body = new BodyNode(this, tokens);
    tokens.removeFirst();
  }
  
  public BodyNode getBody() {
    return body;
  }

  public ConditionNode getCondition() {
    return condition;
  }
}
