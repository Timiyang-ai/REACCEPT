static String generate(
      List<AsciiTableAlignment> alignments,
      List<String> headers,
      List<? extends List<String>> cells) {

    int colCount = alignments.size();

    // find max length of each column
    int[] colLengths = new int[colCount];
    for (int colIdx = 0; colIdx < colCount; colIdx++) {
      colLengths[colIdx] = headers.get(colIdx).length();
    }
    for (List<String> row : cells) {
      for (int colIdx = 0; colIdx < colCount; colIdx++) {
        colLengths[colIdx] = Math.max(colLengths[colIdx], row.get(colIdx).length());
      }
    }

    // write table
    StringBuilder buf = new StringBuilder(2048);
    writeSeparatorLine(buf, colLengths);
    writeDataLine(buf, colLengths, alignments, headers);
    writeSeparatorLine(buf, colLengths);
    for (int rowIdx = 0; rowIdx < cells.size(); rowIdx++) {
      writeDataLine(buf, colLengths, alignments, cells.get(rowIdx));
    }
    writeSeparatorLine(buf, colLengths);
    return buf.toString();
  }