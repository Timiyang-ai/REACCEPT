public Session getCurrentSession() {
        Request req = requests.getCurrent();
        if (req != null) {
            return req.getSession();
        }

        return null;
    }