public MemberList getMembers() {
        MemberList list = new MemberList(this.getClient(), this.getSid());
        list.setRequestAccountSid(this.getRequestAccountSid());
        return list;
    }