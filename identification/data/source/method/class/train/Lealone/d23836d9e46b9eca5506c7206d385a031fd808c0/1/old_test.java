    public static void main(String[] args) throws SQLException {
        System.setProperty("lealone.config", "lealone-test.yaml");
        String url = "jdbc:lealone:tcp://localhost:9210/lealone";
        url = "jdbc:lealone:embed:lealone";
        String[] args2 = { "-url", url, "-user", "root" };
        Shell.main(args2);
    }