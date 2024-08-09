public void add(Term term) {
      assert lastTerm.equals(new Term("")) || term.compareTo(lastTerm) > 0;

      try {
        int prefix = sharedPrefix(lastTerm.bytes, term.bytes);
        int suffix = term.bytes.length - prefix;
        if (term.field.equals(lastTerm.field)) {
          output.writeVInt(prefix << 1);
        } else {
          output.writeVInt(prefix << 1 | 1);
          output.writeString(term.field);
        }
        output.writeVInt(suffix);
        output.writeBytes(term.bytes.bytes, term.bytes.offset + prefix, suffix);
        lastTerm.bytes.copy(term.bytes);
        lastTerm.field = term.field;
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }