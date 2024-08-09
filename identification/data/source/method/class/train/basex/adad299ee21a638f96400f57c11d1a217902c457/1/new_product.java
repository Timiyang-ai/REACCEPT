public HTTPAccept[] accepts() {
    final String accept = req.getHeader(ACCEPT);
    if(accept == null) return new HTTPAccept[0];

    final ArrayList<HTTPAccept> list = new ArrayList<>();
    for(final String produce : accept.split("\\s*,\\s*")) {
      final HTTPAccept acc = new HTTPAccept();
      // check if quality factor was specified
      final Matcher m = QF.matcher(produce);
      if(m.find()) {
        acc.type = m.group(1);
        acc.qf = Token.toDouble(Token.token(m.group(2)));
      } else {
        acc.type = produce;
      }
      // only accept valid double values
      if(acc.qf > 0 && acc.qf <= 1) list.add(acc);
    }
    return list.toArray(new HTTPAccept[list.size()]);
  }