@Test public void instanceOf() {
    assertTrue(BLN_O.instanceOf(AAT_ZM));
    assertFalse(AAT_ZM.instanceOf(BLN_O));
    assertTrue(DBL_O.instanceOf(DBL_ZM));
    assertFalse(DBL_ZM.instanceOf(DBL_O));

    // functions
    final SeqType f = FuncType.get(DEC_ZO, BLN_O).seqType();
    assertFalse(f.instanceOf(ITR_O));
    assertTrue(f.instanceOf(ITEM_O));
    assertTrue(f.instanceOf(f));
    assertTrue(f.instanceOf(FUN_ZO));
    assertFalse(FUN_O.instanceOf(f));
    assertFalse(f.instanceOf(FuncType.get(DEC_ZO, BLN_O, ITR_O).seqType()));
    assertFalse(f.instanceOf(FuncType.get(DEC_ZO, AAT_O).seqType()));
    assertFalse(f.instanceOf(FuncType.get(BLN_O, BLN_O).seqType()));

    // maps
    final MapType m = MapType.get(AtomType.STR, ITR_O);
    assertTrue(m.instanceOf(m));
    assertTrue(m.instanceOf(AtomType.ITEM));
    assertTrue(m.instanceOf(ANY_FUN));
    assertTrue(m.instanceOf(ANY_MAP));
    assertTrue(m.instanceOf(MapType.get(AtomType.ITEM, ITR_O)));
    assertTrue(m.instanceOf(MapType.get(AtomType.STR, ITR_O)));
    assertTrue(m.instanceOf(MapType.get(AtomType.STR, ITR_ZO)));
    assertFalse(m.instanceOf(ANY_ARRAY));
    assertFalse(m.instanceOf(MapType.get(AtomType.STR, BLN_O)));
    assertFalse(m.instanceOf(MapType.get(AtomType.ITR, ITEM_ZM)));

    final ArrayType a = ArrayType.get(ITR_O);
    assertTrue(a.instanceOf(a));
    assertTrue(a.instanceOf(AtomType.ITEM));
    assertTrue(a.instanceOf(ANY_FUN));
    assertTrue(a.instanceOf(ANY_ARRAY));
    assertTrue(a.instanceOf(ArrayType.get(ITR_O)));
    assertTrue(a.instanceOf(ArrayType.get(ITR_O)));
    assertTrue(a.instanceOf(ArrayType.get(ITR_ZO)));
    assertFalse(a.instanceOf(ANY_MAP));
    assertFalse(a.instanceOf(ArrayType.get(BLN_O)));

    // nodes
    assertTrue(ATT_O.instanceOf(NOD_O));
    assertTrue(ATT_O.instanceOf(ATT_O));
    assertFalse(ATT_O.instanceOf(ELM_O));
    assertFalse(ELM_O.instanceOf(f));
    assertFalse(NOD_O.instanceOf(ELM_O));
    assertFalse(ITEM_O.instanceOf(ELM_O));
    assertTrue(ELM_O.instanceOf(ITEM_O));
  }