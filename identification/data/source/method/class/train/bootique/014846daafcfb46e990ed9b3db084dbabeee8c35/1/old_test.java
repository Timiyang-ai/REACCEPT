    @Test
    public void moduleProviderDependencies() {
        final BQModuleProvider testModuleProvider1 = mock(BQModuleProvider.class);
        final BQModuleProvider testModuleProvider2 = mock(BQModuleProvider.class);
        final BQModuleProvider testModuleProvider3 = mock(BQModuleProvider.class);

        when(testModuleProvider1.dependencies()).thenReturn(asList(testModuleProvider2, testModuleProvider3));

        final Collection<BQModuleProvider> bqModuleProviders =
            BootiqueUtils.moduleProviderDependencies(singletonList(testModuleProvider1));

        assertThat(bqModuleProviders, hasItems(testModuleProvider1, testModuleProvider2, testModuleProvider3));
        assertEquals(3, bqModuleProviders.size());

        verify(testModuleProvider1, new AtLeast(1)).dependencies();
        verify(testModuleProvider1, new AtLeast(1)).name();
        verify(testModuleProvider2, new AtLeast(1)).dependencies();
        verify(testModuleProvider2, new AtLeast(1)).name();
        verify(testModuleProvider3, new AtLeast(1)).dependencies();
        verify(testModuleProvider3, new AtLeast(1)).name();

        verifyNoMoreInteractions(testModuleProvider1, testModuleProvider2, testModuleProvider3);
    }