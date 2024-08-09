@Override
    public void updateLocalityStore(Map<XdsLocality, LocalityInfo> localityInfoMap) {
      Set<XdsLocality> oldLocalities = localityMap.keySet();
      Set<XdsLocality> newLocalities = localityInfoMap.keySet();
      ImmutableMap.Builder<XdsLocality, LocalityLbInfo> updatedLocalityMap = ImmutableMap.builder();

      for (XdsLocality oldLocality : oldLocalities) {
        if (!newLocalities.contains(oldLocality)) {
          deactivate(oldLocality);
        }
      }

      ConnectivityState newState = null;
      List<WeightedChildPicker> childPickers = new ArrayList<>(newLocalities.size());
      for (XdsLocality newLocality : newLocalities) {

        if (!edsResponsLocalityInfo.containsKey(newLocality)) {
          loadStatsStore.addLocality(newLocality);
        }

        // Assuming standard mode only (EDS response with a list of endpoints) for now.
        List<EquivalentAddressGroup> newEags = localityInfoMap.get(newLocality).eags;
        LocalityLbInfo localityLbInfo;
        ChildHelper childHelper;
        if (oldLocalities.contains(newLocality)) {
          LocalityLbInfo oldLocalityLbInfo = localityMap.get(newLocality);

          oldLocalityLbInfo.reactivate();

          childHelper = oldLocalityLbInfo.childHelper;
          localityLbInfo =
              new LocalityLbInfo(
                  localityInfoMap.get(newLocality).localityWeight,
                  oldLocalityLbInfo.childBalancer,
                  childHelper);
        } else {
          childHelper =
              new ChildHelper(newLocality, loadStatsStore.getLocalityCounter(newLocality),
                  orcaOobUtil);
          localityLbInfo =
              new LocalityLbInfo(
                  localityInfoMap.get(newLocality).localityWeight,
                  loadBalancerProvider.newLoadBalancer(childHelper),
                  childHelper);
          if (metricsReportIntervalNano > 0) {
            localityLbInfo.childHelper.updateMetricsReportInterval(metricsReportIntervalNano);
          }
        }
        updatedLocalityMap.put(newLocality, localityLbInfo);
        // TODO: put endPointWeights into attributes for WRR.
        localityLbInfo.childBalancer
            .handleResolvedAddresses(
                ResolvedAddresses.newBuilder().setAddresses(newEags).build());

        if (localityLbInfo.childHelper.currentChildState == READY) {
          childPickers.add(
              new WeightedChildPicker(
                  localityInfoMap.get(newLocality).localityWeight,
                  localityLbInfo.childHelper.currentChildPicker));
        }
        newState = aggregateState(newState, childHelper.currentChildState);
      }

      // Add deactivated localities to localityMap to keep track of them.
      for (XdsLocality locality : oldLocalities) {
        if (localityMap.get(locality).isDeactivated()) {
          updatedLocalityMap.put(locality, localityMap.get(locality));
        }
      }
      localityMap = updatedLocalityMap.build();

      final Set<XdsLocality> toBeRemovedFromStatsStore = new HashSet<>();
      // There is a race between picking a subchannel and updating localities, which leads to
      // the possibility that RPCs will be sent to a removed locality. As a result, those RPC
      // loads will not be recorded. We consider this to be natural. By removing locality counters
      // after updating subchannel pickers, we eliminate the race and conservatively record loads
      // happening in that period.
      for (XdsLocality oldLocality : edsResponsLocalityInfo.keySet()) {
        if (!localityInfoMap.containsKey(oldLocality)) {
          toBeRemovedFromStatsStore.add(oldLocality);
        }
      }
      helper.getSynchronizationContext().execute(new Runnable() {
        @Override
        public void run() {
          for (XdsLocality locality : toBeRemovedFromStatsStore) {
            loadStatsStore.removeLocality(locality);
          }
        }
      });

      edsResponsLocalityInfo = ImmutableMap.copyOf(localityInfoMap);
      updatePicker(newState, childPickers);
    }