public ProposalList getPaginatedProposalList(long offset, long limit) {
    ProposalList.Builder builder = ProposalList.newBuilder();
    ImmutableList<Long> rangeList = ContiguousSet
        .create(Range.closedOpen(offset, offset + limit), DiscreteDomain.longs()).asList();
    rangeList.stream().map(ProposalCapsule::calculateDbKey).map(key -> {
      try {
        return dbManager.getProposalStore().get(key);
      } catch (Exception ex) {
        return null;
      }
    }).filter(Objects::nonNull)
        .forEach(proposalCapsule -> builder.addProposals(proposalCapsule.getInstance()));
    return builder.build();
  }