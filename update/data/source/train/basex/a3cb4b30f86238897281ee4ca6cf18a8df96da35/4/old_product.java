private byte[] string() throws QueryIOException {
    if(!consume('"')) throw error("Expected string, found '%'", curr());
    tb.reset();
    char hi = 0; // cached high surrogate
    while(pos < length) {
      int ch = consume();
      if(ch == '"') {
        if(hi != 0) tb.add(hi);
        skipWs();
        return tb.toArray();
      }

      if(ch == '\\') {
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
            ch = n;
            break;
          case 'b':
            ch = unescape ? '\b' : 'b';
            break;
          case 'f':
            ch = unescape ? '\f' : 'f';
            break;
          case 't':
            ch = unescape ? '\t' : 't';
            break;
          case 'r':
            ch = unescape ? '\r' : 'r';
            break;
          case 'n':
            ch = unescape ? '\n' : 'n';
            break;
          case 'u':
            if(pos + 4 >= length) throw eof(", expected four-digit hex value");
            if(unescape) {
              ch = 0;
              for(int i = 0; i < 4; i++) {
                final char x = consume();
                if(x >= '0' && x <= '9')      ch = 16 * ch + x      - '0';
                else if(x >= 'a' && x <= 'f') ch = 16 * ch + x + 10 - 'a';
                else if(x >= 'A' && x <= 'F') ch = 16 * ch + x + 10 - 'A';
                else throw error("Illegal hexadecimal digit: '%'", x);
              }
            } else {
              tb.addByte((byte) 'u');
              for(int i = 0; i < 4; i++) {
                final char x = consume();
                if(x >= '0' && x <= '9' || x >= 'a' && x <= 'f' || x >= 'A' && x <= 'F') {
                  if(i < 3) tb.addByte((byte) x);
                  else ch = x;
                } else throw error("Illegal hexadecimal digit: '%'", x);
              }
            }
            break;
          default:
            throw error("Unknown character escape: '\\%'", n);
        }
      } else if(spec != JsonSpec.LIBERAL && ch <= 0x1F) {
        throw error("Non-escaped control character: '\\%'", CTRL[ch]);
      }

      if(hi != 0) {
        if(ch >= 0xDC00 && ch <= 0xDFFF)
          ch = (hi - 0xD800 << 10) + ch - 0xDC00 + 0x10000;
        else tb.add(hi);
        hi = 0;
      }

      if(ch >= 0xD800 && ch <= 0xDBFF) hi = (char) ch;
      else tb.add(ch);
    }
    throw eof(" in string literal");
  }