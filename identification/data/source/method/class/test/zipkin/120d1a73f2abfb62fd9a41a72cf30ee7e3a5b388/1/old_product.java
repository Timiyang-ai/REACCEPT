public byte[] writeList(List<Span> spans) {
    int lengthOfSpans = spans.size();
    if (lengthOfSpans == 0) return EMPTY_ARRAY;
    if (lengthOfSpans == 1) return write(spans.get(0));

    int sizeInBytes = 0;
    int[] sizeOfValues = new int[lengthOfSpans];
    for (int i = 0; i < lengthOfSpans; i++) {
      int sizeOfValue = sizeOfValues[i] = SPAN.sizeOfValue(spans.get(i));
      sizeInBytes += sizeOfLengthDelimitedField(sizeOfValue);
    }
    byte[] result = new byte[sizeInBytes];
    WriteBuffer writeBuffer = WriteBuffer.wrap(result, 0);
    for (int i = 0; i < lengthOfSpans; i++) {
      writeSpan(spans.get(i), sizeOfValues[i], writeBuffer);
    }
    return result;
  }