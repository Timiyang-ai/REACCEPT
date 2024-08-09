@Override
    public void updateLocalityStore(
        final Map<Locality, LocalityLbEndpoints> localityInfoMap) {

      Set<Locality> newLocalities = localityInfoMap.keySet();
      // TODO: put endPointWeights into attributes for WRR.
      for (Locality locality : newLocalities) {
        if (localityMap.containsKey(locality)) {
          LocalityLbInfo localityLbInfo = localityMap.get(locality);
          LocalityLbEndpoints localityLbEndpoints = localityInfoMap.get(locality);
          handleEagsOnChildBalancer(helper, localityLbInfo, localityLbEndpoints, locality);
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