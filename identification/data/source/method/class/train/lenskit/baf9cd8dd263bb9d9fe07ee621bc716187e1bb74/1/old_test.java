@Test
    public void testAlwaysAddChannel() {
        MutableSparseVector simple = simpleVector();
        simple.addChannel(fooSymbol);
        simple.channel(fooSymbol).set(3, 4.5);
        simple.getOrAddChannel(fooSymbol);
        simple.getOrAddChannel(barSymbol);
        assert(simple.channel(barSymbol).isEmpty());
        simple.channel(barSymbol).set(3, 33);
        assertThat(simple.channel(fooSymbol).get(3), closeTo(4.5));
        assertThat(simple.channel(barSymbol).get(3, -1.0), closeTo(33));
        simple.channel(fooSymbol).unset(8);
        assertThat(simple.channel(fooSymbol).get(8, 45.0), closeTo(45.0));
    }