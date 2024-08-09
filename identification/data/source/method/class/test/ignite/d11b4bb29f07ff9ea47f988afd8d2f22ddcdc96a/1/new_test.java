    @Test
    public void nonZeroes() {
        assertNotNull(MathTestConstants.NULL_VAL, testVector.nonZeroes());

        double[] data = initVector();

        assertNotNull(MathTestConstants.NULL_VAL, testVector.nonZeroes());

        Assert.assertEquals(MathTestConstants.VAL_NOT_EQUALS, StreamSupport.stream(testVector.nonZeroes().spliterator(), false).count(), Arrays.stream(data).filter(x -> x != 0d).count());

        addNilValues(data);

        Assert.assertEquals(MathTestConstants.VAL_NOT_EQUALS, StreamSupport.stream(testVector.nonZeroes().spliterator(), false).count(), Arrays.stream(data).filter(x -> x != 0d).count());
    }