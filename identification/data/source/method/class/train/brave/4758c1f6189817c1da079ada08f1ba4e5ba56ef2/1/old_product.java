static String writeB3SingleFormat(TraceContext context) {
    char[] result = getCharBuffer();
    int pos = 0;
    long traceIdHigh = context.traceIdHigh();
    if (traceIdHigh != 0L) {
      writeHexLong(result, pos, traceIdHigh);
      pos += 16;
    }
    writeHexLong(result, pos, context.traceId());
    pos += 16;
    result[pos++] = '-';
    writeHexLong(result, pos, context.spanId());
    pos += 16;

    Boolean sampled = context.sampled();
    if (sampled != null) {
      result[pos++] = '-';
      result[pos++] = sampled ? '1' : '0';
    }

    long b3Id = context.parentIdAsLong();
    if (b3Id != 0) {
      result[pos++] = '-';
      writeHexLong(result, pos, b3Id);
      pos += 16;
    }

    if (context.debug()) {
      result[pos++] = '-';
      result[pos++] = '1';
    }
    return new String(result, 0, pos);
  }