package nodes;

import java.util.LinkedList;

import interpreter.Construction;
import interpreter.Pair;
import visitors.NodeVisitor;

public class MainNode extends NodeWithChildren implements ASTNode {

  public MainNode(NodeWithChildren parent,
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
    tokens.removeFirst();
    NodeFactory nodeFactory = new NodeFactory();
    // parse the token list for main add childs to main
    // then parse the list for childs, too
    while (!tokens.isEmpty()
        && !tokens.getFirst().getKey().equals(Construction.EndMain.name())) {
      ASTNode child = nodeFactory.getNode(this, tokens);
      addChild(child);
    }
  }
}
