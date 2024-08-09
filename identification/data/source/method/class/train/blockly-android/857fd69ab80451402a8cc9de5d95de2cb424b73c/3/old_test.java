    private Block fromXml(String xml) {
        try {
            return BlocklyXmlHelper.loadOneBlockFromXml(xml, mBlockFactory);
        } catch (BlockLoadingException e) {
            // Should not happen. Throw as RuntimeException
            throw new IllegalStateException(e);
        }
    }