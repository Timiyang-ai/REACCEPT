public boolean add(CharSequence input, CharSequence output) {
      final int length = input.length();
      if (ignoreCase) {
        // convert on the fly to lowercase
        charsSpare.grow(length);
        final char[] buffer = charsSpare.chars();
        for (int i = 0; i < length; ) {
            i += Character.toChars(
                    Character.toLowerCase(
                        Character.codePointAt(input, i)), buffer, i);
        }
        spare.copyChars(buffer, 0, length);
      } else {
        spare.copyChars(input, 0, length);
      }
      if (hash.add(spare.get()) >= 0) {
        outputValues.add(output);
        return true;
      }
      return false;
    }