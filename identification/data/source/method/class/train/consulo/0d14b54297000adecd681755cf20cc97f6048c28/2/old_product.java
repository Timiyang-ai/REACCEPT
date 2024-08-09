@NotNull
  static Context parse(@NotNull CharSequence text, int startOffset, int endOffset, int offset) {
    int asteriskOffset = StringUtil.indexOf(text, '*', startOffset, endOffset);
    if (asteriskOffset < 0) {
      return NOT_MATCHED_CONTEXT;
    }
    
    startOffset = asteriskOffset + 1;

    int startTagStartOffset = -1;
    int startTagEndOffset = -1;
    Set<CharSequence> closedTags = new HashSet<CharSequence>();
    CharSequence startTag = null;
    
    // Try to find start tag to the left of the given offset.
    for (int i = offset - 1; i >= startOffset; i--) {
      char c = text.charAt(i);
      if (c == ' ' || c == '\t') {
        continue;
      }
      
      if (c == '>') {
        if (startTagEndOffset < 0) {
          startTagEndOffset = i;
          continue;
        }
      }
      
      if (c == '<') {
        if (startTagEndOffset < 0 || i >= endOffset) {  // We are inside the tag.
          break;
        }
        
        if (text.charAt(i + 1) == '/') {
          CharSequence tag = text.subSequence(i + 2, startTagEndOffset);
          closedTags.add(tag);
          startTagEndOffset = -1;
        }
        else {
          CharSequence tag = text.subSequence(i + 1, startTagEndOffset);
          if (closedTags.remove(tag)) {
            startTagEndOffset = -1;
            continue;
          }
          startTagStartOffset = i;
          startTag = text.subSequence(i + 1, startTagEndOffset + 1);
          break;
        }
      }
    }
    
    if (startTagStartOffset < 0 || startTagEndOffset < 0) {
      return NOT_MATCHED_CONTEXT;
    }
    
    int endTagStartOffset = -1;
    
    // Try to find closing tag at or after the given offset.
    for (int i = offset; i < endOffset; i++) {
      char c = text.charAt(i);
      if (c == '<' && i < endOffset && text.charAt(i + 1) == '/' && startTag != null 
          && CharArrayUtil.regionMatches(text, i + 2, endOffset, startTag)) 
      {
        endTagStartOffset = i;
        break;
      }
    }
    
    
    return new Context(text, startTagEndOffset, endTagStartOffset, offset);
  }