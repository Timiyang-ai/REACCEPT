public Builder customBlockParserFactory(CustomBlockParserFactory blockParserFactory) {
            blockParserFactories.add(blockParserFactory);
            addExtensionApiPoint(blockParserFactory);
            return this;
        }