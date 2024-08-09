public Builder customBlockParserFactory(BlockParserFactory blockParserFactory) {
            if (blockParserFactory == null) {
                throw new NullPointerException("blockParserFactory must not be null");
            }
            blockParserFactories.add(blockParserFactory);
            return this;
        }