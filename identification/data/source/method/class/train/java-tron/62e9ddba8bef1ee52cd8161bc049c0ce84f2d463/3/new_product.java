public void setAssets(final List<Account> assets) {
    this.assets = assets;

    if (assets == null) {
      this.assets = Collections.emptyList();
    }
  }