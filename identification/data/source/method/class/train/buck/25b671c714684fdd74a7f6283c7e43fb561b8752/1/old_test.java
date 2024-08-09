  private static <U extends ConstructorArg> U extractArg(TargetNode<?> node, Class<U> clazz) {
    return TargetNodes.castArg(node, clazz)
        .orElseThrow(
            () ->
                new AssertionError(
                    String.format(
                        "%s: expected constructor arg to be of type %s (was %s)",
                        node, clazz, node.getConstructorArg().getClass())))
        .getConstructorArg();
  }