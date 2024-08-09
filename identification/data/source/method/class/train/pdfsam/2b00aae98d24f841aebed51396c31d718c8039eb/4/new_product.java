@EventListener
    public void request(PdfLoadRequestEvent event) {
        LOG.trace("PDF load request received");
        event.getDocuments().forEach(i -> i.moveStatusTo(PdfDescriptorLoadingStatus.REQUESTED));
        executor.execute(() -> loadService.load(event.getDocuments(), requiredLoadData.get(event.getOwnerModule())));
    }