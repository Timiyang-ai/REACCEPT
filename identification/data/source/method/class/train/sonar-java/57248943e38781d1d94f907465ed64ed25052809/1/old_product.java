public static Set<List<JavaFileScannerContext.Location>> flow(ExplodedGraph.Node currentNode, Set<SymbolicValue> symbolicValues, Predicate<Constraint> addToFlow,
                                                                Predicate<Constraint> terminateTraversal, List<Class<? extends Constraint>> domains, Set<Symbol> symbols) {
    Set<SymbolicValue> allSymbolicValues = symbolicValues.stream()
      .map(FlowComputation::computedFrom)
      .flatMap(Set::stream)
      .collect(Collectors.toSet());

    PSet<Symbol> trackedSymbols = PCollections.emptySet();
    for (Symbol symbol: symbols) {
      trackedSymbols = trackedSymbols.add(symbol);
    }

    if (symbols.isEmpty()) {
      for (SymbolicValue symbolicValue : symbolicValues) {
        for (Symbol symbol : symbolicValue.computedFromSymbols()) {
          trackedSymbols = trackedSymbols.add(symbol);
        }
      }
    }
    FlowComputation flowComputation = new FlowComputation(allSymbolicValues, addToFlow, terminateTraversal, domains);
    return flowComputation.run(currentNode, trackedSymbols);
  }