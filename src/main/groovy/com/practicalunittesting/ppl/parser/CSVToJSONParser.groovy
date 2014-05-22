package com.practicalunittesting.ppl.parser

import groovy.json.JsonBuilder

class CSVToJSONParser {
    String parse(String input) {
        if (input.isEmpty()) {
            return ""
        }
        def json = new JsonBuilder()

        def facts = []

        input.eachLine {
            String[] tokens = it.split(",");
            facts << new Fact(where: tokens[0], when: tokens[1], who: tokens[2], what: tokens[3])
        }

        json.facts (
            facts.each {
                fact (
                "bum" : "cyk"
                )
            }
        )
//        json.facts {
//            fact facts.collect {
//                where: it.where
//                when: it.when
//                who: it.who
//                what: it.what
//            }
//        }
        return json;
    }
}

class Fact { String where; String when; String who; String what }
