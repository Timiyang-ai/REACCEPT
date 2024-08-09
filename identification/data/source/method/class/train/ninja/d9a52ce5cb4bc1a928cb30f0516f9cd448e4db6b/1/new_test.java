    @Test
    public void resolveStandaloneClass() throws Exception {
        Class<? extends Standalone> resolvedStandaloneClass;
        
        try {
            // try to resolve system property
            StandaloneHelper.resolveStandaloneClass("sys_prop_does_not_exist", null, null);
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage(), containsString("sys_prop_does_not_exist"));
        }
        
        // try to find using META-INF/services
        // mock the service loader
        ForwardingServiceLoader<Standalone> serviceLoader
            = mock(ForwardingServiceLoader.class);

        List<Standalone> standalones = new ArrayList<>();
        standalones.add(new MockStandalone());
        when(serviceLoader.iterator()).thenReturn(standalones.iterator());

        resolvedStandaloneClass = StandaloneHelper.resolveStandaloneClass(null, serviceLoader, "default_does_not_exist");
       
        assertThat(resolvedStandaloneClass.newInstance(), instanceOf(MockStandalone.class));
        
        try {
            // try to resolve using default
            when(serviceLoader.iterator()).thenReturn(new ArrayList<Standalone>().iterator());
            StandaloneHelper.resolveStandaloneClass(null, serviceLoader, "default_does_not_exist");
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage(), containsString("default_does_not_exist"));
        }
        
    }