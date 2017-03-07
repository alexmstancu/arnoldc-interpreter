package nodes;

import java.util.ArrayList;
import java.util.List;

public abstract class NodeWithChildren {
  private List<ASTNode> children;
  private NodeWithChildren parent;

  public NodeWithChildren(NodeWithChildren p) {
    children = new ArrayList<ASTNode>();
    parent = p;
  }

  public void addChild(ASTNode n) {
    children.add(n);
  }

  public List<ASTNode> getChildren() {
    return children;
  }
  
  public void setParent(NodeWithChildren parent) {
    this.parent = parent;
  }
  
  public NodeWithChildren getParent() {
    return parent;
  }
}