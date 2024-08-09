@Test
public void parse() {
    // default output
    parse("[]", "", "<json type=\"array\"/>");
    parse("{}", "", "<json type=\"object\"/>");
    parse("{ } ", "", "<json type=\"object\"/>");
    parse("{ \"\\t\" : 0 }", "", "<json type=\"object\"><_0009 type=\"number\">0</_0009></json>");
    parse("{ \"a\" :0 }", "", "<json type=\"object\"><a type=\"number\">0</a></json>");
    parse("{ \"\" : 0 }", "", "<json type=\"object\"><_ type=\"number\">0</_></json>");
    parse("{ \"\" : 0.0e0 }", "", "...<_ type=\"number\">0.0e0</_>");
    parse("{ \"\" : null }", "", "...<_ type=\"null\"/>");
    parse("{ \"\" : true }", "", "...<_ type=\"boolean\">true</_>");
    parse("{ \"\" : {} }", "", "... type=\"object\"><_ type=\"object\"/>");
    parse("{ \"\" : [] }", "", "... type=\"object\"><_ type=\"array\"/>");
    parse("{ \"\" : 0, \"\": 1 }", "",
        "... type=\"object\"><_ type=\"number\">0</_><_ type=\"number\">1</_>");
    parse("{ \"O\" : [ 1 ] }", "", "...<O type=\"array\"><_ type=\"number\">1</_></O>");
    parse("{ \"A\" : [ 0,1 ] }", "",
        "...<A type=\"array\"><_ type=\"number\">0</_><_ type=\"number\">1</_>");
    parse("{ \"\" : 0.0 }", "", "...0.0");

    // merging data types
    parse("[]", "'merge':true()", "<json arrays=\"json\"/>");
    parse("{}", "'merge':true()", "<json objects=\"json\"/>");
    parse("{ } ", "'merge':true()", "<json objects=\"json\"/>");
    parse("{ \"\\t\" : 0 }", "'merge':true()",
        "<json objects=\"json\" numbers=\"_0009\"><_0009>0</_0009></json>");
    parse("{ \"a\" :0 }", "'merge':true()", "<json objects=\"json\" numbers=\"a\"><a>0</a></json>");
    parse("{ \"\" : 0 }", "'merge':true()", "<json objects=\"json\" numbers=\"_\"><_>0</_></json>");
    parse("{ \"\" : 0.0e0 }", "'merge':true()", "...<_>0.0e0</_>");
    parse("{ \"\" : null }", "'merge':true()", "...<_/>");
    parse("{ \"\" : true }", "'merge':true()", "...<_>true</_>");
    parse("{ \"\" : {} }", "'merge':true()", "... objects=\"json _\"><_/>");
    parse("{ \"\" : [] }", "'merge':true()", "... objects=\"json\" arrays=\"_\"><_/>");
    parse("{ \"\" : 0, \"\": 1 }", "'merge':true()",
        "... objects=\"json\" numbers=\"_\"><_>0</_><_>1</_>");
    parse("{ \"O\" : [ 1 ] }", "'merge':true()",
        "... objects=\"json\" arrays=\"O\" numbers=\"_\"><O><_>1</_></O>");
    parse("{ \"A\" : [ 0,1 ] }", "'merge':true()",
        "... objects=\"json\" arrays=\"A\" numbers=\"_\"><A><_>0</_><_>1</_>");

    // errors
    parseError("", "");
    parseError("{", "");
    parseError("{ \"", "");
    parseError("{ \"\" : 00 }", "");
    parseError("{ \"\" : 0. }", "");
    parseError("{ \"\\c\" : 0 }", "");
    parseError("{ \"\" : 0e }", "");
    parseError("{ \"\" : 0.1. }", "");
    parseError("{ \"\" : 0.1e }", "");
    parseError("{ \"a\" : 0 }}", "");
    parseError("{ \"a\" : 0, }", "'format':'RFC4627'");
}