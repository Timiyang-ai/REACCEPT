public void setWitnesses(final List<Witness> witnesses) {
    this.witnesses = witnesses;

    if (witnesses == null) {
      this.witnesses = Collections.emptyList();
    }
  }