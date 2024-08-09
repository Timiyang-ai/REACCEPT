void addMapping(Range nextRange, String javaName) {
    SourceInfo nextInfo = nextRange.getSourceInfo();
    if (!canMerge(nextRange, nextInfo, javaName)) {
      flush(null);
    }

    if (empty) {
      // Start a new range.
      javaFile = nextInfo.getFileName();
      javaLine = nextInfo.getStartLine();
      startLine = nextRange.getStartLine();
      startColumn = nextRange.getStartColumn();
      endLine = nextRange.getEndLine();
      endColumn = nextRange.getEndColumn();
      empty = false;

      if (javaName != null) {
        flush(javaName); // Don't merge mappings with Java names.
      }

      return;
    }

    // Merge with the buffer by adjusting the end of the JavaScript range if needed.
    // (It's rarely needed because the range of a Java statement usually comes before
    // any subexpressions within that statement, and there is rarely more than one Java
    // statement per line.)

    int nextEndLine = nextRange.getEndLine();
    if (nextEndLine < endLine) {
      return; // The multi-line range in the buffer already covers it.
    }

    int nextEndColumn = nextRange.getEndColumn();
    if (nextEndLine == endLine && nextEndColumn <= endColumn) {
      return; // The range in the buffer already covers it.
    }

    endLine = nextEndLine;
    endColumn = nextEndColumn;
  }