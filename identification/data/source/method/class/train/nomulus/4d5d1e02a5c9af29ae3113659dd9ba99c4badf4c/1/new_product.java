public ImmutableMap<InternetDomainName, String> checkDomainsWithToken(
      List<InternetDomainName> domainNames, String token, String clientId, DateTime now) {
    AllocationToken tokenEntity = ofy().load().key(Key.create(AllocationToken.class, token)).now();
    String globalResult;
    if (tokenEntity == null) {
      globalResult = new InvalidAllocationTokenException().getMessage();
    } else if (tokenEntity.isRedeemed()) {
      globalResult = AlreadyRedeemedAllocationTokenException.ERROR_MSG_SHORT;
    } else {
      globalResult = "";
    }
    ImmutableMap<InternetDomainName, String> checkResults =
        domainNames
            .stream()
            .collect(ImmutableMap.toImmutableMap(Function.identity(), domainName -> globalResult));
    // Only call custom logic if there wasn't a global allocation token error that applies to all
    // check results. The custom logic can only add errors, not override existing errors.
    return globalResult.isEmpty()
        ? tokenCustomLogic.checkDomainsWithToken(checkResults, tokenEntity, clientId, now)
        : checkResults;
  }