public TaskHandle addDomainRefreshTask(String fullyQualifiedDomainName) {
    return addDomainRefreshTask(fullyQualifiedDomainName, Duration.ZERO);
  }