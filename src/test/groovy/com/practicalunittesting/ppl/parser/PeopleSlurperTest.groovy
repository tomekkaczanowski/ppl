package com.practicalunittesting.ppl.parser

import spock.lang.Specification

class PeopleSlurperTest extends Specification {

    def input = """\
_Jan Kowalski
a,b,c
_Jan Kot
a,b
_MarekZegarek
b,c
"""

    def "should read people from text"() {
        PeopleSlurper slurper = new PeopleSlurper()
        slurper.slurp(input)
    }

    def "should reject duplicated people"() {
        given:
        true

        when:
        true

        then:
        true == false
    }
}
