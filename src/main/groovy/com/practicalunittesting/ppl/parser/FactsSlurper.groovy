package com.practicalunittesting.ppl.parser

import com.practicalunittesting.ppl.Fact
import com.practicalunittesting.ppl.Facts

class FactsSlurper {
    Facts slurp(String input) {
        if (input.isEmpty()) {
            return new Facts();
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
                facts << new Fact(section.where, section.when, person, it)
            }
        }
        new Facts(facts, true)
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
