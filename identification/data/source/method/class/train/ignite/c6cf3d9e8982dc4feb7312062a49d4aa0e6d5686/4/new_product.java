public String toString(int precision) {
        StringBuilder sb = new StringBuilder();
        values.forEach((k, v) -> sb.append(String.format("%s = %." + precision + "f\n", k.getPrettyName(), v)));
        return sb.toString();
    }