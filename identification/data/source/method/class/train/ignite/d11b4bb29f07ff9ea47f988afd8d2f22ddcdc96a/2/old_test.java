    @Test(expected = NullPointerException.class)
    public void all() {
        assertNotNull(MathTestConstants.NULL_VAL, testVector.all());

        assertNotNull(MathTestConstants.NULL_VAL, getAbstractVector(createStorage()).all());

        getAbstractVector().all().iterator().next();
    }