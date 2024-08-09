private Item xsd(final QueryContext qc) throws QueryException {
    final Value seq = xsdInfo(qc);
    if(seq == Empty.SEQ) return null;
    throw BXVA_FAIL_X.get(info, seq.iter().next());
  }