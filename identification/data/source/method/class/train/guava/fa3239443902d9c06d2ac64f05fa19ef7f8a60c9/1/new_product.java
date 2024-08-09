public static <N> Traverser<N> forTree(SuccessorsFunction<N> tree) {
    checkNotNull(tree);
    if (tree instanceof BaseGraph) {
      checkArgument(((BaseGraph<?>) tree).isDirected(), "Undirected graphs can never be trees.");
    }
    if (tree instanceof Network) {
      checkArgument(((Network<?, ?>) tree).isDirected(), "Undirected networks can never be trees.");
    }
    return new TreeTraverser<>(tree);
  }