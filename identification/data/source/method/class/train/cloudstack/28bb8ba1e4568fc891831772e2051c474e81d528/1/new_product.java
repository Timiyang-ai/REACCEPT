protected static Session openConnectionSession(Connection conn) throws IOException, InterruptedException {
        Session sess = conn.openSession();
        return sess;
    }