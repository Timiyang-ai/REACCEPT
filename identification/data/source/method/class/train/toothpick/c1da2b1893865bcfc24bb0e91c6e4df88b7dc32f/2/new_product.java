public static void release(Scope scope) {
    ScopeNode scopeNode = (ScopeNode) scope;
    scopeNode.release();
  }