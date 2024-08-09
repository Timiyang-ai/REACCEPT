@Override
  public final RuleMatch[] match(final AnalyzedSentence text)
      throws IOException {
    final List<RuleMatch> ruleMatches = new ArrayList<RuleMatch>();
    final AnalyzedTokenReadings[] tokens = text.getTokensWithoutWhitespace();
    final int[] tokenPositions = new int[tokens.length + 1];
    final int patternSize = patternElements.size();
    Element elem = null;
    final boolean sentStart = patternElements.get(0).isSentStart();
    for (int i = 0; i < tokens.length; i++) {
      // stop processing if rule is longer than the sentence
      // or stop looking for sent_start
      if (patternSize + i > tokens.length || sentStart && i > 0) {
        break;
      }
      boolean allElementsMatch = false;
      int firstMatchToken = -1;
      int lastMatchToken = -1;
      int matchingTokens = 0;
      int prevSkipNext = 0;
      // this variable keeps the total number
      // of tokens skipped
      int skipShiftTotal = 0;
      language.getUnifier().reset();
      for (int k = 0; k < patternSize; k++) {
        final Element prevElement = elem;
        elem = patternElements.get(k);
        setupRef(firstMatchToken, elem, tokens);
        final int skipNext = translateElementNo(elem.getSkipNext());
        final int nextPos = i + k + skipShiftTotal;
        if (nextPos >= tokens.length) {
          allElementsMatch = false;
          break;
        }
        prevMatched = false;
        if (prevSkipNext + nextPos >= tokens.length || prevSkipNext < 0) { // SENT_END?
          prevSkipNext = tokens.length - (nextPos + 1);
        }
        for (int m = nextPos; m <= nextPos + prevSkipNext; m++) {
          allElementsMatch = testAllReadings(tokens, elem, prevElement, m,
              firstMatchToken, prevSkipNext);
          if (allElementsMatch) {
            lastMatchToken = m;
            final int skipShift = lastMatchToken - nextPos;
            tokenPositions[matchingTokens] = skipShift + 1;
            prevSkipNext = skipNext;
            matchingTokens++;
            skipShiftTotal += skipShift;
            if (firstMatchToken == -1) {
              firstMatchToken = lastMatchToken;
            }
            break;
          }
        }
        if (!allElementsMatch) {
          break;
        }
      }
      // FIXME: this might be probably removed
      if (firstMatchToken + matchingTokens >= tokens.length) {
        matchingTokens = tokens.length - firstMatchToken;
      }
      if (firstMatchToken + skipShiftTotal + matchingTokens > tokens.length) {
        allElementsMatch = false;
      }
      if (allElementsMatch) {
        final RuleMatch rM = createRuleMatch(tokenPositions, tokens,
            firstMatchToken, lastMatchToken, matchingTokens);
        if (rM != null) {
          ruleMatches.add(rM);
        }
      }
    }
    return ruleMatches.toArray(new RuleMatch[ruleMatches.size()]);
  }