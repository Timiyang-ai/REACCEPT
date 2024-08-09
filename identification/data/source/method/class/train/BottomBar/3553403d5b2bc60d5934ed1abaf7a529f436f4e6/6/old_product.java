public void setItems(@XmlRes int xmlRes) {
        clearItems();
        mItems = MiscUtils.inflateFromXMLResource(getContext(), xmlRes);
        updateItems(mItems);
    }