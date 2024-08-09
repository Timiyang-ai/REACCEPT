protected XDOM buildPresentationXDOM(String html, DocumentReference targetDocumentReference)
        throws OfficeImporterException
    {
        try {
            String syntaxId = this.documentAccessBridge.getDocument(targetDocumentReference).getSyntax().toIdString();
            BlockRenderer renderer = this.componentManager.getInstance(BlockRenderer.class, syntaxId);

            Map<String, String> galleryParameters = Collections.emptyMap();
            ExpandedMacroBlock gallery = new ExpandedMacroBlock("gallery", galleryParameters, renderer, false);
            gallery.addChild(this.xhtmlParser.parse(new StringReader(html)));

            XDOM xdom = new XDOM(Collections.singletonList((Block) gallery));
            // Make sure (image) references are resolved relative to the target document reference.
            xdom.getMetaData().addMetaData(MetaData.BASE, entityReferenceSerializer.serialize(targetDocumentReference));
            return xdom;
        } catch (Exception e) {
            throw new OfficeImporterException("Failed to build presentation XDOM.", e);
        }
    }