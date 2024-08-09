public Collection<TopLevelItem> getAllItems() {

        if (this instanceof ViewGroup) {
            final Collection<TopLevelItem> items = new LinkedHashSet<TopLevelItem>(getItems());

            for(View view: ((ViewGroup) this).getViews()) {
                items.addAll(view.getAllItems());
            }
            return Collections.unmodifiableCollection(items);
        } else {
            return getItems();
        }
    }