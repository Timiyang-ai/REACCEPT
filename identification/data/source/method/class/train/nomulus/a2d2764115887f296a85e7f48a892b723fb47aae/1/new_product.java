public static Money getDomainRenewCost(String domainName, DateTime priceTime, int years) {
    checkArgument(years > 0, "Number of years must be positive");
    return getPricesForDomainName(domainName, priceTime).getRenewCost().multipliedBy(years);
  }