@Override
    public void updateLocalityStore(
        final ImmutableMap<Locality, LocalityLbEndpoints> localityInfoMap) {

      Set<Locality> newLocalities = localityInfoMap.keySet();
      // TODO: put endPointWeights into attributes for WRR.
      for (Locality locality : newLocalities) {
        if (localityMap.containsKey(locality)) {
          final LocalityLbInfo localityLbInfo = localityMap.get(locality);
          LocalityLbEndpoints localityInfo = localityInfoMap.get(locality);
          final List<EquivalentAddressGroup> eags = new ArrayList<>();
          for (LbEndpoint endpoint: localityInfo.getEndpoints()) {
            eags.add(endpoint.getAddress());
          }
          // In extreme case handleResolvedAddresses() may trigger updateBalancingState()
          // immediately, so execute handleResolvedAddresses() after all the setup in this method is
          // complete.
          helper.getSynchronizationContext().execute(new Runnable() {
            @Override
            public void run() {
              localityLbInfo.childBalancer.handleResolvedAddresses(
                  ResolvedAddresses.newBuilder().setAddresses(eags).build());
            }
          });
        }
      }

      for (Locality newLocality : newLocalities) {
        if (!localities.contains(newLocality)) {
          loadStatsStore.addLocality(newLocality);
        }
      }
      final Set<Locality> toBeRemovedFromStatsStore = new HashSet<>();
      // There is a race between picking a subchannel and updating localities, which leads to
      // the possibility that RPCs will be sent to a removed locality. As a result, those RPC
      // loads will not be recorded. We consider this to be natural. By removing locality counters
      // after updating subchannel pickers, we eliminate the race and conservatively record loads
      // happening in that period.
      for (Locality oldLocality : localities) {
        if (!localityInfoMap.containsKey(oldLocality)) {
          toBeRemovedFromStatsStore.add(oldLocality);
        }
      }
      helper.getSynchronizationContext().execute(new Runnable() {
        @Override
        public void run() {
          for (Locality locality : toBeRemovedFromStatsStore) {
            loadStatsStore.removeLocality(locality);
          }
        }
      });
      localities = newLocalities;

      priorityManager.updateLocalities(localityInfoMap);

      for (Locality oldLocality : localityMap.keySet()) {
        if (!newLocalities.contains(oldLocality)) {
          deactivate(oldLocality);
        }
      }
    }