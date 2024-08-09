private byte[] number() throws QueryIOException {
    tb.reset();

    // integral part
    int c = consume();
    tb.addByte((byte) c);
    if(c == '-') {
      c = consume();
      if(c < '0' || c > '9') throw error("Number expected after '-'");
      tb.addByte((byte) c);
    }

    final boolean zero = c == '0';
    c = curr();
    if(zero && c >= '0' && c <= '9') throw error("No digit allowed after '0'");
    loop: while(true) {
      switch(c) {
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
          tb.addByte((byte) c);
          pos++;
          c = curr();
          break;
        case '.':
        case 'e':
        case 'E':
          break loop;
        default:
          skipWs();
          return tb.finish();
      }
    }

    if(consume('.')) {
      tb.addByte((byte) '.');
      c = curr();
      if(c < '0' || c > '9') throw error("Number expected after '.'");
      do {
        tb.addByte((byte) c);
        pos++;
        c = curr();
      } while(c >= '0' && c <= '9');
      if(c != 'e' && c != 'E') {
        skipWs();
        return tb.finish();
      }
    }

    // 'e' or 'E'
    tb.addByte((byte) consume());
    c = curr();
    if(c == '-' || c == '+') {
      tb.addByte((byte) consume());
      c = curr();
    }

    if(c < '0' || c > '9') throw error("Exponent expected");
    do tb.addByte((byte) consume());
    while((c = curr()) >= '0' && c <= '9');
    skipWs();
    return tb.finish();
  }