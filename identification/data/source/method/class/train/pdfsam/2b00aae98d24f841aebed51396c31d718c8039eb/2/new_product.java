@EventListener
    public void request(PdfLoadRequestEvent<? extends PdfDocumentDescriptor> event) {
        LOG.trace("Pdf load request received");
        event.getDocuments().forEach(i -> i.moveStatusTo(PdfDescriptorLoadingStatus.REQUESTED));
        executor.execute(() -> loadService.load(event.getDocuments(), requiredLoadData.get(event.getOwnerModule())));
    }