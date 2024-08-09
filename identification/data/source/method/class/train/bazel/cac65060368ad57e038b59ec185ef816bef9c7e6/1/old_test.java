  private void setBreakpoints(Collection<Location> locations) throws Exception {
    setBreakpoints(
        locations
            .stream()
            .map(l -> Breakpoint.newBuilder().setLocation(l).build())
            .collect(Collectors.toList()));
  }