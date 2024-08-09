@Test public void intersectTest() {
    final SeqType
        // function(xs:boolean) as xs:decimal?
        f = FuncType.get(DEC_ZO, BLN).seqType(),
        // function(xs:boolean) as xs:nonNegativeInteger
        f2 = FuncType.get(AtomType.NNI.seqType(), BLN).seqType(),
        // function(xs:boolean, xs:boolean) as xs:nonNegativeInteger
        f3 = FuncType.get(AtomType.NNI.seqType(), BLN, BLN).seqType(),
        // function(xs:integer) as xs:nonNegativeInteger
        f4 = FuncType.get(AtomType.NNI.seqType(), ITR).seqType(),
        // function(xs:boolean) as xs:integer
        f5 = FuncType.get(ITR, BLN).seqType(),
        // function(xs:boolean) as xs:boolean
        f6 = FuncType.get(BLN, BLN).seqType(),
        // map(xs:anyAtomicType, xs:integer)
        m = MapType.get(AtomType.AAT, ITR).seqType(),
        // map(xs:boolean, xs:integer)
        m2 = MapType.get(AtomType.BLN, ITR).seqType(),
        // map(xs:boolean, xs:nonNegativeInteger)
        m3 = MapType.get(AtomType.BLN, AtomType.NNI.seqType()).seqType(),
        // map(xs:integer, xs:integer)
        m4 = MapType.get(AtomType.ITR, ITR).seqType();

    intersect(get(AtomType.ITEM, 0), ITEM, null);
    intersect(ATT, ATT, ATT);
    intersect(ATT, NOD, ATT);
    intersect(ATT, ELM, null);
    intersect(NOD, ITR, null);
    intersect(f, ITR, null);
    intersect(f, f, f);
    intersect(f, f2, f2);
    intersect(m, f, m);
    intersect(f, f5, f5);
    intersect(f, f4, FuncType.get(AtomType.NNI.seqType(), AAT).seqType());
    intersect(f2, f3, null);
    intersect(f5, f6, null);
    intersect(m, ITEM, m);
    intersect(m, ITR, null);
    intersect(m, m2, m);
    intersect(m, m3, MapType.get(AtomType.AAT, AtomType.NNI.seqType()).seqType());
    intersect(m2, m4, m);
    intersect(m2, MapType.get(AtomType.BLN, BLN).seqType(), null);
    intersect(m, FUN_O, m);
    intersect(m, f, m);
    intersect(m4, f5, m);
    intersect(m, f3, null);
    intersect(m, f6, null);
    intersect(m, FuncType.get(ITR, ITEM).seqType(), null);
  }