public TaskHandle addHostRefreshTask(String fullyQualifiedHostName) {
    Optional<InternetDomainName> tld =
        Registries.findTldForName(InternetDomainName.from(fullyQualifiedHostName));
    checkArgument(tld.isPresent(),
        String.format("%s is not a subordinate host to a known tld", fullyQualifiedHostName));
    return addToQueue(TargetType.HOST, fullyQualifiedHostName, tld.get().toString());
  }