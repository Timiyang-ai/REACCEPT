public TaskHandle addDomainRefreshTask(String fullyQualifiedDomainName) {
    return addToQueue(
        TargetType.DOMAIN,
        fullyQualifiedDomainName,
        assertTldExists(getTldFromSld(fullyQualifiedDomainName)));
  }