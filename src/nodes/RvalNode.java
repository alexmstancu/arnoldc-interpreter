package nodes;

import java.util.LinkedList;

import interpreter.Pair;
import visitors.NodeVisitor;

public class RvalNode implements ASTNode, PrintableNode {
  private String name;
  
  public RvalNode(String n) {
    name = n;
  }
  
  public String getName() {
    return name;
  }
  
  
  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public void parse(LinkedList<Pair<String, String>> tokens) {
    
  }

  @Override
  public String print() {
    return name;
  }

}
