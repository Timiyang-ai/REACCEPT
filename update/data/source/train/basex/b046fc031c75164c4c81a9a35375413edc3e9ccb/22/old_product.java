private Item dtd(final QueryContext qc) throws QueryException {
    final Value seq = dtdInfo(qc);
    if(seq == Empty.SEQ) return null;
    throw BXVA_FAIL.get(info, seq.iter().next());
  }