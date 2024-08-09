public static Set<Flow> flow(ExplodedGraph.Node currentNode, Set<SymbolicValue> symbolicValues, Predicate<Constraint> addToFlow, Predicate<Constraint> terminateTraversal,
    List<Class<? extends Constraint>> domains, Set<Symbol> symbols) {
    return flow(currentNode, symbolicValues, addToFlow, terminateTraversal, domains, symbols, false);
  }