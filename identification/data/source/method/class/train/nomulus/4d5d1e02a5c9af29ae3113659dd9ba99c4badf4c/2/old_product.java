public AllocationTokenDomainCheckResults checkDomainsWithToken(
      List<InternetDomainName> domainNames, String token, String clientId, DateTime now) {
    try {
      AllocationToken tokenEntity = loadToken(token);
      // Only call custom logic if there wasn't a global allocation token error that applies to all
      // check results. The custom logic can only add errors, not override existing errors.
      return AllocationTokenDomainCheckResults.create(
          Optional.of(tokenEntity),
          tokenCustomLogic.checkDomainsWithToken(
              ImmutableList.copyOf(domainNames), tokenEntity, clientId, now));
    } catch (EppException e) {
      return AllocationTokenDomainCheckResults.create(
          Optional.empty(),
          ImmutableMap.copyOf(Maps.toMap(domainNames, ignored -> e.getMessage())));
    }
  }