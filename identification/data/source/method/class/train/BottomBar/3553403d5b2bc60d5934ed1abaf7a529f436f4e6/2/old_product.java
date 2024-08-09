void setItems(@XmlRes int xmlRes) {
        TabParser.Config config = new TabParser.Config.Builder()
                .inActiveTabAlpha(inActiveTabAlpha)
                .activeTabAlpha(activeTabAlpha)
                .inActiveTabColor(inActiveTabColor)
                .activeTabColor(activeTabColor)
                .barColorWhenSelected(Color.WHITE)
                .badgeBackgroundColor(Color.RED)
                .build();

        TabParser parser = new TabParser(getContext(), config, xmlRes);
        updateItems(parser.getTabs());
    }