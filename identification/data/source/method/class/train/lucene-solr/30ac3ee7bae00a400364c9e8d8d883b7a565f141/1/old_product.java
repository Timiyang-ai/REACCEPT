public boolean add(CharSequence input, CharSequence output) {
      final int length = input.length();
      if (ignoreCase) {
        // convert on the fly to lowercase
        charsSpare.grow(length);
        final char[] buffer = charsSpare.chars;
        for (int i = 0; i < length; ) {
            i += Character.toChars(
                    Character.toLowerCase(
                        Character.codePointAt(input, i)), buffer, i);
        }
        UnicodeUtil.UTF16toUTF8(buffer, 0, length, spare);
      } else {
        UnicodeUtil.UTF16toUTF8(input, 0, length, spare);
      }
      if (hash.add(spare) >= 0) {
        outputValues.add(output);
        return true;
      }
      return false;
    }