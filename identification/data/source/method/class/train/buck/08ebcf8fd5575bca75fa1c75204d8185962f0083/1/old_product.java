public static String escapeJsonForStringEmbedding(String input) {
    StringBuilder builder = new StringBuilder(input.length());
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      if (c > 0x7f || outputEscapes[c] == 0) {
        builder.append(c);
      } else if (outputEscapes[c] == -1) {
        builder.append('\\').append('u').append('0').append('0');
        if (c < 0x10) {
          builder.append('0');
        }
        builder.append(Integer.toHexString(c));
      } else {
        builder.append('\\').append((char) outputEscapes[c]);
      }
    }

    return builder.toString();
  }