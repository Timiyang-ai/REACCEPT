public Document getDocument() throws IOException {
		return DomUtils.asDocument(this.dom);
	}