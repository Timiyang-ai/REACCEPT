public void setItems(@XmlRes int xmlRes, BottomBarTab.Config defaultTabConfig) {
        if (xmlRes == 0) {
            throw new RuntimeException("No items specified for the BottomBar!");
        }

        if (defaultTabConfig == null) {
            defaultTabConfig = getTabConfig();
        }

        TabParser parser = new TabParser(getContext(), defaultTabConfig, xmlRes);
        updateItems(parser.getTabs());
    }