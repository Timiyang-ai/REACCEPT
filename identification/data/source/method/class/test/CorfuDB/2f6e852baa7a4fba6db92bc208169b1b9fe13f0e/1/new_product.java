public void removeNode(Layout currentLayout, String endpoint) throws QuorumUnreachableException,
            CloneNotSupportedException,
            LayoutModificationException, OutrankedException {

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
        reconfigureSequencerServers(currentLayout, newLayout, false);
    }