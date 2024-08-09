public int compareKey(SwaptionSabrSensitivity other) {
    return ComparisonChain.start()
        .compare(convention.toString(), other.convention.toString())
        .compare(expiry, other.expiry)
        .compare(tenor, other.tenor)
        .compare(currency, other.currency)
        .result();
  }