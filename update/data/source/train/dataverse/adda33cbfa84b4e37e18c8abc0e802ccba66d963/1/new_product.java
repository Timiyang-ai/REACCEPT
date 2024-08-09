public static String sanitizeBasicHTML(String unsafe){
        
        if (unsafe == null){
            return null;
        }
        // basic includes: a, b, blockquote, br, cite, code, dd, dl, dt, em, i, li, ol, p, pre, q, small, span, strike, strong, sub, sup, u, ul
        //Whitelist wl = Whitelist.basic().addTags("img", "h1", "h2", "h3", "kbd", "hr", "s", "del");  

        Whitelist wl = Whitelist.basicWithImages().addTags( "h1", "h2", "h3", "kbd", "hr", "s", "del","map","area").addAttributes("img", "usemap")
                .addAttributes("map", "name").addAttributes("area", "shape","coords","href","title","alt")
                .addEnforcedAttribute("a", "target", "_blank");

        return Jsoup.clean(unsafe, wl);
        
    }