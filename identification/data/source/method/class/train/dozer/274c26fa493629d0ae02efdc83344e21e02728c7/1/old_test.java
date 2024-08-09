    @Test(expected = MappingException.class)
    public void test_convert_exception() {
        converter.convert(Boolean.TRUE, new BigDecimal(1), Boolean.class,
                          BigDecimal.class);
        fail();
    }