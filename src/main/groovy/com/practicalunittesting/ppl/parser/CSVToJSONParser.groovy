package com.practicalunittesting.ppl.parser

import com.practicalunittesting.ppl.Fact
import groovy.json.JsonBuilder

class CSVToJSONParser {
    String parse(String input) {
        if (input.isEmpty()) {
            return ""
        }
        def facts = []

        input.eachLine {
            String[] tokens = it.split(",");
            facts << new Fact(where: tokens[0], when: tokens[1], who: tokens[2], what: tokens[3])
        }

        def builder = new JsonBuilder()

        builder.facts {
            fact(
                    facts.collect{
                        Fact a -> [where: a.where, when: a.when, who: a.who, what: a.what]
                    }
            )
        }
        println builder.toPrettyString()
        return builder.toPrettyString()
    }
}
