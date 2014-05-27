package com.practicalunittesting.ppl.parser

import groovy.json.JsonSlurper

class JSONPersonSearch {
    ArrayList search(String jsonInput, String person) {
        def json = new JsonSlurper().parseText(jsonInput)

        def found = json.facts
                .fact
                .findAll { it.who == person }
        return found
    }
}
