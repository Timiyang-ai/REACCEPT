public void removeNode(Layout currentLayout, String endpoint) throws QuorumUnreachableException,
            CloneNotSupportedException,
            LayoutModificationException, OutrankedException, ExecutionException {

        LayoutBuilder builder = new LayoutBuilder(currentLayout);
        Layout newLayout = builder.removeLayoutServer(endpoint)
                .removeSequencerServer(endpoint)
                .removeLogunitServer(endpoint)
                .removeUnResponsiveServer(endpoint)
                .setEpoch(currentLayout.getEpoch() + 1)
                .build();

        // Seal after constructing the layout, so that the system
        // isn't blocked if the builder throws an exception
        currentLayout.setRuntime(runtime);
        sealEpoch(currentLayout);

        newLayout.setRuntime(runtime);
        attemptConsensus(newLayout);

        runtime.invalidateLayout();

        try {
            reconfigureSequencerServers(currentLayout, newLayout, false);
        } catch (InterruptedException ie) {
            log.error("removeNode: Bootstrapping sequencer failed due to exception : ", ie);
            Thread.currentThread().interrupt();
            throw new RuntimeException(ie);
        } catch (ExecutionException ee) {
            log.error("removeNode: Bootstrapping sequencer failed due to exception : ", ee);
            throw ee;
        }
    }