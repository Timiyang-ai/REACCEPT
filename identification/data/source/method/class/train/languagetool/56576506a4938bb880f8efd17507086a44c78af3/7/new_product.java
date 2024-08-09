@Override
  public final RuleMatch[] match(final AnalyzedSentence text) throws IOException {

    final List<RuleMatch> ruleMatches = new ArrayList<RuleMatch>();    
    final AnalyzedTokenReadings[] tokens = text.getTokensWithoutWhitespace();
    final int[] tokenPositions = new int[tokens.length + 1 ];

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
    final boolean startWithSentStart = patternElements.get(0).isSentStart();

    boolean inUnification = false;
    boolean uniMatched = false;

    for (int i = 0; i < tokens.length; i++) {
      boolean allElementsMatch = true;

      //stop processing if rule is longer than the sentence
      if (patternSize + i > tokens.length) {
        allElementsMatch = false;
        break;
      }

      //stop looking for sent_start - it will never match any
      //token except the first
      if (startWithSentStart && i > 0) {
        allElementsMatch = false;
        break;
      }

      int matchingTokens = 0;
      for (int k = 0; (k < patternSize); k++) {
        if (elem != null) {
          prevElement = elem;
        }
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
            final AnalyzedToken matchToken = tokens[m].getAnalyzedToken(l);
            if (prevSkipNext > 0 && prevElement != null
                && prevElement.isMatchedByScopeNextException(matchToken)) {
              exceptionMatched = true;
              prevMatched = true;              
            }
            if (elem.isReferenceElement()
                && (firstMatchToken + elem.getMatch().getTokenRef() 
                    < tokens.length)) {
              elem.compile(tokens[firstMatchToken + 
                                  elem.getMatch().getTokenRef()],
                                  language.getSynthesizer());
            }            

            if (elem.hasAndGroup()) {
              for (final Element andElement : elem.getAndGroup()) {
                if (andElement.isReferenceElement()
                    && (firstMatchToken + andElement.getMatch().getTokenRef() 
                        < tokens.length)) {
                  andElement.compile(tokens[firstMatchToken 
                                            + andElement.getMatch().getTokenRef()],
                                            language.getSynthesizer());
                }                
              }              
              if (l == 0) { 
                elem.setupAndGroup();
              }
            }

            thisMatched |= elem.isMatchedCompletely(matchToken);            
            if (thisMatched && elem.isUnified()) {
              if (inUnification) {                
                uniMatched = uniMatched || language.getUnifier().
                isSatisfied(matchToken, elem.getUniFeature(), elem.getUniType());
                if (l + 1 == numberOfReadings) {
                  thisMatched &= uniMatched;
                }
              } else {
                if (elem.getUniNegation()) {
                  language.getUnifier().setNegation(true);
                } 
                thisMatched |= language.getUnifier().
                isSatisfied(matchToken, elem.getUniFeature(), elem.getUniType());                 
                if (l + 1 == numberOfReadings) {
                  inUnification = true;
                  language.getUnifier().startUnify();
                  uniMatched = false;
                }
              }
            } 

            if (!elem.isUnified()) {
              inUnification = false;
              uniMatched = false;
              language.getUnifier().reset();
            }

            if (l + 1 == numberOfReadings && elem.hasAndGroup()) {
              thisMatched &= elem.checkAndGroup(thisMatched);
            }                
            exceptionMatched |= elem.isExceptionMatchedCompletely(matchToken);
            if (!exceptionMatched && m > 0 && elem.hasPreviousException()) {
              final int numReadings = tokens[m - 1].getReadingsLength();
              for (int p = 0; p < numReadings; p++) {             
                exceptionMatched |= 
                  elem.isMatchedByScopePreviousException(
                      tokens[m - 1].getAnalyzedToken(p));
              }
            }

            // Logical OR (cannot be AND):
            if (thisMatched || exceptionMatched) {
              matched = true;
              matchPos = m;
              skipShift = matchPos - nextPos;              
              tokenPositions[matchingTokens] = skipShift + 1;
            } else {
              matched |= false;                            
            }
            skipMatch = (skipMatch || matched) && !exceptionMatched;
          }

          //disallow exceptions that should match only current tokens          
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

      if (firstMatchToken + matchingTokens >= tokens.length) {
        matchingTokens = tokens.length - firstMatchToken;
      }

      if (firstMatchToken + skipShiftTotal + matchingTokens > tokens.length) {
        allElementsMatch = false;
      }

      if (allElementsMatch) {

        final String errMessage = formatMatches(tokens,
            tokenPositions, firstMatchToken, message);

        int correctedStPos = 0;
        if (startPositionCorrection > 0) {        
          for (int l = 0; l <= startPositionCorrection; l++) {
            correctedStPos +=  tokenPositions[l];
          }
          correctedStPos--;
        }        

        int correctedEndPos = 0;
        if (endPositionCorrection < 0) {
          int l = 0;
          while (l > endPositionCorrection) {
            correctedEndPos -= tokenPositions[matchingTokens + l - 1];
            l--;
          }
        }         

        AnalyzedTokenReadings firstMatchTokenObj = tokens[firstMatchToken + correctedStPos];
        boolean startsWithUppercase = 
          StringTools.startsWithUppercase(firstMatchTokenObj.getToken())
          && !matchConvertsCase();

        if (firstMatchTokenObj.isSentStart() && tokens.length > firstMatchToken + correctedStPos + 1) {
          // make uppercasing work also at sentence start: 
          firstMatchTokenObj = tokens[firstMatchToken + correctedStPos + 1];
          startsWithUppercase = StringTools.startsWithUppercase(firstMatchTokenObj.getToken());
        }
        int fromPos = tokens[firstMatchToken + correctedStPos]
                             .getStartPos();
        //FIXME: this is fishy, assumes that comma should always come before whitespace        
        if (errMessage.contains(SUGG_TAG + ",") && firstMatchToken + correctedStPos >= 1) {
          fromPos = tokens[firstMatchToken + correctedStPos - 1].getStartPos() 
          + tokens[firstMatchToken + correctedStPos - 1].getToken().length();          
        }

        final int toPos = tokens[lastMatchToken + correctedEndPos].getStartPos()
        + tokens[lastMatchToken + correctedEndPos].getToken().length();
        if (fromPos < toPos) { //this can happen with some skip="-1" when the last token is not matched
          final RuleMatch ruleMatch = new RuleMatch(this, fromPos, toPos, errMessage,
              shortMessage, startsWithUppercase);        
          ruleMatches.add(ruleMatch);        
        }
      } 
      firstMatchToken = -1;
      lastMatchToken = -1;
      skipShiftTotal = 0;
    }

    return ruleMatches.toArray(new RuleMatch[ruleMatches.size()]);
  }