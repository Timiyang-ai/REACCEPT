static String generate(
      List<AsciiTableAlignment> alignments,
      List<String> headers,
      List<? extends List<String>> cells) {

    int colCount = alignments.size();
    int rowCount = cells.size();
    ArgChecker.isTrue(
        headers.size() == colCount,
        "Number of headers {} must match number of alignments {}", headers.size(), colCount);

    // find max length of each column
    int[] colLengths = new int[colCount];
    for (int colIdx = 0; colIdx < colCount; colIdx++) {
      colLengths[colIdx] = headers.get(colIdx).length();
    }
    for (int rowIdx = 0; rowIdx < rowCount; rowIdx++) {
      List<String> row = cells.get(rowIdx);
      ArgChecker.isTrue(
          row.size() == colCount,
          "Table of cells has incorrect number of columns {} in row {}", row.size(), rowIdx);
      for (int colIdx = 0; colIdx < colCount; colIdx++) {
        colLengths[colIdx] = Math.max(colLengths[colIdx], row.get(colIdx).length());
      }
    }
    int colTotalLength = 3;  // allow for last vertical separator and windows line separator
    for (int colIdx = 0; colIdx < colCount; colIdx++) {
      colTotalLength += colLengths[colIdx] + 3;  // each column has two spaces and a vertical separator
    }

    // write table
    StringBuilder buf = new StringBuilder((rowCount + 3) * colTotalLength);
    writeSeparatorLine(buf, colLengths);
    writeDataLine(buf, colLengths, alignments, headers);
    writeSeparatorLine(buf, colLengths);
    for (int rowIdx = 0; rowIdx < rowCount; rowIdx++) {
      writeDataLine(buf, colLengths, alignments, cells.get(rowIdx));
    }
    writeSeparatorLine(buf, colLengths);
    return buf.toString();
  }