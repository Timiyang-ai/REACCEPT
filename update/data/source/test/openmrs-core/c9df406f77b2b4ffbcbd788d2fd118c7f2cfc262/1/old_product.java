public Vector<Object> findProvider(String name, boolean includeVoided, Integer start, Integer length) {
		Vector<Object> providerListItem = new Vector<Object>();
		List<Provider> providerList = Context.getProviderService().getProviders(name, start, length, null);
		
		if (providerList.size() == 0) {
			MessageSourceService mss = Context.getMessageSourceService();
			providerListItem.add(mss.getMessage("Provider.noMatchesFound", new Object[] { name }, Context.getLocale()));
		} else {
			for (Provider p : providerList) {
				if (!p.isRetired() || (p.isRetired() && includeVoided == true))
					providerListItem.add(new ProviderListItem(p));
			}
		}
		return providerListItem;
	}