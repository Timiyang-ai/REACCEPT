public void validate(String origin) {
        List<String> problems = new ArrayList<>();
        if(source == null) {
            if(path == null) {
                problems.add("You must specify either path or source");
            } else if(method == null) {
                problems.add("You must specify method along with path: " + path);
            }
        } else {
            if(path != null) {
                problems.add("Conflicting source: " + source + " and path: " + path);
            }
            if(method != null) {
                problems.add("Conflicting source: " + source + " and method: " + method);
            }
        }
        if(method != null && !Util.METHODS.contains(method.toUpperCase())) {
            problems.add("Invalid HTTP method: " + method);
        }
        if(!problems.isEmpty()) {
            throw new RuntimeException("Bad paths element in " + origin + " [ " + String.join(" | ", problems) + " ]");
        }
    }