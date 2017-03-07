package visitors;

import nodes.AndNode;
import nodes.AssignmentNode;
import nodes.BodyNode;
import nodes.ConditionNode;
import nodes.ConstantNode;
import nodes.DeclareNode;
import nodes.DifferenceNode;
import nodes.DivisionNode;
import nodes.ElseBodyNode;
import nodes.EqualToNode;
import nodes.GreaterThanNode;
import nodes.IfBodyNode;
import nodes.IfNode;
import nodes.LvalNode;
import nodes.MainNode;
import nodes.ModuloNode;
import nodes.MultiplicationNode;
import nodes.OrNode;
import nodes.PrintNode;
import nodes.RvalNode;
import nodes.StringNode;
import nodes.SumNode;
import nodes.VariableNode;
import nodes.WhileNode;

public interface NodeVisitor {
  void visit(MainNode node);
  
  void visit(AndNode node);
  void visit(OrNode node);
  void visit(SumNode node);
  void visit(DifferenceNode node);
  void visit(MultiplicationNode node);
  void visit(ModuloNode node);
  void visit(GreaterThanNode node);
  void visit(EqualToNode node);
  void visit(DivisionNode node);
  void visit(DeclareNode node);
  void visit(AssignmentNode node);  
  void visit(WhileNode node);
  void visit(BodyNode node); 
  void visit(ConditionNode node);
  void visit(ConstantNode node);  
  void visit(IfNode node);
  void visit(IfBodyNode node);
  void visit(ElseBodyNode node);  
  void visit(LvalNode node);
  void visit(RvalNode node);
  void visit(VariableNode node); 
  void visit(PrintNode node);
  void visit(StringNode node);
}
