@Test
    public void testAlwaysAddChannel() {
        MutableSparseVector simple = simpleVector();
        simple.addChannelVector(fooSymbol);
        simple.getChannelVector(fooSymbol).set(3, 4.5);
        simple.getOrAddChannelVector(fooSymbol);
        simple.getOrAddChannelVector(barSymbol);
        assert(simple.getChannelVector(barSymbol).isEmpty());
        simple.getChannelVector(barSymbol).set(3, 33);
        assertThat(simple.getChannelVector(fooSymbol).get(3), closeTo(4.5));
        assertThat(simple.getChannelVector(barSymbol).get(3, -1.0), closeTo(33));
        simple.getChannelVector(fooSymbol).unset(8);
        assertThat(simple.getChannelVector(fooSymbol).get(8, 45.0), closeTo(45.0));
    }