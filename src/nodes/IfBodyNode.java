package nodes;

import java.util.LinkedList;

import interpreter.Construction;
import interpreter.Pair;
import visitors.NodeVisitor;

public class IfBodyNode extends NodeWithChildren implements ASTNode {

  public IfBodyNode(NodeWithChildren parent,
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
    Pair<String, String> top = tokens.getFirst();
    NodeFactory nodeFactory = new NodeFactory();
    // traverse the list of tokens and add childs of if body
    while (!top.getKey().equals(Construction.EndIf.name())
        && !top.getKey().equals(Construction.Else.name())) {
      ASTNode child = nodeFactory.getNode(this, tokens);
      addChild(child);
      top = tokens.getFirst();
    }
  }

}
