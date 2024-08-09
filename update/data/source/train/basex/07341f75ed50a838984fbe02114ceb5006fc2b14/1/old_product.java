private byte[] string() throws QueryException {
    if(!consume('"')) throw error("Expected string, found '%'", curr());
    tb.reset();
    while(ip < il) {
      final int c = consume();
      if(c == '"') {
        skipWs();
        return tb.finish();
      }

      if(c == '\\') {
        final int n = consume();
        switch(n) {
          case '/':
          case '\\':
          case '"':
            tb.addByte((byte) n);
            break;
          case 'b':
            tb.addByte((byte) '\b');
            break;
          case 'f':
            tb.addByte((byte) '\f');
            break;
          case 't':
            tb.addByte((byte) '\t');
            break;
          case 'r':
            tb.addByte((byte) '\r');
            break;
          case 'n':
            tb.addByte((byte) '\n');
            break;
          case 'u':
            if(ip + 4 >= il) throw eof(", expected four-digit hex value");
            int cp = 0;
            for(int i = 0; i < 4; i++) {
              final char x = consume();
              if(x >= '0' && x <= '9')      cp = 16 * cp + x      - '0';
              else if(x >= 'a' && x <= 'f') cp = 16 * cp + x + 10 - 'a';
              else if(x >= 'A' && x <= 'F') cp = 16 * cp + x + 10 - 'A';
              else throw error("Illegal hexadecimal digit: '%'", x);
            }
            tb.add(cp);
            break;
          default:
            throw error("Unknown character escape: '\\%'", n);
        }
      } else if(spec != Spec.LIBERAL && c <= 0x1F) {
        throw error("Unescaped control character: '\\%'", CTRL[c]);
      } else {
        tb.add(c);
      }
    }
    throw eof(" in string literal");
  }