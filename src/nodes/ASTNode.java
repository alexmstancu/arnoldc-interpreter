package nodes;

import java.util.LinkedList;

import interpreter.Pair;
import visitors.NodeVisitor;

public interface ASTNode {
  void accept(NodeVisitor visitor);
  void parse(LinkedList<Pair<String, String>> tokens);
}
