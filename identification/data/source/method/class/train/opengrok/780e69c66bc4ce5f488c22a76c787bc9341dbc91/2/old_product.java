public boolean hasDefinitionAt(String symbol, int lineNumber, String[] strs) {
        Set<Integer> lines = symbols.get(symbol);
        if (strs.length > 0) {
            strs[0] = "none";
        }

        // Get tag info
        if (lines != null && lines.contains(lineNumber)) {
            LineTagMap line_map = line_maps.get(lineNumber);
            if (line_map != null) {
                for (Tag tag : line_map.sym_tags.get(symbol)) {
                    if (tag.used) continue;
                    if (strs.length > 0) { //NOPMD
                        strs[0] = tag.type;
                    }
                    tag.used = true;
                    // Assume the first one
                    return true;
                }
            }
        }
        return false;
    }