package com.practicalunittesting.ppl.parser

import groovy.json.JsonBuilder

class CSVToJSONParser {
    String parse(String input) {
        if (input.isEmpty()) {
            return ""
        }
        def json = new JsonBuilder()

        json.facts {
            input.eachLine {
            String[] tokens = it.split(",");
                fact {
                    where tokens[0].trim()
                    when tokens[1].trim()
                    who tokens[2].trim()
                    what tokens[3].trim()
                }
            }

        }
        return json;
    }
}
