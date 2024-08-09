@Override
    public void updateLocalityStore(Map<XdsLocality, LocalityInfo> localityInfoMap) {
      Set<XdsLocality> oldLocalities = localityMap.keySet();
      Set<XdsLocality> newLocalities = localityInfoMap.keySet();
      Map<XdsLocality, LocalityLbInfo> updatedLocalityMap = new LinkedHashMap<>();

      final Set<XdsLocality> toRemove = new HashSet<>();
      for (XdsLocality oldLocality : oldLocalities) {
        if (!newLocalities.contains(oldLocality)) {
          toRemove.add(oldLocality);
          // No graceful transition until a high-level lb graceful transition design is available.
          localityMap.get(oldLocality).shutdown();
        }
      }

      ConnectivityState newState = null;
      List<WeightedChildPicker> childPickers = new ArrayList<>(newLocalities.size());
      for (XdsLocality newLocality : newLocalities) {

        // Assuming standard mode only (EDS response with a list of endpoints) for now
        List<EquivalentAddressGroup> newEags = localityInfoMap.get(newLocality).eags;
        LocalityLbInfo localityLbInfo;
        ChildHelper childHelper;
        if (oldLocalities.contains(newLocality)) {
          LocalityLbInfo oldLocalityLbInfo = localityMap.get(newLocality);
          childHelper = oldLocalityLbInfo.childHelper;
          localityLbInfo =
              new LocalityLbInfo(oldLocalityLbInfo.localityWeight,
                  oldLocalityLbInfo.childBalancer,
                  childHelper);
        } else {
          statsStore.addLocality(newLocality);
          childHelper =
              new ChildHelper(newLocality, statsStore.getLocalityCounter(newLocality), orcaOobUtil);
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
      localityMap = Collections.unmodifiableMap(updatedLocalityMap);

      updatePicker(newState, childPickers);

      // There is a race between picking a subchannel and updating localities, which leads to
      // the possibility that RPCs will be sent to a removed locality. As a result, those RPC
      // loads will not be recorded. We consider this to be natural. By removing locality counters
      // after updating subchannel pickers, we eliminate the race and conservatively record loads
      // happening in that period.
      helper.getSynchronizationContext().execute(new Runnable() {
        @Override
        public void run() {
          for (XdsLocality locality : toRemove) {
            statsStore.removeLocality(locality);
          }
        }
      });
    }