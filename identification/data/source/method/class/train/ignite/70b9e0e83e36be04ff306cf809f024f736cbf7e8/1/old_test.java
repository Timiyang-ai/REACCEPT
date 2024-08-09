@Test
    public void testParseUpdate() throws Exception {
        checkQuery("update Person set name='Peter'");
        checkQuery("update Person per set name='Peter', old = 5");
        checkQuery("update Person p set name='Peter' limit 20");
        checkQuery("update Person p set name='Peter', old = length('zzz') limit 20");
        checkQuery("update Person p set name=DEFAULT, old = null limit ?");
        checkQuery("update Person p set name=? where old >= ? and old < ? limit ?");
        checkQuery("update Person p set name=(select a.Street from sch2.Address a where a.id=p.addrId), old = " +
            "(select 42) where old = sqrt(?)");
        checkQuery("update Person p set (name, old) = (select 'Peter', 42)");
        checkQuery("update Person p set (name, old) = (select street, id from sch2.Address where id > 5 and id <= ?)");
    }