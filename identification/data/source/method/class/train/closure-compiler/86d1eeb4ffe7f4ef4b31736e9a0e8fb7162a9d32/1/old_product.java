public static Node getNameNode(Node n) {
    Preconditions.checkState(n.isFunction() || n.isClass());
    Node parent = n.getParent();
    switch (parent.getType()) {
      case NAME:
        // var name = function() ...
        // var name2 = function name1() ...
        return parent;

      case ASSIGN: {
        // qualified.name = function() ...
        // qualified.name2 = function name1() ...
        Node firstChild = parent.getFirstChild();
        return firstChild.isQualifiedName() ? firstChild : null;
      }

      default:
        // function name() ...
        Node funNameNode = n.getFirstChild();
        // Don't return the name node for anonymous functions/classes.
        // TODO(tbreisacher): Currently we do two kinds of "empty" checks because
        // anonymous classes have an EMPTY name node while anonymous functions
        // have a STRING node with an empty string. Consider making these the same.
        return (funNameNode.isEmpty() || funNameNode.getString().isEmpty())
            ? null : funNameNode;
    }
  }