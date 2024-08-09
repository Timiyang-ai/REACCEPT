boolean doesFunctionMeetMinimumRequirements(
      final String fnName, Node fnNode) {
    Node block = NodeUtil.getFunctionBody(fnNode);

    // Basic restrictions on functions that can be inlined:
    // 1) It contains a reference to itself.
    // 2) It uses its parameters indirectly using "arguments" (it isn't
    //    handled yet.
    // 3) It references "eval". Inline a function containing eval can have
    //    large performance implications.

    final String fnRecursionName = fnNode.getFirstChild().getString();
    Preconditions.checkState(fnRecursionName != null);

    Predicate<Node> p = new Predicate<Node>(){
      public boolean apply(Node n) {
        if (n.getType() == Token.NAME) {
          return n.getString().equals("arguments")
            || n.getString().equals("eval")
            || n.getString().equals(fnName)
            || (!fnRecursionName.isEmpty()
                && n.getString().equals(fnRecursionName));
        }
        return false;
      }
    };

    return !NodeUtil.has(block, p, Predicates.<Node>alwaysTrue());
  }