@Test public void instanceOf() {
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
    final MapType m = MapType.get(AtomType.STR, ITR);
    assertTrue(m.instanceOf(m));
    assertTrue(m.instanceOf(AtomType.ITEM));
    assertTrue(m.instanceOf(FuncType.ANY_FUN));
    assertTrue(m.instanceOf(MapType.ANY_MAP));
    assertTrue(m.instanceOf(MapType.get(AtomType.ITEM, ITR)));
    assertTrue(m.instanceOf(MapType.get(AtomType.STR, ITR)));
    assertTrue(m.instanceOf(MapType.get(AtomType.STR, ITR_ZO)));
    assertFalse(m.instanceOf(ArrayType.ANY_ARRAY));
    assertFalse(m.instanceOf(MapType.get(AtomType.STR, BLN)));
    assertFalse(m.instanceOf(MapType.get(AtomType.ITR, ITEM_ZM)));

    final ArrayType a = ArrayType.get(ITR);
    assertTrue(a.instanceOf(a));
    assertTrue(a.instanceOf(AtomType.ITEM));
    assertTrue(a.instanceOf(FuncType.ANY_FUN));
    assertTrue(a.instanceOf(ArrayType.ANY_ARRAY));
    assertTrue(a.instanceOf(ArrayType.get(ITR)));
    assertTrue(a.instanceOf(ArrayType.get(ITR)));
    assertTrue(a.instanceOf(ArrayType.get(ITR_ZO)));
    assertFalse(a.instanceOf(MapType.ANY_MAP));
    assertFalse(a.instanceOf(ArrayType.get(BLN)));

    // nodes
    assertTrue(ATT.instanceOf(NOD));
    assertTrue(ATT.instanceOf(ATT));
    assertFalse(ATT.instanceOf(ELM));
    assertFalse(ELM.instanceOf(f));
    assertFalse(NOD.instanceOf(ELM));
    assertFalse(ITEM.instanceOf(ELM));
    assertTrue(ELM.instanceOf(ITEM));
  }