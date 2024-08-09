public static boolean equivalent(
      @Nullable Network<?, ?> networkA, @Nullable Network<?, ?> networkB) {
    if (networkA == networkB) {
      return true;
    }
    if (networkA == null || networkB == null) {
      return false;
    }

    if (networkA.isDirected() != networkB.isDirected()
        || !networkA.nodes().equals(networkB.nodes())
        || !networkA.edges().equals(networkB.edges())) {
      return false;
    }

    for (Object edge : networkA.edges()) {
      if (!networkA.incidentNodes(edge).equals(networkB.incidentNodes(edge))) {
        return false;
      }
    }

    return true;
  }