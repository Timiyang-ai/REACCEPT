private byte[] number() throws QueryIOException {
    tb.reset();

    // integral part
    int ch = consume();
    add(ch);
    if(ch == '-') {
      ch = consume();
      if(ch < '0' || ch > '9') throw error("Number expected after '-'");
      add(ch);
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
          add(ch);
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
      add('.');
      ch = curr();
      if(ch < '0' || ch > '9') throw error("Number expected after '.'");
      do {
        add(ch);
        pos++;
        ch = curr();
      } while(ch >= '0' && ch <= '9');
      if(ch != 'e' && ch != 'E') {
        skipWs();
        return tb.toArray();
      }
    }

    // 'e' or 'E'
    add(consume());
    ch = curr();
    if(ch == '-' || ch == '+') {
      add(consume());
      ch = curr();
    }

    if(ch < '0' || ch > '9') throw error("Exponent expected");
    do add(consume());
    while((ch = curr()) >= '0' && ch <= '9');
    skipWs();
    return tb.toArray();
  }