@Test
    public void getAllItems() throws Exception {

        final CompositeView rootView = Mockito.mock(CompositeView.class);
        final View leftView = Mockito.mock(View.class);
        final View rightView = Mockito.mock(View.class);

        Mockito.when(rootView.getAllItems()).thenCallRealMethod();
        Mockito.when(leftView.getAllItems()).thenCallRealMethod();
        Mockito.when(rightView.getAllItems()).thenCallRealMethod();

        final TopLevelItem rootJob = Mockito.mock(TopLevelItem.class);
        final TopLevelItem leftJob = Mockito.mock(TopLevelItem.class);
        final TopLevelItem rightJob = Mockito.mock(TopLevelItem.class);
        final TopLevelItem sharedJob = Mockito.mock(TopLevelItem.class);

        Mockito.when(rootJob.getDisplayName()).thenReturn("rootJob");
        Mockito.when(leftJob.getDisplayName()).thenReturn("leftJob");
        Mockito.when(rightJob.getDisplayName()).thenReturn("rightJob");
        Mockito.when(sharedJob.getDisplayName()).thenReturn("sharedJob");

        Mockito.when(rootView.getViews()).thenReturn(Arrays.asList(leftView, rightView));
        Mockito.when(rootView.getItems()).thenReturn(Arrays.asList(rootJob, sharedJob));
        Mockito.when(leftView.getItems()).thenReturn(Arrays.asList(leftJob, sharedJob));
        Mockito.when(rightView.getItems()).thenReturn(Arrays.asList(rightJob));

        final TopLevelItem[] expected = new TopLevelItem[] {rootJob, sharedJob, leftJob, rightJob};

        Assert.assertArrayEquals(expected, rootView.getAllItems().toArray());
    }