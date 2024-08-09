@Override
    public void updateLocalityStore(Map<Locality, LocalityInfo> localityInfoMap) {
      Set<Locality> oldLocalities = localityMap.keySet();
      Set<Locality> newLocalities = localityInfoMap.keySet();

      Iterator<Locality> iterator = oldLocalities.iterator();
      while (iterator.hasNext()) {
        Locality oldLocality = iterator.next();
        if (!newLocalities.contains(oldLocality)) {
          // No graceful transition until a high-level lb graceful transition design is available.
          localityMap.get(oldLocality).shutdown();
          iterator.remove();
          if (localityMap.isEmpty()) {
            // down-size the map
            localityMap = new HashMap<>();
          }
        }
      }

      ConnectivityState newState = null;
      List<WeightedChildPicker> childPickers = new ArrayList<>(newLocalities.size());
      for (Locality newLocality : newLocalities) {

        // Assuming standard mode only (EDS response with a list of endpoints) for now
        List<EquivalentAddressGroup> newEags = localityInfoMap.get(newLocality).eags;
        LocalityLbInfo localityLbInfo;
        ChildHelper childHelper;
        if (oldLocalities.contains(newLocality)) {
          LocalityLbInfo oldLocalityLbInfo
              = localityMap.get(newLocality);
          childHelper = oldLocalityLbInfo.childHelper;
          localityLbInfo = new LocalityLbInfo(
              localityInfoMap.get(newLocality).localityWeight,
              oldLocalityLbInfo.childBalancer,
              childHelper);
        } else {
          childHelper = new ChildHelper(newLocality);
          localityLbInfo =
              new LocalityLbInfo(
                  localityInfoMap.get(newLocality).localityWeight,
                  loadBalancerProvider.newLoadBalancer(childHelper),
                  childHelper);
          localityMap.put(newLocality, localityLbInfo);
        }
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

      updatePicker(newState, childPickers);

    }