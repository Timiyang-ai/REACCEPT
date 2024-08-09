private byte[] string() throws QueryIOException {
    if(!consume('"')) throw error("Expected string, found '%'", curr());
    tb.reset();
    char hi = 0; // cached high surrogate
    while(pos < length) {
      int cp = consume();
      if(cp == '"') {
        if(hi != 0) tb.add(hi);
        skipWs();
        return tb.finish();
      }

      if(cp == '\\') {
        if(!unescape) {
          if(hi != 0) {
            tb.add(hi);
            hi = 0;
          }
          tb.addByte((byte) '\\');
        }

        final int n = consume();
        switch(n) {
          case '/':
          case '\\':
          case '"':
            cp = n;
            break;
          case 'b':
            cp = unescape ? '\b' : 'b';
            break;
          case 'f':
            cp = unescape ? '\f' : 'f';
            break;
          case 't':
            cp = unescape ? '\t' : 't';
            break;
          case 'r':
            cp = unescape ? '\r' : 'r';
            break;
          case 'n':
            cp = unescape ? '\n' : 'n';
            break;
          case 'u':
            if(pos + 4 >= length) throw eof(", expected four-digit hex value");
            if(unescape) {
              cp = 0;
              for(int i = 0; i < 4; i++) {
                final char x = consume();
                if(x >= '0' && x <= '9')      cp = 16 * cp + x      - '0';
                else if(x >= 'a' && x <= 'f') cp = 16 * cp + x + 10 - 'a';
                else if(x >= 'A' && x <= 'F') cp = 16 * cp + x + 10 - 'A';
                else throw error("Illegal hexadecimal digit: '%'", x);
              }
            } else {
              tb.addByte((byte) 'u');
              for(int i = 0; i < 4; i++) {
                final char x = consume();
                if(x >= '0' && x <= '9' || x >= 'a' && x <= 'f' || x >= 'A' && x <= 'F') {
                  if(i < 3) tb.addByte((byte) x);
                  else cp = x;
                } else throw error("Illegal hexadecimal digit: '%'", x);
              }
            }
            break;
          default:
            throw error("Unknown character escape: '\\%'", n);
        }
      } else if(spec != JsonSpec.LIBERAL && cp <= 0x1F) {
        throw error("Non-escaped control character: '\\%'", CTRL[cp]);
      }

      if(hi != 0) {
        if(cp >= 0xDC00 && cp <= 0xDFFF)
          cp = (hi - 0xD800 << 10) + cp - 0xDC00 + 0x10000;
        else tb.add(hi);
        hi = 0;
      }

      if(cp >= 0xD800 && cp <= 0xDBFF) hi = (char) cp;
      else tb.add(cp);
    }
    throw eof(" in string literal");
  }