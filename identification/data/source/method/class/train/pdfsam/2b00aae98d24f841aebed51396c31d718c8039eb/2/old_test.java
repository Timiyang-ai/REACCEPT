    @Test
    public void request() {
        PdfLoadRequestEvent event = new PdfLoadRequestEvent(DefaultPriorityTestModule.ID);
        PdfDocumentDescriptor first = mock(PdfDocumentDescriptor.class);
        PdfDocumentDescriptor second = mock(PdfDocumentDescriptor.class);
        event.add(first);
        event.add(second);
        victim.request(event);
        verify(first).moveStatusTo(PdfDescriptorLoadingStatus.REQUESTED);
        verify(second).moveStatusTo(PdfDescriptorLoadingStatus.REQUESTED);
        verify(loadService, timeout(1000).times(1)).load(anyCollection(), eq(RequiredPdfData.DEFAULT));
    }