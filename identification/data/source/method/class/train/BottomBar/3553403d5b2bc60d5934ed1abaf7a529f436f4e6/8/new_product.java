void setItems(@XmlRes int xmlRes) {
        if (xmlRes == 0) {
            throw new RuntimeException("No items specified for the BottomBar!");
        }

        TabParser.Config config = new TabParser.Config.Builder()
                .inActiveTabAlpha(inActiveTabAlpha)
                .activeTabAlpha(activeTabAlpha)
                .inActiveTabColor(inActiveTabColor)
                .activeTabColor(activeTabColor)
                .barColorWhenSelected(defaultBackgroundColor)
                .badgeBackgroundColor(Color.RED)
                .titleTextAppearance(titleTextAppearance)
                .titleTypeFace(getContext(), titleTypeFace)
                .build();

        TabParser parser = new TabParser(getContext(), config, xmlRes);
        updateItems(parser.getTabs());
    }