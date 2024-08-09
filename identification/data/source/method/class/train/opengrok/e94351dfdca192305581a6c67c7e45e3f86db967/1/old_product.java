public List<Tag> getTags(int line) {
        LineTagMap line_map = line_maps.get(line);
        List<Tag> result = null;

        if (line_map != null) {
            result = new ArrayList<>();
            for (Set<Tag> tags : line_map.sym_tags.values()) {
                for (Tag tag : tags) {
                    result.add(tag);
                }
            }
        }        
        
        return result;
    }