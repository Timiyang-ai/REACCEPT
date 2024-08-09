static void parseCueText(String id, String markup, WebvttCue.Builder builder,
      List<WebvttCssStyle> styles) {
    SpannableStringBuilder spannedText = new SpannableStringBuilder();
    Stack<StartTag> startTagStack = new Stack<>();
    List<StyleMatch> scratchStyleMatches = new ArrayList<>();
    int pos = 0;
    while (pos < markup.length()) {
      char curr = markup.charAt(pos);
      switch (curr) {
        case CHAR_LESS_THAN:
          if (pos + 1 >= markup.length()) {
            pos++;
            break; // avoid ArrayOutOfBoundsException
          }
          int ltPos = pos;
          boolean isClosingTag = markup.charAt(ltPos + 1) == CHAR_SLASH;
          pos = findEndOfTag(markup, ltPos + 1);
          boolean isVoidTag = markup.charAt(pos - 2) == CHAR_SLASH;
          String fullTagExpression = markup.substring(ltPos + (isClosingTag ? 2 : 1),
              isVoidTag ? pos - 2 : pos - 1);
          String tagName = getTagName(fullTagExpression);
          if (tagName == null || !isSupportedTag(tagName)) {
            continue;
          }
          if (isClosingTag) {
            StartTag startTag;
            do {
              if (startTagStack.isEmpty()) {
                break;
              }
              startTag = startTagStack.pop();
              applySpansForTag(id, startTag, spannedText, styles, scratchStyleMatches);
            } while(!startTag.name.equals(tagName));
          } else if (!isVoidTag) {
            startTagStack.push(StartTag.buildStartTag(fullTagExpression, spannedText.length()));
          }
          break;
        case CHAR_AMPERSAND:
          int semiColonEnd = markup.indexOf(CHAR_SEMI_COLON, pos + 1);
          int spaceEnd = markup.indexOf(CHAR_SPACE, pos + 1);
          int entityEnd = semiColonEnd == -1 ? spaceEnd
              : spaceEnd == -1 ? semiColonEnd : Math.min(semiColonEnd, spaceEnd);
          if (entityEnd != -1) {
            applyEntity(markup.substring(pos + 1, entityEnd), spannedText);
            if (entityEnd == spaceEnd) {
              spannedText.append(" ");
            }
            pos = entityEnd + 1;
          } else {
            spannedText.append(curr);
            pos++;
          }
          break;
        default:
          spannedText.append(curr);
          pos++;
          break;
      }
    }
    // apply unclosed tags
    while (!startTagStack.isEmpty()) {
      applySpansForTag(id, startTagStack.pop(), spannedText, styles, scratchStyleMatches);
    }
    applySpansForTag(id, StartTag.buildWholeCueVirtualTag(), spannedText, styles,
        scratchStyleMatches);
    builder.setText(spannedText);
  }