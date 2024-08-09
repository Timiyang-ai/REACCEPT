public boolean hasDefinitionAt(String symbol, int lineNumber, String[] strs) {
        Set<Integer> lines = symbols.get(symbol);
        if (strs.length > 0) {
            strs[0] = "none";
        }

        // Get tag info
        if (lines != null && lines.contains(lineNumber)) {
            LineTagMap line_map = line_maps.get(lineNumber);
            if (line_map != null) {
                Set<Tag> tags = line_map.sym_tags.get(symbol);
                Iterator it = tags.iterator();
                while (it.hasNext()) {
                    Tag tag = (Tag)it.next();
                    if (strs.length > 0) {
                        strs[0] = tag.type;
                    }
                    // Assume the first one
                    break;
                }
            }
            return true;
        }

        return false;
    }