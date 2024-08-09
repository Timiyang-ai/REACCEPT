protected String expand(String str) {
        return (_ct = getCallTarget()) != null ? _ct.expand(str) : expand0(str);
    }