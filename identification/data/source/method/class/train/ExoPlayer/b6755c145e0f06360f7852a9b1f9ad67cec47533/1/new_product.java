boolean skipToNextPage(ExtractorInput input, long limit)
      throws IOException, InterruptedException {
    limit = Math.min(limit + 3, endPosition);
    byte[] buffer = new byte[2048];
    int peekLength = buffer.length;
    while (true) {
      if (input.getPosition() + peekLength > limit) {
        // Make sure to not peek beyond the end of the input.
        peekLength = (int) (limit - input.getPosition());
        if (peekLength < 4) {
          // Not found until end.
          return false;
        }
      }
      input.peekFully(buffer, 0, peekLength, false);
      for (int i = 0; i < peekLength - 3; i++) {
        if (buffer[i] == 'O'
            && buffer[i + 1] == 'g'
            && buffer[i + 2] == 'g'
            && buffer[i + 3] == 'S') {
          // Match! Skip to the start of the pattern.
          input.skipFully(i);
          return true;
        }
      }
      // Overlap by not skipping the entire peekLength.
      input.skipFully(peekLength - 3);
    }
  }