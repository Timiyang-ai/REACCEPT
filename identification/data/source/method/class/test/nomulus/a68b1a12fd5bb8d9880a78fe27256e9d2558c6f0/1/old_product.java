public static void enqueueDomainBaseTask(DomainBase domain) {
    ofy().assertInTransaction();
    // This method needs to use ofy transactionTime as the DomainBase's creationTime because
    // CreationTime isn't yet populated when this method is called during the resource flow.
    String tld = domain.getTld();
    if (domain.getLaunchNotice() == null) {
      getQueue(QUEUE_SUNRISE).add(TaskOptions.Builder
          .withTag(tld)
          .method(Method.PULL)
          .payload(getCsvLineForSunriseDomain(domain, ofy().getTransactionTime())));
    } else {
      getQueue(QUEUE_CLAIMS).add(TaskOptions.Builder
          .withTag(tld)
          .method(Method.PULL)
          .payload(getCsvLineForClaimsDomain(domain, ofy().getTransactionTime())));
    }
  }