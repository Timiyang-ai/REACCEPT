public static Money getDomainCreateCost(String domainName, DateTime priceTime, int years) {
    checkArgument(years > 0, "Number of years must be positive");
    return getPricesForDomainName(domainName, priceTime).getCreateCost().multipliedBy(years);
  }