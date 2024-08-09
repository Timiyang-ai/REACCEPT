@Test
    @SuppressWarnings("unchecked")
    public void testCompute2() {
        doReturn(42).when(dataset).compute(any(IgniteBiFunction.class), any(IgniteBinaryOperator.class), any());

        Integer res = (Integer) wrapper.compute(mock(IgniteFunction.class), mock(IgniteBinaryOperator.class),
            null);

        assertEquals(42, res.intValue());

        verify(dataset, times(1)).compute(any(IgniteBiFunction.class), any(IgniteBinaryOperator.class), any());
    }