    private static void listTest() {
        List<String> list = new ArrayList<String>();

        // add/get
        list.add("first");
        list.add("second");
        out("List.add/get(0): " + list.get(0));
        out("List.add/get(1): " + list.get(1));
        list.add(0, "newfirst");
        out("List.add/get(0): " + list.get(0));
        out("List.add/get(1): " + list.get(1));

        // size
        out("List.size: " + list.size());

        // addAll
        List<String> list2 = new ArrayList<String>();
        list2.add("3");
        list2.add("4");
        list.addAll(list2);
        out("List.addAll: " + list.size());
    }