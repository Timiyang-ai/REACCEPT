private byte[] number() throws QueryIOException {
    tb.reset();

    // integral part
    int ch = consume();
    tb.addByte((byte) ch);
    if(ch == '-') {
      ch = consume();
      if(ch < '0' || ch > '9') throw error("Number expected after '-'");
      tb.addByte((byte) ch);
    }

    final boolean zero = ch == '0';
    ch = curr();
    if(zero && ch >= '0' && ch <= '9') throw error("No digit allowed after '0'");
    loop: while(true) {
      switch(ch) {
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
          tb.addByte((byte) ch);
          pos++;
          ch = curr();
          break;
        case '.':
        case 'e':
        case 'E':
          break loop;
        default:
          skipWs();
          return tb.toArray();
      }
    }

    if(consume('.')) {
      tb.addByte((byte) '.');
      ch = curr();
      if(ch < '0' || ch > '9') throw error("Number expected after '.'");
      do {
        tb.addByte((byte) ch);
        pos++;
        ch = curr();
      } while(ch >= '0' && ch <= '9');
      if(ch != 'e' && ch != 'E') {
        skipWs();
        return tb.toArray();
      }
    }

    // 'e' or 'E'
    tb.addByte((byte) consume());
    ch = curr();
    if(ch == '-' || ch == '+') {
      tb.addByte((byte) consume());
      ch = curr();
    }

    if(ch < '0' || ch > '9') throw error("Exponent expected");
    do tb.addByte((byte) consume());
    while((ch = curr()) >= '0' && ch <= '9');
    skipWs();
    return tb.toArray();
  }