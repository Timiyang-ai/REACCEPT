@Test
    @SmallTest
    public void testConvert_noConstraints_doesNotThrowException() {
        mConverter.convert(new WorkSpec("id"));
    }