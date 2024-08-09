public List<Tag> getTags(int line) {
        LineTagMap line_map = line_maps.get(line);
        List<Tag> result = null;

        if (line_map != null) {
            result = new ArrayList<>();
            for (Set<Tag> ltags : line_map.sym_tags.values()) {
                for (Tag tag : ltags) {
                    result.add(tag);
                }
            }
        }

        return result;
    }