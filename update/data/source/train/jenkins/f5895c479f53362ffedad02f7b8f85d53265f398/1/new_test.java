@Test
    public void getAllItems() throws Exception {

        final View leftView = Mockito.mock(View.class);
        final View rightView = Mockito.mock(View.class);
        CompositeView rootView = new CompositeView("rootJob", leftView, rightView);

        Mockito.when(leftView.getAllItems()).thenCallRealMethod();
        Mockito.when(rightView.getAllItems()).thenCallRealMethod();

        final TopLevelItem rootJob = createJob("rootJob");
        final TopLevelItem sharedJob = createJob("sharedJob");
        
        rootView = rootView.withJobs(rootJob, sharedJob);
        
        final TopLevelItem leftJob = createJob("leftJob");
        final TopLevelItem rightJob = createJob("rightJob");

        Mockito.when(leftView.getItems()).thenReturn(Arrays.asList(leftJob, sharedJob));
        Mockito.when(rightView.getItems()).thenReturn(Arrays.asList(rightJob));

        final TopLevelItem[] expected = new TopLevelItem[] {rootJob, sharedJob, leftJob, rightJob};

        Assert.assertArrayEquals(expected, rootView.getAllItems().toArray());
    }