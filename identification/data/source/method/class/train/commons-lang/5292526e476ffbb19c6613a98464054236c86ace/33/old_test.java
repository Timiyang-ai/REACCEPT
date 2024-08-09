@Test(expected=IllegalArgumentException.class)
    public void test_setUpToClass_invalid() {
        Integer val = Integer.valueOf(5);
        ReflectionToStringBuilder test = new ReflectionToStringBuilder(val);
        try {
            test.setUpToClass(String.class);
        } finally {
            test.toString();
        }
    }