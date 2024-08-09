@Test
    public void testGo() throws Exception {
        final String importerDescription = "Some description.";
        final AcceptsOneWidget container = mock(AcceptsOneWidget.class);
        final ProjectImporterDescriptor projectImporter = mock(ProjectImporterDescriptor.class);

        when(projectImporter.getDescription()).thenReturn(importerDescription);

        presenter.go(container);

        verify(container).setWidget(eq(view));
        verify(view).setProjectName(anyString());
        verify(view).setProjectDescription(anyString());
        verify(view).setProjectUrl(anyString());
        verify(view).setUrlTextBoxFocused();
    }