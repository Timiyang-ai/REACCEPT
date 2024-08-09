@Test public void testStrong() {
    final RelDataType intType = typeFactory.createSqlType(SqlTypeName.INTEGER);

    final ImmutableBitSet c = ImmutableBitSet.of();
    final ImmutableBitSet c0 = ImmutableBitSet.of(0);
    final ImmutableBitSet c1 = ImmutableBitSet.of(1);
    final ImmutableBitSet c01 = ImmutableBitSet.of(0, 1);
    final ImmutableBitSet c13 = ImmutableBitSet.of(1, 3);

    // input ref
    final RexInputRef aRef = rexBuilder.makeInputRef(intType, 0);

    assertThat(strongIf(aRef, c0), is(true));
    assertThat(strongIf(aRef, c1), is(false));
    assertThat(strongIf(aRef, c01), is(true));
    assertThat(strongIf(aRef, c13), is(false));

    // literals are strong iff they are always null
    assertThat(strongIf(trueLiteral, c), is(false));
    assertThat(strongIf(trueLiteral, c13), is(false));
    assertThat(strongIf(falseLiteral, c13), is(false));
    assertThat(strongIf(nullLiteral, c), is(true));
    assertThat(strongIf(nullLiteral, c13), is(true));
    assertThat(strongIf(unknownLiteral, c13), is(true));

    // AND is strong if one of its arguments is strong
    final RexNode andUnknownTrue = and(unknownLiteral, trueLiteral);
    final RexNode andTrueUnknown = and(trueLiteral, unknownLiteral);
    final RexNode andFalseTrue = and(falseLiteral, trueLiteral);

    assertThat(strongIf(andUnknownTrue, c), is(true));
    assertThat(strongIf(andTrueUnknown, c), is(true));
    assertThat(strongIf(andFalseTrue, c), is(false));
  }