public void addIdentifier(String type, String value, String title, String url) {
        Identifier i = new Identifier(type, value, title, url);
        this.identifiers.add(i);
    }