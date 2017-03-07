package nodes;

import java.util.LinkedList;

import interpreter.Construction;
import interpreter.Pair;
import visitors.NodeVisitor;

public class AssignmentNode extends NodeWithChildren implements ASTNode {
  private LvalNode var;
  private ASTNode val;
  
  public AssignmentNode(NodeWithChildren parent, 
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
    var = new LvalNode(tokens.removeFirst().getValue());
    Pair<String, String> token = tokens.removeFirst();
    String value =  token.getValue();
    if(value.charAt(0) >= '0' && value.charAt(0) <= '9') {
      val = new ConstantNode(Integer.parseInt(value));
    } else {
      val = new RvalNode(value);
    }
    
    Pair<String, String> top = tokens.getFirst();
    while (!top.getKey().equals(Construction.EndAssignVariable.name())) {
      val = getNode(this, tokens, val);
      top = tokens.getFirst();
    }
    tokens.removeFirst();
  }
  
  private ASTNode getNode(NodeWithChildren parent, 
      LinkedList<Pair<String, String>> tokens, ASTNode prev) {
    
    if (tokens.getFirst().getKey().equals(Construction.Or.name())) {
      return new OrNode(parent, tokens, prev);
    }
    if (tokens.getFirst().getKey().equals(Construction.And.name())) {
      return new AndNode(parent, tokens, prev);
    }
    if (tokens.getFirst().getKey().equals(Construction.DivisionOperator.name())) {
      return new DivisionNode(parent, tokens, prev);
    }
    if (tokens.getFirst().getKey().equals(Construction.PlusOperator.name())) {
      return new SumNode(parent, tokens, prev);
    }
    if (tokens.getFirst().getKey().equals(Construction.MinusOperator.name())) {
      return new DifferenceNode(parent, tokens, prev);
    }
    if (tokens.getFirst().getKey().equals(Construction.MultiplicationOperator.
        name())) {
      return new MultiplicationNode(parent, tokens, prev);
    }
    if (tokens.getFirst().getKey().equals(Construction.ModuloOperator.name())) {
      return new ModuloNode(parent, tokens, prev);
    }
    if (tokens.getFirst().getKey().equals(Construction.EqualTo.name())) {
      return new EqualToNode(parent, tokens, prev);
    }
    if (tokens.getFirst().getKey().equals(Construction.GreaterThan.name())) {
      return new GreaterThanNode(parent, tokens, prev);
    }
    return null;
  }
  
  public LvalNode getVar() {
    return var;
  }

  public ASTNode getVal() {
    return val;
  }
}
