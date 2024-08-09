@EventListener
    public void request(PdfLoadRequestEvent event) {
        LOG.trace("Pdf load request received");
        executor.submit(() -> loadService.load(event.getDocuments()));
    }