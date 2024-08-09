public String toString(int precition) {
        StringBuilder sb = new StringBuilder();
        values.forEach((k, v) -> {
            sb.append(String.format("%s = %." + precition + "f\n", k.getPrettyName(), v));
        });
        return sb.toString();
    }