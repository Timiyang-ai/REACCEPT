int literal() throws QueryException {
    final int ch = next();
    if(ch == '[') { // check begin of variable marker
      if(!more()) PICDATE.thrw(info, token); // [$
      if(!consume('[')) return -1; // [...
    } else if(ch == ']') { // check end of variable marker
      if(!consume(']')) PICDATE.thrw(info, token); // ]$ or ]...
    }
    return ch;
  }