boolean doesFunctionMeetMinimumRequirements(
      String fnName, Node fnNode) {
    Node block = NodeUtil.getFunctionBody(fnNode);

    // Don't inline recursive functions, nor functions that contain
    // 'this', 'arguments' references.
    if (NodeUtil.isNameReferenced(block, fnName)) {
      return false;
    }

    String fnRecursionName = fnNode.getFirstChild().getString();
    if (fnRecursionName != null
        && !fnRecursionName.isEmpty()
        && !fnRecursionName.equals(fnName)
        && NodeUtil.isNameReferenced(block, fnRecursionName)) {
      return false;
    }

    // nor functions that contain 'arguments' references.
    if (NodeUtil.isNameReferenced(block, "arguments")) {
      return false;
    }

    return true;
  }