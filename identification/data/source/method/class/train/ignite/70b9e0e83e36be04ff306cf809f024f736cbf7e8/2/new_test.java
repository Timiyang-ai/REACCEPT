@Test
    public void testParseInsert() throws Exception {
        /* Plain rows w/functions, operators, defaults, and placeholders. */
        checkQuery("insert into Person(old, name) values(5, 'John')");
        checkQuery("insert into Person(name) values(null)");
        checkQuery("insert into Person() values()");
        checkQuery("insert into Person(name) values(null), (null)");
        checkQuery("insert into Person(name) values(null),");
        checkQuery("insert into Person(name, parentName) values(null, null), (?, ?)");
        checkQuery("insert into Person(old, name) values(5, 'John',), (6, 'Jack')");
        checkQuery("insert into Person(old, name) values(5 * 3, null,)");
        checkQuery("insert into Person(old, name) values(ABS(-8), 'Max')");
        checkQuery("insert into Person(old, name) values(5, 'Jane'), (null, null), (6, 'Jill')");
        checkQuery("insert into Person(old, name, parentName) values(8 * 7, null, 'Unknown')");
        checkQuery("insert into Person(old, name, parentName) values" +
            "(2016 - 1828, CONCAT('Leo', 'Tolstoy'), CONCAT(?, 'Tolstoy'))," +
            "(?, 'AlexanderPushkin', null)," +
            "(ABS(1821 - 2016), CONCAT('Fyodor', null, UPPER(CONCAT(SQRT(?), 'dostoevsky'))), null),");
        checkQuery("insert into Person(date, old, name, parentName, addrId) values " +
            "('20160112', 1233, 'Ivan Ivanov', 'Peter Ivanov', 123)");
        checkQuery("insert into Person(date, old, name, parentName, addrId) values " +
            "(CURRENT_DATE(), RAND(), ASCII('Hi'), INSERT('Leo Tolstoy', 4, 4, 'Max'), ASCII('HI'))");
        checkQuery("insert into Person(date, old, name, parentName, addrId) values " +
            "(TRUNCATE(TIMESTAMP '2015-12-31 23:59:59'), POWER(3,12), NULL, NULL, NULL)");
        checkQuery("insert into Person SET old = 5, name = 'John'");
        checkQuery("insert into Person SET name = CONCAT('Fyodor', null, UPPER(CONCAT(SQRT(?), 'dostoevsky'))), " +
            "old = select (5, 6)");
        checkQuery("insert into Person(old, name) select ASCII(parentName), INSERT(parentName, 4, 4, 'Max') from " +
            "Person where date='2011-03-12'");

        /* Subqueries. */
        checkQuery("insert into Person(old, name) select old, parentName from Person");
        checkQuery("insert into Person(old, name) direct sorted select old, parentName from Person");
        checkQuery("insert into Person(old, name) sorted select old, parentName from Person where old > 5");
        checkQuery("insert into Person(old, name) select 5, 'John'");
        checkQuery("insert into Person(old, name) select p1.old, 'Name' from person p1 join person p2 on " +
            "p2.name = p1.parentName where p2.old > 30");
        checkQuery("insert into Person(old) select 5 from Person UNION select street from sch2.Address limit ? " +
            "offset ?");
    }