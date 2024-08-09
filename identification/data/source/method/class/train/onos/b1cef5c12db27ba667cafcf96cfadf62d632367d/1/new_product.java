public Set<VplsConfig> vplss() {
        Set<VplsConfig> vplss = Sets.newHashSet();

        JsonNode vplsNode = object.get(VPLS);

        if (vplsNode == null) {
            return vplss;
        }

        vplsNode.forEach(jsonNode -> {
            String name = jsonNode.get(NAME).asText();

            Set<String> ifaces = Sets.newHashSet();
            JsonNode vplsIfaces = jsonNode.path(INTERFACE);
            if (vplsIfaces.toString().isEmpty()) {
                vplsIfaces = ((ObjectNode) jsonNode).putArray(INTERFACE);
            }
            vplsIfaces.forEach(ifacesNode -> ifaces.add(ifacesNode.asText()));

            String encap = null;
            if (jsonNode.hasNonNull(ENCAPSULATION)) {
                encap = jsonNode.get(ENCAPSULATION).asText();
            }
            vplss.add(new VplsConfig(name,
                                     ifaces,
                                     EncapsulationType.enumFromString(encap)));
        });

        return vplss;
    }