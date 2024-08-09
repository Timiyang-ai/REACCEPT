public static void closeScope(Object name) {
    synchronized (ROOT_SCOPES) {
      // we remove the scope first, so that other threads don't see it, and see the next snapshot of
      // the tree
      ScopeNode scope = (ScopeNode) MAP_KEY_TO_SCOPE.remove(name);
      if (scope != null) {
        ScopeNode parentScope = scope.getParentScope();
        if (parentScope != null) {
          parentScope.removeChild(scope);
        } else {
          ConfigurationHolder.configuration.onScopeForestReset();
          ROOT_SCOPES.remove(name);
        }
        removeScopeAndChildrenFromMap(scope);
      }
    }
  }