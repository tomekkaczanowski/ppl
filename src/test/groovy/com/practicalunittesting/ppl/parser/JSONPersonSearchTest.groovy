package com.practicalunittesting.ppl.parser

import spock.lang.Specification

class JSONPersonSearchTest extends Specification {

    def input = """\
{
    "facts": {
        "fact": [
            {
                "where": "Geecon 2012",
                "when": "04.04.2012",
                "who": "Jan Kowalski",
                "what": "short chat"
            },
            {
                "where": "Geecon 2014",
                "when": "18.05.2014",
                "who": "Jan Kowalski",
                "what": "talked about weather"
            },
            {
                "where": "Geecon 2011",
                "when": "12.05.2011",
                "who": "Jan Kowalski",
                "what": "haven't met"
            },
            {
                "where": "Geecon 2013",
                "when": "12.05.2013",
                "who": "Adam Nowak",
                "what": "never met him"
            }
        ]
    }
}
"""

    def "should find exact person"() {
        given:
        def JSONPersonSearch searcher = new JSONPersonSearch()

        when:
        def kowalski = searcher.search(input, "Jan Kowalski")
        def nowak = searcher.search(input, "Adam Nowak")

        then:
        kowalski != null
        kowalski[0].who == "Jan Kowalski"
        kowalski[1].who == "Jan Kowalski"
        kowalski[2].who == "Jan Kowalski"
        nowak != null
        nowak[0].who == "Adam Nowak"
    }


    def "by default results should be sorted by date descending"() {
        given:
        def JSONPersonSearch searcher = new JSONPersonSearch()

        when:
        def kowalski = searcher.search(input, "Jan Kowalski")

        then:
        kowalski != null
        kowalski[0].when == "12.05.2011"
        kowalski[1].when == "04.04.2012"
        kowalski[2].when == "18.05.2014"
    }

    def "should return empty arraylist for non existing person"() {

        given:
        def JSONPersonSearch searcher = new JSONPersonSearch()

        when:
        def unknown = searcher.search(input, "Gall Anonim")

        then:
        unknown.isEmpty()
    }
}
