public static Span extractNER(List<CoreLabel> tokens, Span seed) {
    // Error checks
    if (seed == null) {
      return new Span(0, 1);
    }
    if (seed.start() < 0 || seed.end() < 0) {
      return new Span(0, 0);
    }
    if (seed.start() >= tokens.size() || seed.end() > tokens.size()) {
      return new Span(tokens.size(),tokens.size());
    }
    if (tokens.get(seed.start()).ner() == null) {
      return seed;
    }
    if (seed.start() < 0 || seed.end() > tokens.size()) {
      return Span.fromValues(Math.max(0, seed.start()), Math.min(tokens.size(), seed.end()));
    }

    // Find the span's beginning
    int begin = seed.start();
    while (begin < seed.end() - 1 && "O".equals(tokens.get(begin).ner())) {
      begin += 1;
    }
    String beginNER = tokens.get(begin).ner();
    if (!"O".equals(beginNER)) {
      while (begin > 0 && tokens.get(begin - 1).ner().equals(beginNER)) {
        begin -= 1;
      }
    } else {
      begin = seed.start();
    }
    // Find the span's end
    int end = seed.end() - 1;
    while (end > begin && "O".equals(tokens.get(end).ner())) {
      end -= 1;
    }
    String endNER = tokens.get(end).ner();
    if (!"O".equals(endNER)) {
      while (end < tokens.size() - 1 && tokens.get(end + 1).ner().equals(endNER)) {
        end += 1;
      }
    } else {
      end = seed.end() - 1;
    }
    // Check that the NER of the beginning and end are the same
    if (beginNER.equals(endNER)) {
      return Span.fromValues(begin, end + 1);
    } else {
      String bestNER = guessNER(tokens, Span.fromValues(begin, end + 1));
      if (beginNER.equals(bestNER)) {
        return extractNER(tokens, Span.fromValues(begin, begin + 1));
      } else if (endNER.equals(bestNER)){
        return extractNER(tokens, Span.fromValues(end, end + 1));
      } else {
        // Something super funky is going on...
        return Span.fromValues(begin, end + 1);
      }
    }
  }