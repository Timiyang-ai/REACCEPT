public ValueWithFailures<List<SecurityPosition>> parseLightweight(Collection<CharSource> charSources) {
    return parse(charSources, SecurityPosition.class);
  }