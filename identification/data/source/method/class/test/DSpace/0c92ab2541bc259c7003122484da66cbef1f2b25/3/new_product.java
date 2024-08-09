public String getCurrentSessionId() {
        Request req = requests.getCurrent();
        if (req != null) {
            Session session = req.getSession();
            if (session != null) {
                return session.getSessionId();
            }
        }

        return null;
    }