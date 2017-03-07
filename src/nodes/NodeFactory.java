package nodes;

import java.util.LinkedList;

import interpreter.Construction;
import interpreter.Pair;

public class NodeFactory {
  public ASTNode getNode(NodeWithChildren parent, 
      LinkedList<Pair<String, String>> tokens) {
    String instruction = tokens.getFirst().getKey();

    if(instruction.equals(Construction.DeclareInt.name())) {
      return new DeclareNode(parent, tokens);
    }
    
    if(instruction.equals(Construction.If.name())) {
      return new IfNode(parent, tokens);
    }
    
    if(instruction.equals(Construction.While.name())) {
      return new WhileNode(parent, tokens);
    }
    
    if(instruction.equals(Construction.AssignVariable.name())) {
      return new AssignmentNode(parent, tokens);
    }
    
    if(instruction.equals(Construction.Print.name())) {
      return new PrintNode(parent, tokens);
    }
    
    return null;
  }
}
