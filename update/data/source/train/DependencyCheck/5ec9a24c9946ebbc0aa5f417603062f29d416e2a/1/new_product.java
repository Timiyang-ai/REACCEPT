public void addIdentifier(String type, String value, String url) {
        Identifier i = new Identifier(type, value, url);
        this.identifiers.add(i);
    }