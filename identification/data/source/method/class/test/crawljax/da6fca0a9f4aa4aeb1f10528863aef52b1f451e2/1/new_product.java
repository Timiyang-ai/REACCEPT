public Document getDocument() throws SAXException, IOException {
		return DomUtils.getDocument(this.dom);
	}