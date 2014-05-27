package com.practicalunittesting.ppl.parser

import groovy.json.JsonSlurper

class JSONPersonSearch {
    ArrayList search(String jsonInput, String person) {
        def json = new JsonSlurper().parseText(jsonInput)

        def found = json.facts
                .fact
                .findAll { it.who == person }
        return found.sort {  a, b ->
            def aDate =    Date.parse( "dd.MM.yyyy", a.when )
            def bDate =    Date.parse( "dd.MM.yyyy", b.when )
            aDate <=> bDate
        }
    }
}
