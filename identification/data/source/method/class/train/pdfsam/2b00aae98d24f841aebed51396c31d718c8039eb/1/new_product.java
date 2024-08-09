@EventListener
    public void request(PdfLoadRequestEvent event) {
        LOG.trace("Pdf load request received");
        event.getDocuments().forEach((i) -> i.moveStatusTo(PdfDescriptorLoadingStatus.REQUESTED));
        executor.submit(() -> loadService.load(event.getDocuments(), requiredLoadData.get(event.getOwnerModule())));
    }