public void setItems(@XmlRes int xmlRes) {
        mItems = MiscUtils.inflateFromXMLResource(getContext(), xmlRes);
        updateItems(mItems);
    }