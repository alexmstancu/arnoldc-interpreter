package nodes;

import java.util.LinkedList;

import interpreter.Construction;
import interpreter.Pair;
import visitors.NodeVisitor;

public class ElseBodyNode extends NodeWithChildren implements ASTNode {

  public ElseBodyNode(NodeWithChildren p, 
      LinkedList<Pair<String, String>> tokens) {
    super(p);
    parse(tokens);
  }

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);

  }

  @Override
  public void parse(LinkedList<Pair<String, String>> tokens) {
    tokens.removeFirst();
    Pair<String, String> top = tokens.getFirst();
    NodeFactory nodeFactory = new NodeFactory();
    // traverse the list of tokens and add childs of else
    while (!top.getKey().equals(Construction.EndIf.name())) {
      ASTNode child = nodeFactory.getNode(this, tokens);
      addChild(child);
      top = tokens.getFirst();
    }
  }

}
