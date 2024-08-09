    @Test
    public void doStart() throws Exception {
        when(selector.selectCounts()).thenReturn(1);
        when(selector.getCandidates()).thenReturn(Lists.newArrayList());
        selector.setSelectStrategy(new SelectOneCycle(selector));
        doCallRealMethod().when(selector).nextHop();

        Throwable throwable = null;
        try {
            session.doStart();
        } catch (Exception e) {
            throwable = e;
        }
        Assert.assertNotNull(throwable);
        Assert.assertTrue(throwable instanceof NoResourceException);
    }