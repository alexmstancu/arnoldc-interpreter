package visitors;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Stack;

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

public class InterpretNodeVisitor implements NodeVisitor {

  private PrintWriter printWriter;
  private Stack<String> stack;
  private HashMap<String, String> vars;

  public InterpretNodeVisitor(String outputFile) throws IOException {
    printWriter = new PrintWriter(outputFile);
    stack = new Stack<String>();
    vars = new HashMap<>();
  }

  @Override
  public void visit(AndNode node) {
    node.getLeft().accept(this);
    node.getRight().accept(this);
    doOp('&');
  }

  @Override
  public void visit(OrNode node) {
    node.getLeft().accept(this);
    node.getRight().accept(this);
    doOp('|');
  }

  @Override
  public void visit(AssignmentNode node) {
    node.getVal().accept(this);
    vars.put(node.getVar().getName(), stack.pop());
  }

  @Override
  public void visit(BodyNode node) {
    for (ASTNode child : node.getChildren())
      child.accept(this);
  }

  @Override
  public void visit(ConditionNode node) {
    addNode(node);
  }

  @Override
  public void visit(ConstantNode node) {
    addNode(node);
  }

  @Override
  public void visit(DeclareNode node) {
    node.getConstant().accept(this);
    vars.put(node.getLval().getName(), stack.pop());
  }

  @Override
  public void visit(DifferenceNode node) {
    node.getLeft().accept(this);
    node.getRight().accept(this);
    doOp('-');
  }

  @Override
  public void visit(ElseBodyNode node) {
    for (ASTNode child : node.getChildren())
      child.accept(this);
  }

  @Override
  public void visit(IfBodyNode node) {
    for (ASTNode child : node.getChildren())
      child.accept(this);
  }

  @Override
  public void visit(IfNode node) {
    node.getCondition().accept(this);
    String valueCondition = stack.pop();
    if (!valueCondition.equals("0")) {
      node.getIfBody().accept(this);
    } else if (node.getElseBody() != null) {
      node.getElseBody().accept(this);
    }
  }

  @Override
  public void visit(LvalNode node) {
    addNode(node);
  }

  @Override
  public void visit(MainNode node) {
    for (ASTNode child : node.getChildren())
      child.accept(this);
    printWriter.close();
  }

  @Override
  public void visit(PrintNode node) {
    node.getToBePrinted().accept(this);
    printWriter.println(stack.pop());
  }

  @Override
  public void visit(RvalNode node) {
    addNode(node);
  }

  @Override
  public void visit(StringNode node) {
    addNode(node);
  }

  @Override
  public void visit(WhileNode node) {
    while (true) {
      node.getCondition().accept(this);
      String valueCondition = stack.pop();
      if (valueCondition.equals("0")) {
        break;
      }
      
      node.getBody().accept(this);
    }
  }

  @Override
  public void visit(SumNode node) {
    node.getLeft().accept(this);
    node.getRight().accept(this);
    doOp('+');
  }

  @Override
  public void visit(VariableNode node) {
    addNode(node);
  }

  @Override
  public void visit(MultiplicationNode node) {
    node.getLeft().accept(this);
    node.getRight().accept(this);
    doOp('*');
  }

  @Override
  public void visit(ModuloNode node) {
    node.getLeft().accept(this);
    node.getRight().accept(this);
    doOp('%');
  }

  @Override
  public void visit(GreaterThanNode node) {
    node.getLeft().accept(this);
    node.getRight().accept(this);
    doOp('>');
  }

  @Override
  public void visit(EqualToNode node) {
    node.getLeft().accept(this);
    node.getRight().accept(this);
    doOp('=');
  }

  @Override
  public void visit(DivisionNode node) {
    node.getLeft().accept(this);
    node.getRight().accept(this);
    doOp('/');
  }

  private <T extends PrintableNode> void addNode(T node) {
    stack.push(valueOfNode(node));
  }

  private String valueOfNode(PrintableNode node) {
    if (node instanceof VariableNode)
      return valueOfVar(((VariableNode) node).getName());
    if (node instanceof LvalNode)
      return valueOfVar(((LvalNode) node).getName());
    if (node instanceof RvalNode)
      return valueOfVar(((RvalNode) node).getName());
    if (node instanceof ConditionNode)
      return valueOfVar(((ConditionNode) node).getName());
    return node.print();
  }

  private String valueOfVar(String name) {
    return vars.get(name);
  }

  private void doOp(char op) {
    String strR = stack.pop();
    String strL = stack.pop();
    int L = Integer.parseInt(strL);
    int R = Integer.parseInt(strR);
    int result = 0;
    switch (op) {
    case '+':
      result = L + R;
      break;
    case '-':
      result = L - R;
      break;
    case '*':
      result = L * R;
      break;
    case '/':
      result = L / R;
      break;
    case '%':
      result = L % R;
      break;
    case '>':
      result = L > R ? 1 : 0;
      break;
    case '=':
      result = L == R ? 1 : 0;
      break;
    case '|':
      result = (L != 0 || R != 0) ? 1 : 0;
      break;
    case '&':
      result = (L != 0 && R != 0) ? 1 : 0;
      break;
    default:
      break;
    }
    stack.push(Integer.toString(result));
  }
}
