@Override
  public final RuleMatch[] match(final AnalyzedSentence text)
      throws IOException {
    final List<RuleMatch> ruleMatches = new ArrayList<RuleMatch>();
    final AnalyzedTokenReadings[] tokens = text.getTokensWithoutWhitespace();
    final int[] tokenPositions = new int[tokens.length + 1];
    int tokenPos = 0;
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
      boolean allElementsMatch = true;
      // stop processing if rule is longer than the sentence
      // or stop looking for sent_start - it will never match any token except
      // the first
      if (patternSize + i > tokens.length || sentStart && i > 0) {
        allElementsMatch = false;
        break;
      }
      int matchingTokens = 0;
      for (int k = 0; k < patternSize; k++) {
        prevElement = elem;
        elem = patternElements.get(k);
        skipNext = translateElementNo(elem.getSkipNext());
        final int nextPos = tokenPos + k + skipShiftTotal;
        if (nextPos >= tokens.length) {
          allElementsMatch = false;
          break;
        }
        boolean skipMatch = false, thisMatched = false, prevMatched = false;
        boolean exceptionMatched = false;
        if (prevSkipNext + nextPos >= tokens.length || prevSkipNext < 0) { // SENT_END?
          prevSkipNext = tokens.length - (nextPos + 1);
        }
        for (int m = nextPos; m <= nextPos + prevSkipNext; m++) {
          boolean matched = false;
          final int numberOfReadings = tokens[m].getReadingsLength();
          for (int l = 0; l < numberOfReadings; l++) {
            final boolean lastReading = l + 1 == numberOfReadings;
            final AnalyzedToken matchToken = tokens[m].getAnalyzedToken(l);
            if (prevSkipNext > 0 && prevElement != null
                && prevElement.isMatchedByScopeNextException(matchToken)) {
              exceptionMatched = true;
              prevMatched = true;
            }
            if (elem.isReferenceElement()) {
              setupRef(firstMatchToken, elem, tokens);
            }
            setupAndGroup(l, firstMatchToken, elem, tokens);
            thisMatched |= elem.isMatchedCompletely(matchToken);
            thisMatched &= testUnification(thisMatched, lastReading, 
                matchToken, elem);
            if (lastReading && elem.hasAndGroup()) {
              thisMatched &= elem.checkAndGroup(thisMatched);
            }
            exceptionMatched |= elem.isExceptionMatchedCompletely(matchToken);
            if (!exceptionMatched && m > 0 && elem.hasPreviousException()) {
              exceptionMatched |= elem.
                isMatchedByPreviousException(tokens[m - 1]);
            }
            // Logical OR (cannot be AND):
            if (thisMatched || exceptionMatched) {
              matched = true;
              matchPos = m;
              skipShift = matchPos - nextPos;
              tokenPositions[matchingTokens] = skipShift + 1;
            }
            skipMatch = (skipMatch || matched) && !exceptionMatched;
          }
          // disallow exceptions that should match only current tokens
          if (!(thisMatched || prevMatched)) {
            exceptionMatched = false;
            skipMatch = false;
          }
          if (skipMatch) {
            break;
          }
        }
        allElementsMatch = skipMatch;
        if (skipMatch) {
          prevSkipNext = skipNext;
          matchingTokens++;
          lastMatchToken = matchPos;
          if (firstMatchToken == -1) {
            firstMatchToken = matchPos;
          }
          skipShiftTotal += skipShift;
        } else {
          prevSkipNext = 0;
          skipShiftTotal = 0;
          break;
        }
      }
      tokenPos++;
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
      language.getUnifier().reset();
    }
    return ruleMatches.toArray(new RuleMatch[ruleMatches.size()]);
  }