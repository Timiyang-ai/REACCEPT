public static Money getDomainRenewCost(
      String domainName, DateTime priceTime, String clientIdentifier, int years) {
    checkArgument(years > 0, "Number of years must be positive");
    Optional<Money> annualCost =
        getPremiumPriceForDomainName(
            InternetDomainName.from(domainName), priceTime, clientIdentifier);
    return annualCost
        .or(Registry.get(getTldFromDomainName(domainName)).getStandardRenewCost(priceTime))
        .multipliedBy(years);
  }