  @Test
  @SuppressWarnings("unchecked")
  public void updateLocalityStore_updateStatsStoreLocalityTracking() {
    Map<Locality, LocalityLbEndpoints> localityInfoMap = new HashMap<>();
    localityInfoMap
        .put(locality1,
            new LocalityLbEndpoints(ImmutableList.of(lbEndpoint11, lbEndpoint12), 1, 0));
    localityInfoMap
        .put(locality2,
            new LocalityLbEndpoints(ImmutableList.of(lbEndpoint21, lbEndpoint22), 2, 0));
    localityStore.updateLocalityStore(ImmutableMap.copyOf(localityInfoMap));
    verify(loadStatsStore).addLocality(locality1);
    verify(loadStatsStore).addLocality(locality2);

    localityInfoMap
        .put(locality3,
            new LocalityLbEndpoints(ImmutableList.of(lbEndpoint31, lbEndpoint32), 3, 0));
    localityStore.updateLocalityStore(ImmutableMap.copyOf(localityInfoMap));
    verify(loadStatsStore).addLocality(locality3);

    localityInfoMap = ImmutableMap
        .of(locality4,
            new LocalityLbEndpoints(ImmutableList.of(lbEndpoint41, lbEndpoint42), 4, 0));
    localityStore.updateLocalityStore(ImmutableMap.copyOf(localityInfoMap));
    verify(loadStatsStore).removeLocality(locality1);
    verify(loadStatsStore).removeLocality(locality2);
    verify(loadStatsStore).removeLocality(locality3);
    verify(loadStatsStore).addLocality(locality4);

    localityStore.updateLocalityStore(ImmutableMap.copyOf(Collections.EMPTY_MAP));
    verify(loadStatsStore).removeLocality(locality4);
  }