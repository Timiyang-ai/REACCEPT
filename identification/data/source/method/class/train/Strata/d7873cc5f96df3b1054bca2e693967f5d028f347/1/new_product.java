@Override
  public InterpolatedNodalCurveDefinition filtered(LocalDate valuationDate, ReferenceData refData) {
    // mutable list of date-node pairs
    ArrayList<Pair<LocalDate, CurveNode>> nodeDates = nodes.stream()
        .map(node -> Pair.of(node.date(valuationDate, refData), node))
        .collect(toCollection(ArrayList::new));
    // delete nodes if clash, but don't throw exceptions yet
    loop:
    for (int i = 0; i < nodeDates.size(); i++) {
      Pair<LocalDate, CurveNode> pair = nodeDates.get(i);
      CurveNodeDateOrder restriction = pair.getSecond().getDateOrder();
      // compare node to previous node
      if (i > 0) {
        Pair<LocalDate, CurveNode> pairBefore = nodeDates.get(i - 1);
        if (DAYS.between(pairBefore.getFirst(), pair.getFirst()) < restriction.getMinGapInDays()) {
          switch (restriction.getAction()) {
            case DROP_THIS:
              nodeDates.remove(i);
              i = -1;  // restart loop
              continue loop;
            case DROP_OTHER:
              nodeDates.remove(i - 1);
              i = -1;  // restart loop
              continue loop;
            case EXCEPTION:
              break;  // do nothing yet
            default:
              throw new IllegalStateException("Unexpected enum value");
          }
        }
      }
      // compare node to next node
      if (i < nodeDates.size() - 1) {
        Pair<LocalDate, CurveNode> pairAfter = nodeDates.get(i + 1);
        if (DAYS.between(pair.getFirst(), pairAfter.getFirst()) < restriction.getMinGapInDays()) {
          switch (restriction.getAction()) {
            case DROP_THIS:
              nodeDates.remove(i);
              i = -1;  // restart loop
              continue loop;
            case DROP_OTHER:
              nodeDates.remove(i + 1);
              i = -1;  // restart loop
              continue loop;
            case EXCEPTION:
              break;  // do nothing yet
            default:
              throw new IllegalStateException("Unexpected enum value");
          }
        }
      }
    }
    // throw exceptions if rules breached
    for (int i = 0; i < nodeDates.size(); i++) {
      Pair<LocalDate, CurveNode> pair = nodeDates.get(i);
      CurveNodeDateOrder restriction = pair.getSecond().getDateOrder();
      // compare node to previous node
      if (i > 0) {
        Pair<LocalDate, CurveNode> pairBefore = nodeDates.get(i - 1);
        if (DAYS.between(pairBefore.getFirst(), pair.getFirst()) < restriction.getMinGapInDays()) {
          throw new IllegalArgumentException(Messages.format(
              "Curve node dates clash, node '{}' and '{}' resolved to dates '{}' and '{}' respectively",
              pairBefore.getSecond().getLabel(),
              pair.getSecond().getLabel(),
              pairBefore.getFirst(),
              pair.getFirst()));
        }
      }
      // compare node to next node
      if (i < nodeDates.size() - 1) {
        Pair<LocalDate, CurveNode> pairAfter = nodeDates.get(i + 1);
        if (DAYS.between(pair.getFirst(), pairAfter.getFirst()) < restriction.getMinGapInDays()) {
          throw new IllegalArgumentException(Messages.format(
              "Curve node dates clash, node '{}' and '{}' resolved to dates '{}' and '{}' respectively",
              pair.getSecond().getLabel(),
              pairAfter.getSecond().getLabel(),
              pair.getFirst(),
              pairAfter.getFirst()));
        }
      }
    }
    // return the resolved definition
    List<CurveNode> filteredNodes = nodeDates.stream().map(p -> p.getSecond()).collect(toImmutableList());
    return new InterpolatedNodalCurveDefinition(
        name, xValueType, yValueType, dayCount, filteredNodes, interpolator, extrapolatorLeft, extrapolatorRight);
  }