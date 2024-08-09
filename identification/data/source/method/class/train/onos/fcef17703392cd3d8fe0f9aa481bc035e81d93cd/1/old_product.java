public boolean allocateLabel(Tunnel tunnel) {
        long applyNum = 1;
        boolean isLastLabelToPush = false;
        Collection<LabelResource> labelRscList;

        checkNotNull(labelRsrcService, LABEL_RESOURCE_SERVICE_NULL);
        checkNotNull(pceStore, PCE_STORE_NULL);

        List<Link> linkList = tunnel.path().links();
        if ((linkList != null) && (linkList.size() > 0)) {
            // Sequence through reverse order to push local labels into devices
            // Generation of labels from egress to ingress
            for (ListIterator iterator = linkList.listIterator(linkList.size()); iterator.hasPrevious();) {
                Link link = (Link) iterator.previous();
                DeviceId dstDeviceId = link.dst().deviceId();
                DeviceId srcDeviceId = link.src().deviceId();
                labelRscList = labelRsrcService.applyFromDevicePool(dstDeviceId, applyNum);
                if ((labelRscList != null) && (labelRscList.size() > 0)) {
                    // Link label value is taken from destination device local pool.
                    // [X]OUT---link----IN[Y]OUT---link-----IN[Z] where X, Y and Z are nodes.
                    // Link label value is used as OUT and IN for both ends
                    // (source and destination devices) of the link.
                    // Currently only one label is allocated to a device (destination device).
                    // So, no need to iterate through list
                    Iterator<LabelResource> labelIterator = labelRscList.iterator();
                    DefaultLabelResource defaultLabelResource = (DefaultLabelResource) labelIterator.next();
                    LabelResourceId labelId = defaultLabelResource.labelResourceId();
                    log.debug("Allocated local label: " + labelId.toString()
                              + "to device: " + defaultLabelResource.deviceId().toString());
                    PortNumber dstPort = link.dst().port();

                    // Check whether this is last link label to push
                    if (!iterator.hasPrevious()) {
                       isLastLabelToPush = true;
                    }

                    // Push into destination device
                    //TODO: uncomment below lines once installLocalLabelRule() method is ready
                    // Destination device IN port is link.dst().port()
                    //installLocalLabelRule(dstDeviceId, labelId, dstPort, tunnel.tunnelId(), isLastLabelToPush,
                    //                      LabelType.IN, Objective.Operation.ADD);

                    // Push into source device
                    //TODO: uncomment below lines once installLocalLabelRule() method is ready
                    // Source device OUT port will be link.dst().port(). Means its remote port used to send packet.
                    //installLocalLabelRule(srcDeviceId, labelId, dstPort, tunnel.tunnelId(), isLastLabelToPush,
                    //                      LabelType.OUT, Objective.Operation.ADD);

                    // Add or update pcecc tunnel info in pce store.
                    updatePceccTunnelInfoInStore(srcDeviceId, dstDeviceId, labelId, dstPort,
                                                 tunnel, isLastLabelToPush);
                } else {
                    log.error("Unable to allocate label to device id {}.", dstDeviceId.toString());
                    releaseLabel(tunnel);
                    return false;
                }
            }
        } else {
           log.error("Tunnel {} is having empty links.", tunnel.toString());
           return false;
        }

        return true;
    }