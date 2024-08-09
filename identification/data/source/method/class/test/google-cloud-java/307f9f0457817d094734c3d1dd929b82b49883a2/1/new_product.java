public Page<ChangeRequest> listChangeRequests(Dns.ChangeRequestListOption... options) {
    return dns.listChangeRequests(getName(), options);
  }