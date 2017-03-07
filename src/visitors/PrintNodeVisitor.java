package visitors;

import java.io.IOException;
import java.io.PrintWriter;

import nodes.ASTNode;
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
import nodes.PrintableNode;
import nodes.RvalNode;
import nodes.StringNode;
import nodes.SumNode;
import nodes.VariableNode;
import nodes.WhileNode;

public class PrintNodeVisitor implements NodeVisitor {

  private PrintWriter printWriter;
  private int tabs;

  public PrintNodeVisitor(String outputFile) throws IOException {
    printWriter = new PrintWriter(outputFile);
    tabs = 0;
  }

  private void print(String s) {
    for (int i = 0; i < tabs; i++)
      printWriter.print('\t');
    printWriter.println(s);
  }

  @Override
  public void visit(AndNode node) {
    printNode(node);
    tabs++;
    node.getLeft().accept(this);
    node.getRight().accept(this);
    tabs--;
  }

  @Override
  public void visit(OrNode node) {
    printNode(node);
    tabs++;
    node.getLeft().accept(this);
    node.getRight().accept(this);
    tabs--;
  }

  @Override
  public void visit(AssignmentNode node) {
    printNode(node);
    tabs++;
    node.getVar().accept(this);
    node.getVal().accept(this);
    tabs--;
  }

  @Override
  public void visit(BodyNode node) {
    printNode(node);
    tabs++;
    for (ASTNode child : node.getChildren())
      child.accept(this);
    tabs--;
  }

  @Override
  public void visit(ConditionNode node) {
    printNode(node);
  }

  @Override
  public void visit(ConstantNode node) {
    printNode(node);
  }

  @Override
  public void visit(DeclareNode node) {
    printNode(node);
    tabs++;
    node.getLval().accept(this);
    node.getConstant().accept(this);
    tabs--;
  }

  @Override
  public void visit(DifferenceNode node) {
    printNode(node);
    tabs++;
    node.getLeft().accept(this);
    node.getRight().accept(this);
    tabs--;
  }

  @Override
  public void visit(ElseBodyNode node) {
    printNode(node);
    tabs++;
    for (ASTNode child : node.getChildren())
      child.accept(this);
    tabs--;
  }

  @Override
  public void visit(IfBodyNode node) {
    printNode(node);
    tabs++;
    for (ASTNode child : node.getChildren())
      child.accept(this);
    tabs--;
  }

  @Override
  public void visit(IfNode node) {
    printNode(node);
    tabs++;
    node.getCondition().accept(this);
    node.getIfBody().accept(this);
    if (node.getElseBody() != null)
      node.getElseBody().accept(this);
    tabs--;
  }

  @Override
  public void visit(LvalNode node) {
    printNode(node);
  }

  @Override
  public void visit(MainNode node) {
    printNode(node);
    tabs++;
    for (ASTNode child : node.getChildren())
      child.accept(this);
    tabs--;
    printWriter.close();
  }

  @Override
  public void visit(PrintNode node) {
    printNode(node);
    tabs++;
    node.getToBePrinted().accept(this);
    tabs--;
  }

  @Override
  public void visit(RvalNode node) {
    printNode(node);
  }

  @Override
  public void visit(StringNode node) {
    printNode(node);
  }

  @Override
  public void visit(WhileNode node) {
    printNode(node);
    tabs++;
    node.getCondition().accept(this);
    node.getBody().accept(this);
    tabs--;
  }

  @Override
  public void visit(SumNode node) {
    printNode(node);
    tabs++;
    node.getLeft().accept(this);
    node.getRight().accept(this);
    tabs--;
  }

  @Override
  public void visit(VariableNode node) {
    printNode(node);
  }

  @Override
  public void visit(MultiplicationNode node) {
    printNode(node);
    tabs++;
    node.getLeft().accept(this);
    node.getRight().accept(this);
    tabs--;
  }

  @Override
  public void visit(ModuloNode node) {
    printNode(node);
    tabs++;
    node.getLeft().accept(this);
    node.getRight().accept(this);
    tabs--;
  }

  @Override
  public void visit(GreaterThanNode node) {
    printNode(node);
    tabs++;
    node.getLeft().accept(this);
    node.getRight().accept(this);
    tabs--;
  }

  @Override
  public void visit(EqualToNode node) {
    printNode(node);
    tabs++;
    node.getLeft().accept(this);
    node.getRight().accept(this);
    tabs--;
  }

  @Override
  public void visit(DivisionNode node) {
    printNode(node);
    tabs++;
    node.getLeft().accept(this);
    node.getRight().accept(this);
    tabs--;
  }

  public <T extends ASTNode> void printNode(T node) {
    String str = node.getClass().getSimpleName();
    if (node instanceof PrintableNode) {
      PrintableNode pNode = (PrintableNode) node;
      str += " <" + pNode.print() + ">";
    }
    print(str);
  }
}
