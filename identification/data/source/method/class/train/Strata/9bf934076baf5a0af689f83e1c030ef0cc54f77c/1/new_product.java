public CsvRowOutputWithHeaders withHeaders(List<String> headers, boolean alwaysQuote) {
    ImmutableMap<String, Integer> headerIndices = zipWithIndex(headers.stream())
        .collect(toImmutableMap(ObjIntPair::getFirst, ObjIntPair::getSecond));
    writeLine(headers, alwaysQuote);
    return new CsvRowOutputWithHeaders(ImmutableList.copyOf(headers), headerIndices, alwaysQuote);
  }