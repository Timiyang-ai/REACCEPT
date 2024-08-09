  private void vetoed(Veto... vetoes) {
    nearest.vetoed(GROUP_KEY, ImmutableSet.copyOf(vetoes));
  }