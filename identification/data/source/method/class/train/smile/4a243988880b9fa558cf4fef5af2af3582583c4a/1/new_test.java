    @Test
    void diff() throws InvalidExpressionException {

        final String QUERY = "4*x^2";
        final String EXPECTED = "8*x";
        String actual = Calculus.diff(QUERY);

        assertEquals(EXPECTED, actual);
    }