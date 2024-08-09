@Test
    public void testParseMerge() throws Exception {
        /* Plain rows w/functions, operators, defaults, and placeholders. */
        checkQuery("merge into Person(old, name) values(5, 'John')");
        checkQuery("merge into Person(name) values(DEFAULT)");
        checkQuery("merge into Person(name) values(DEFAULT), (null)");
        checkQuery("merge into Person(name, parentName) values(DEFAULT, null), (?, ?)");
        checkQuery("merge into Person(old, name) values(5, 'John',), (6, 'Jack')");
        checkQuery("merge into Person(old, name) values(5 * 3, DEFAULT,)");
        checkQuery("merge into Person(old, name) values(ABS(-8), 'Max')");
        checkQuery("merge into Person(old, name) values(5, 'Jane'), (DEFAULT, DEFAULT), (6, 'Jill')");
        checkQuery("merge into Person(old, name, parentName) values(8 * 7, DEFAULT, 'Unknown')");
        checkQuery("merge into Person(old, name, parentName) values" +
            "(2016 - 1828, CONCAT('Leo', 'Tolstoy'), CONCAT(?, 'Tolstoy'))," +
            "(?, 'AlexanderPushkin', null)," +
            "(ABS(1821 - 2016), CONCAT('Fyodor', null, UPPER(CONCAT(SQRT(?), 'dostoevsky'))), DEFAULT)");
        checkQuery("merge into Person(date, old, name, parentName, addrId) values " +
            "('20160112', 1233, 'Ivan Ivanov', 'Peter Ivanov', 123)");
        checkQuery("merge into Person(date, old, name, parentName, addrId) values " +
            "(CURRENT_DATE(), RAND(), ASCII('Hi'), INSERT('Leo Tolstoy', 4, 4, 'Max'), ASCII('HI'))");
        checkQuery("merge into Person(date, old, name, parentName, addrId) values " +
            "(TRUNCATE(TIMESTAMP '2015-12-31 23:59:59'), POWER(3,12), NULL, DEFAULT, DEFAULT)");
        checkQuery("merge into Person(old, name) select ASCII(parentName), INSERT(parentName, 4, 4, 'Max') from " +
            "Person where date='2011-03-12'");

        /* KEY clause. */
        checkQuery("merge into Person(_key, old, name) key(_key) values('a', 5, 'John')");
        checkQuery("merge into SCH3.Person(id, old, name) key(id) values(1, 5, 'John')");
        checkQuery("merge into SCH3.Person(_key, old, name) key(_key) values(?, 5, 'John')");
        checkQuery("merge into SCH3.Person(_key, id, old, name) key(_key, id) values(?, ?, 5, 'John')");
        assertParseThrows("merge into Person(old, name) key(name) values(5, 'John')", IgniteSQLException.class,
            "Invalid column name in KEYS clause of MERGE - it may include only key and/or affinity columns: NAME");
        assertParseThrows("merge into SCH3.Person(id, stuff, old, name) key(stuff) values(1, 'x', 5, 'John')",
            IgniteSQLException.class, "Invalid column name in KEYS clause of MERGE - it may include only key and/or " +
                "affinity columns: STUFF");

        /* Subqueries. */
        checkQuery("merge into Person(old, name) select old, parentName from Person");
        checkQuery("merge into Person(old, name) select old, parentName from Person where old > 5");
        checkQuery("merge into Person(old, name) select 5, 'John'");
        checkQuery("merge into Person(old, name) select p1.old, 'Name' from person p1 join person p2 on " +
            "p2.name = p1.parentName where p2.old > 30");
        checkQuery("merge into Person(old) select 5 from Person UNION select street from sch2.Address limit ? " +
            "offset ?");
    }