public Collection<TopLevelItem> getAllItems() {

        final Collection<TopLevelItem> items = new LinkedHashSet<TopLevelItem>(
                getItems()
        );

        if (this instanceof ViewGroup) {

            for(final View view: ((ViewGroup) this).getViews()) {

                items.addAll(view.getAllItems());
            }
        }

        return Collections.unmodifiableCollection(items);
    }