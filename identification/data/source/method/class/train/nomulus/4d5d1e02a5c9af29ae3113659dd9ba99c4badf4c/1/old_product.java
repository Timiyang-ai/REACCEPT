public ImmutableMap<String, String> checkDomainsWithToken(
      List<String> domainNames, String token, String clientId) {
    AllocationToken tokenEntity = ofy().load().key(Key.create(AllocationToken.class, token)).now();
    String result;
    if (tokenEntity == null) {
      result = new InvalidAllocationTokenException().getMessage();
    } else if (tokenEntity.isRedeemed()) {
      result = AlreadyRedeemedAllocationTokenException.ERROR_MSG_SHORT;
    } else {
      result = "";
    }
    ImmutableMap<String, String> checkResults =
        domainNames
            .stream()
            .collect(ImmutableMap.toImmutableMap(Function.identity(), domainName -> result));
    return tokenCustomLogic.checkDomainsWithToken(checkResults, tokenEntity, clientId);
  }