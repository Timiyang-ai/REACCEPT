public void clearFabric() {
    // before every test, clear data fabric.
    // otherwise it might see spurious entries from other tests :(
    this.local.execute(new ClearFabric(true, true, true));
  }