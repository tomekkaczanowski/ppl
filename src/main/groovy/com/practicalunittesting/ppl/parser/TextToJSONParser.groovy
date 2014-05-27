package com.practicalunittesting.ppl.parser

import groovy.json.JsonBuilder

class TextToJSONParser {
    String parse(String input) {
        if (input.isEmpty()) {
            return ""
        }

        def facts = []
        def section
        def person
        input.eachLine {
            if (isSection(it)) {
                section = parseSection(it)
            }
            else if (isPerson(it)) {
                person = parsePerson(it)
            }
            else if (!it.isEmpty()) {
                facts << new Fact(where: section.where, when: section.when, who: person, what: it)
            }
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

    def parsePerson(String line) {
        return line.replaceAll('_', '')
    }

    boolean isPerson(String line) {
        return line.startsWith('_')
    }

    def parseSection(String line) {
        String noBraces = line.replaceAll("\\[", "").replaceAll("\\]", "");
        String[] tokens = noBraces.split(",");
        return new Section(tokens[0],
                tokens[1])
    }

    boolean isSection(String line) {
        return line.startsWith('[')
    }
    class Section {

        private String where
        private String when

        Section(String where, String when) {
            this.when = when
            this.where = where
        }

    }
}
