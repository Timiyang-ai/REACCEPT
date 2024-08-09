public static String writeB3SingleFormat(TraceContext context) {
    char[] buffer = getCharBuffer();
    int length = writeB3SingleFormat(context, context.parentIdAsLong(), buffer);
    return new String(buffer, 0, length);
  }