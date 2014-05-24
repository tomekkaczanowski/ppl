package com.practicalunittesting.ppl.parser

import com.jayway.jsonpath.JsonPath
import static com.jayway.jsonpath.Filter.filter
import static com.jayway.jsonpath.Criteria.*
import groovy.json.JsonSlurper

class JSONPersonSearch {
    ArrayList search(String jsonInput, String person) {
//        def slurper = new JsonSlurper()
//        def result = slurper.parseText(jsonInput)
//        println result.facts.fact
//        println "person: $person"
//        def read = JsonPath.read(jsonInput, '$.facts.fact[?(@.who == "' + person + '")]');
//        println read
//        println JsonPath.read(jsonInput, '$.facts.fact[0].who');

//        println JsonPath.read(jsonInput, '$.facts.fact[?]', filter(where("who").eq(person)));

        def json = new JsonSlurper().parseText(jsonInput)

        def found = json.facts
                .fact
                .findAll { it.who == person }

        println found
        println found.class
        println found[0]
        println found[1]

//        println "node: $kowalskiNode"
//
//
//        def nowakNode = json.facts
//                .fact
//                .find { it.who == 'Adam Nowak' }
//
//        println "node: $nowakNode"
        return found
    }
}
