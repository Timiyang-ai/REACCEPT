private byte[] string() throws QueryIOException {
    if(!consume('"')) throw error("Expected string, found '%'", curr());
    tb.reset();
    char high = 0; // cached high surrogate
    while(pos < length) {
      final int p = pos;
      int ch = consume();
      // string is closed..
      if(ch == '"') {
        // unpaired surrogate?
        if(high != 0) add(high, pos - 7, p);
        skipWs();
        return tb.toArray();
      }

      // escape sequence
      if(ch == '\\') {
        ch = consume();
        switch(ch) {
          case '\\':
          case '/':
          case '"':
            break;
          case 'b':
            ch = '\b';
            break;
          case 'f':
            ch = '\f';
            break;
          case 'n':
            ch = '\n';
            break;
          case 'r':
            ch = '\r';
            break;
          case 't':
            ch = '\t';
            break;
          case 'u':
            if(pos + 4 >= length) throw eof(", expected four-digit hex value");
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
            throw error("Unknown character escape: '\\%'", ch);
        }
      } else if(!liberal && ch <= 0x1F) {
        throw error("Non-escaped control character: '\\%'", CTRL[ch]);
      }

      if(high != 0) {
        if(ch >= 0xDC00 && ch <= 0xDFFF) {
          // compute resulting codepoint
          ch = (high - 0xD800 << 10) + ch - 0xDC00 + 0x10000;
        } else if(escape) {
          // add invalid high surrogate, treat low surrogate as next character
          add(high, p, pos);
        }
        high = 0;
      }

      if(ch >= 0xD800 && ch <= 0xDBFF) {
        // remember high surrogate
        high = (char) ch;
      } else {
        add(ch, p, pos);
      }
    }
    throw eof(" in string literal");
  }