public Page<ChangeRequest> listChangeRequests(Dns.ChangeRequestListOption... options) {
    return dns.listChangeRequests(name(), options);
  }