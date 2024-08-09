public FastAdapter<Item> withSelectable(boolean selectable) {
        if (selectable) {
            addExtension(mSelectExtension);
        } else {
            mExtensions.remove(mSelectExtension);
        }
        //TODO revisit this --> false means anyways that it is not in the extension list!
        mSelectExtension.withSelectable(selectable);
        return this;
    }