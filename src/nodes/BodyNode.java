package nodes;

import java.util.LinkedList;

import interpreter.Construction;
import interpreter.Pair;
import visitors.NodeVisitor;

public class BodyNode extends NodeWithChildren implements ASTNode {
  
  public BodyNode(NodeWithChildren parent, LinkedList<Pair<String, String>> tokens) {
    super(parent);
    parse(tokens);
  }
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);

  }

  @Override
  public void parse(LinkedList<Pair<String, String>> tokens) {
    Pair<String, String> top = tokens.getFirst();
    NodeFactory nodeFactory = new NodeFactory();
    while (!top.getKey().equals(Construction.EndWhile.name())) {
      ASTNode child = nodeFactory.getNode(this, tokens);
      addChild(child);
      top = tokens.getFirst();
    }
  }

}
