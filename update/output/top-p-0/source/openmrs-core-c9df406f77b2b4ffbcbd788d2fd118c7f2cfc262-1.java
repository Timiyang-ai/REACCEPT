@Test
public void findProvider_shouldReturnTheListOfProvidersIncludingRetiredProvidersForTheMatchingSearchName()
        throws Exception {
    
    Vector<Object> providers = service.findProvider("provider", true, 0, 10);
    Assert.assertEquals(3, providers.size());
    
    Assert.assertTrue(CollectionUtils.exists(providers, new Predicate() {
        
        @Override
        public boolean evaluate(Object object) {
            return ((ProviderListItem) object).getDisplayName().equals("Jimmy Manana Chemalit");
        }
    }));
}