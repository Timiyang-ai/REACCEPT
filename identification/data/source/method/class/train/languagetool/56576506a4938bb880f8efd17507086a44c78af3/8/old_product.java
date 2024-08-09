@Override
  public final RuleMatch[] match(final AnalyzedSentence text)
      throws IOException {
    final List<RuleMatch> ruleMatches = new ArrayList<RuleMatch>();
    final AnalyzedTokenReadings[] tokens = text.getTokensWithoutWhitespace();
    final int[] tokenPositions = new int[tokens.length + 1];
    int prevSkipNext = 0;
    int skipNext = 0;
    int matchPos = 0;
    int skipShift = 0;
    // this variable keeps the total number
    // of tokens skipped - used to avoid
    // that nextPos gets back to unmatched tokens...
    int skipShiftTotal = 0;
    int firstMatchToken = -1;
    int lastMatchToken = -1;
    final int patternSize = patternElements.size();
    Element elem = null, prevElement = null;
    final boolean sentStart = patternElements.get(0).isSentStart();
    language.getUnifier().reset();
    for (int i = 0; i < tokens.length; i++) {
      // stop processing if rule is longer than the sentence
      // or stop looking for sent_start
      if (patternSize + i > tokens.length || sentStart && i > 0) {
        break;
      }
      boolean allElementsMatch = false;
      int matchingTokens = 0;
      for (int k = 0; k < patternSize; k++) {
        prevElement = elem;
        elem = patternElements.get(k);
        skipNext = translateElementNo(elem.getSkipNext());
        final int nextPos = i + k + skipShiftTotal;
        if (nextPos >= tokens.length) {
          allElementsMatch = false;
          break;
        }
        boolean thisMatched = false, prevMatched = false;
        boolean exceptionMatched = false;
        if (prevSkipNext + nextPos >= tokens.length || prevSkipNext < 0) { // SENT_END?
          prevSkipNext = tokens.length - (nextPos + 1);
        }
        for (int m = nextPos; m <= nextPos + prevSkipNext; m++) {
          final int numberOfReadings = tokens[m].getReadingsLength();
          for (int l = 0; l < numberOfReadings; l++) {
            final boolean lastReading = l + 1 == numberOfReadings;
            final AnalyzedToken matchToken = tokens[m].getAnalyzedToken(l);
            prevMatched |= prevSkipNext > 0 && prevElement != null
                && prevElement.isMatchedByScopeNextException(matchToken);
            if (elem.isReferenceElement()) {
              setupRef(firstMatchToken, elem, tokens);
            }
            setupAndGroup(l, firstMatchToken, elem, tokens);
            thisMatched |= elem.isMatchedCompletely(matchToken);
            thisMatched &= testUnification(thisMatched, lastReading, 
                matchToken, elem);
            if (lastReading) {
              thisMatched &= elem.checkAndGroup(thisMatched);
            }
            exceptionMatched |= elem.isExceptionMatchedCompletely(matchToken);
            if (!exceptionMatched && m > 0 && elem.hasPreviousException()) {
              exceptionMatched |= elem.
                isMatchedByPreviousException(tokens[m - 1]);
            }
            if (thisMatched) {
              matchPos = m;
              skipShift = matchPos - nextPos;
              tokenPositions[matchingTokens] = skipShift + 1;
            }
            allElementsMatch = (allElementsMatch || thisMatched) && !(exceptionMatched || prevMatched);
          }
          // disallow exceptions that should match only current tokens
          exceptionMatched &= thisMatched;
          allElementsMatch &= thisMatched;
          if (allElementsMatch) {
            break;
          }
        }
        if (allElementsMatch) {
          prevSkipNext = skipNext;
          matchingTokens++;
          lastMatchToken = matchPos;
          if (firstMatchToken == -1) {
            firstMatchToken = matchPos;
          }
          skipShiftTotal += skipShift;
        } else {
          break;
        }
      }
      //FIXME: this might be probably removed
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
      firstMatchToken = -1;
      lastMatchToken = -1;
      skipShiftTotal = 0;
      prevSkipNext = 0;
      language.getUnifier().reset();
    }
    return ruleMatches.toArray(new RuleMatch[ruleMatches.size()]);
  }