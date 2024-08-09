@Test public void unionTest() {
    assertTrue(STR.union(ITR).eq(AAT));
    assertTrue(STR.union(STR).eq(STR));
    assertTrue(STR.union(ATT).eq(ITEM));
    assertTrue(AtomType.NST.seqType().union(STR).eq(STR));
    assertTrue(STR.union(AtomType.NST.seqType()).eq(STR));
    assertTrue(STR.union(AtomType.JAVA.seqType()).eq(ITEM));

    assertTrue(ATT.union(ELM).eq(NOD));
    assertTrue(NOD.union(ELM).eq(NOD));
    assertTrue(ELM.union(NOD).eq(NOD));
    assertTrue(ELM.union(ELM).eq(ELM));
    assertTrue(ELM.union(STR).eq(ITEM));

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
        // map(xs:anyAtomicType, xs:integer)
        m = MapType.get(AtomType.AAT, ITR).seqType(),
        // map(xs:boolean, xs:integer)
        m2 = MapType.get(AtomType.BLN, ITR).seqType(),
        // map(xs:boolean, xs:nonNegativeInteger)
        m3 = MapType.get(AtomType.BLN, AtomType.NNI.seqType()).seqType(),
        // map(xs:integer, xs:integer)
        m4 = MapType.get(AtomType.ITR, ITR).seqType();
    union(f, ITR, ITEM);
    union(f, FUN_O, FUN_O);
    union(f2, f3, FUN_O);
    union(f2, f4, FUN_O);
    union(MAP_O, m, MAP_O);
    union(m, ITR, ITEM);
    union(m, f, f);
    union(m, f2, f5);
    union(m, m2, m2);
    union(m, m3, m2);
    union(m2, m4, FUN_O);
  }