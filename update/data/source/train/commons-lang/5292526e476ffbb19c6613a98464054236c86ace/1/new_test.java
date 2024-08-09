@Test(expected=IllegalArgumentException.class)
    public void test_setUpToClass_invalid() {
        final Integer val = Integer.valueOf(5);
        final ReflectionToStringBuilder test = new ReflectionToStringBuilder(val);
        try {
            test.setUpToClass(String.class);
        } finally {
            test.toString();
        }
    }