protected String expand(String str) {
        return (_ct = getCallTarget(Capsule.class)) != null ? _ct.expand(str) : expand0(str);
    }