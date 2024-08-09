    @Test
    public void addDelegateComponentsTest() {
        (new PlatformAccess()).setPlatform(mockPlatform);
        BaseComponentSub2 comp = new BaseComponentSub2();
        BaseComponentSub2 comp2 = new BaseComponentSub2();
        comp.addDelegateComponent(comp2);
        
        Assert.assertEquals(comp.getComponents().size(), 1);
        Assert.assertEquals(comp.getComponents().iterator().next(), comp2);
    }