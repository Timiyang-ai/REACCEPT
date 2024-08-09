protected XDOM buildPresentationXDOM(String html, DocumentReference targetDocumentReference)
        throws OfficeImporterException
    {
        try {
            String syntaxId = documentAccessBridge.getDocument(targetDocumentReference).getSyntax().toIdString();
            BlockRenderer renderer = componentManager.lookup(BlockRenderer.class, syntaxId);

            Map<String, String> galleryParameters = Collections.emptyMap();
            ExpandedMacroBlock gallery = new ExpandedMacroBlock("gallery", galleryParameters, renderer, false);
            gallery.addChild(xhtmlParser.parse(new StringReader(html)));

            return new XDOM(Collections.singletonList((Block) gallery));
        } catch (Exception e) {
            throw new OfficeImporterException("Failed to build presentation XDOM.", e);
        }
    }