private byte[] string() throws QueryIOException {
    if(!consume('"')) throw error("Expected string, found '%'", curr());
    tb.reset();
    char high = 0; // cached high surrogate
    while(pos < length) {
      final int p = pos;
      int ch = consume();
      if(ch == '"') {
        if(high != 0) add(high, pos - 7, p);
        skipWs();
        return tb.toArray();
      }

      if(ch == '\\') {
        if(escape) {
          if(high != 0) {
            tb.add(high);
            high = 0;
          }
          tb.add('\\');
        }

        final int n = consume();
        switch(n) {
          case '/':
          case '\\':
          case '"':
            ch = n;
            break;
          case 'b':
            ch = escape ? 'b' : '\b';
            break;
          case 'f':
            ch = escape ? 'f' : '\f';
            break;
          case 't':
            ch = escape ? 't' : '\t';
            break;
          case 'r':
            ch = escape ? 'r' : '\r';
            break;
          case 'n':
            ch = escape ? 'n' : '\n';
            break;
          case 'u':
            if(pos + 4 >= length) throw eof(", expected four-digit hex value");
            if(escape) {
              tb.add('u');
              for(int i = 0; i < 4; i++) {
                final char x = consume();
                if(x >= '0' && x <= '9' || x >= 'a' && x <= 'f' || x >= 'A' && x <= 'F') {
                  tb.add(x);
                } else throw error("Illegal hexadecimal digit: '%'", x);
              }
              continue;
            }
            ch = 0;
            for(int i = 0; i < 4; i++) {
              final char x = consume();
              if(x >= '0' && x <= '9')      ch = 16 * ch + x      - '0';
              else if(x >= 'a' && x <= 'f') ch = 16 * ch + x + 10 - 'a';
              else if(x >= 'A' && x <= 'F') ch = 16 * ch + x + 10 - 'A';
              else throw error("Illegal hexadecimal digit: '%'", x);
            }
            break;
          default:
            throw error("Unknown character escape: '\\%'", n);
        }
      } else if(!liberal && ch <= 0x1F) {
        throw error("Non-escaped control character: '\\%'", CTRL[ch]);
      }

      if(high != 0) {
        if(ch >= 0xDC00 && ch <= 0xDFFF) ch = (high - 0xD800 << 10) + ch - 0xDC00 + 0x10000;
        else add(high, p, pos);
        high = 0;
      }

      if(ch >= 0xD800 && ch <= 0xDBFF) {
        high = (char) ch;
      } else {
        add(ch, p, pos);
      }
    }
    throw eof(" in string literal");
  }