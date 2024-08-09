boolean doesFunctionMeetMinimumRequirements(
      final String fnName, Node fnNode) {
    Node block = NodeUtil.getFunctionBody(fnNode);

    // Basic restrictions on functions that can be inlined:
    // 0) The function is inlinable by convention
    // 1) It contains a reference to itself.
    // 2) It uses its parameters indirectly using "arguments" (it isn't
    //    handled yet.
    // 3) It references "eval". Inline a function containing eval can have
    //    large performance implications.

    if (!compiler.getCodingConvention().isInlinableFunction(fnNode)) {
      return false;
    }

    final String fnRecursionName = fnNode.getFirstChild().getString();
    checkState(fnRecursionName != null);

    // If the function references "arguments" directly in the function
    boolean referencesArguments = NodeUtil.isNameReferenced(
        block, "arguments", NodeUtil.MATCH_NOT_FUNCTION);

    // or it references "eval" or one of its names anywhere.
    Predicate<Node> p = new Predicate<Node>(){
      @Override
      public boolean apply(Node n) {
        if (n.isName()) {
          return n.getString().equals("eval")
            || (!fnName.isEmpty()
                && n.getString().equals(fnName))
            || (!fnRecursionName.isEmpty()
                && n.getString().equals(fnRecursionName));
        }
        return false;
      }
    };

    return !referencesArguments
        && !NodeUtil.has(block, p, Predicates.<Node>alwaysTrue());
  }