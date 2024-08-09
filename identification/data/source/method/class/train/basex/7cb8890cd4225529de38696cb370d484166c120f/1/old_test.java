@Test public void instanceOfTest() {
    assertTrue(BLN.instanceOf(AAT_ZM));
    assertFalse(AAT_ZM.instanceOf(BLN));
    assertTrue(DBL.instanceOf(DBL_ZM));
    assertFalse(DBL_ZM.instanceOf(DBL));

    // functions
    final SeqType f = FuncType.get(DEC_ZO, BLN).seqType();
    assertFalse(f.instanceOf(ITR));
    assertTrue(f.instanceOf(ITEM));
    assertTrue(f.instanceOf(f));
    assertTrue(f.instanceOf(FUN_OZ));
    assertFalse(FUN_O.instanceOf(f));
    assertFalse(f.instanceOf(FuncType.get(DEC_ZO, BLN, ITR).seqType()));
    assertFalse(f.instanceOf(FuncType.get(DEC_ZO, AAT).seqType()));
    assertFalse(f.instanceOf(FuncType.get(BLN, BLN).seqType()));

    // maps
    final SeqType m = MapType.get(AtomType.AAT, ITR).seqType();
    assertTrue(m.instanceOf(f));
    assertFalse(f.instanceOf(m));
    assertFalse(MAP_O.instanceOf(m));
    assertTrue(m.instanceOf(MAP_O));
    assertFalse(m.instanceOf(MapType.get(AtomType.AAT, BLN).seqType()));
    assertFalse(MapType.get(AtomType.BLN, ITR).seqType().instanceOf(m));

    /* arrays
    final SeqType a = ArrayType.get(ITR).seqType();
    assertTrue(a.instanceOf(f));
    assertFalse(f.instanceOf(a));
    assertFalse(ARRAY_O.instanceOf(a));
    assertTrue(a.instanceOf(ARRAY_O));
    assertFalse(a.instanceOf(ArrayType.get(BLN).seqType()));
    assertFalse(ArrayType.get(ITR).seqType().instanceOf(a));
    */

    // nodes
    assertTrue(ATT.instanceOf(NOD));
    assertTrue(ATT.instanceOf(ATT));
    assertFalse(ATT.instanceOf(ELM));
    assertFalse(ELM.instanceOf(f));
    assertFalse(NOD.instanceOf(ELM));
    assertFalse(ITEM.instanceOf(ELM));
    assertTrue(ELM.instanceOf(ITEM));
  }