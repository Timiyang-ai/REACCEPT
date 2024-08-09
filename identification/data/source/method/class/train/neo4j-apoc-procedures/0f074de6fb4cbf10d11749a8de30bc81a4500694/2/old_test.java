        private ResourceIterator<Node> search(String index, String value) {
        return db.execute("CALL apoc.index.search({index}, {value}) YIELD node RETURN node",
                map("index", index, "value", value)).columnAs("node");
    }