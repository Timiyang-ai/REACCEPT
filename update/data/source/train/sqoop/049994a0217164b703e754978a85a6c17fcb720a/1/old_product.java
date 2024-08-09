public MConnection newConnection(long cid) {
    return new MConnection(
      cid,
      getConnector(cid).getConnectionForms(),
      getFramework().getConnectionForms()
    );
  }