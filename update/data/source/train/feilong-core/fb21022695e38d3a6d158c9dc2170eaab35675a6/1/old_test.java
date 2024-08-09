@Test(expected = BeanOperationException.class)
    @SuppressWarnings("static-method")
    public void testCopyProperties(){
        AccessExceptionProperty user = new AccessExceptionProperty();

        AccessExceptionProperty user2 = new AccessExceptionProperty();
        BeanUtil.copyProperties(user2, user);
    }