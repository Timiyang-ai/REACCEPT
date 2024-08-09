static void skipToNextPage(ExtractorInput input)
      throws IOException, InterruptedException {

    byte[] buffer = new byte[2048];
    int peekLength = buffer.length;
    long length = input.getLength();
    while (true) {
      if (length != C.LENGTH_UNBOUNDED && input.getPosition() + peekLength > length) {
        // Make sure to not peek beyond the end of the input.
        peekLength = (int) (length - input.getPosition());
        if (peekLength < 4) {
          // Not found until eof.
          throw new EOFException();
        }
      }
      input.peekFully(buffer, 0, peekLength, false);
      for (int i = 0; i < peekLength - 3; i++) {
        if (buffer[i] == 'O' && buffer[i + 1] == 'g' && buffer[i + 2] == 'g'
            && buffer[i + 3] == 'S') {
          // Match! Skip to the start of the pattern.
          input.skipFully(i);
          return;
        }
      }
      // Overlap by not skipping the entire peekLength.
      input.skipFully(peekLength - 3);
    }
  }