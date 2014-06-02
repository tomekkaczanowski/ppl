package com.practicalunittesting.ppl.parser

import spock.lang.Specification

class CategorySearchTest extends Specification {

    def people = """\
_JanJugorodzina,[jug:rodzina]
_MarekRodzina,[rodzina]
_AlbertJugowicz
"""
    def facts = """\
[Geecon 2014, 18.05.2014]
_JanJugorodzina
fact about jan jugorodzina
_MarekRodzina
fact about marek rodzina
_AlbertJugowicz
fact about albert jugowicz
"""
    def "should find all facts of people from given category"() {
        // TODO should find facts by Fact.getFactsOfCategory("jug") etc.
    }
}
