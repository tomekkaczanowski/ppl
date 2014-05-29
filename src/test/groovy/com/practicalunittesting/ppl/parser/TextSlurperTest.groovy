package com.practicalunittesting.ppl.parser

import spock.lang.Specification

class TextSlurperTest extends Specification {

    def "should not fail with empty input"() {
        given:
        TextSlurper slurper = new TextSlurper()

        when:
        def result = slurper.slurp("")

        then:
        result != null
        !result.hasFacts()
    }

    def input = """\
[Geecon 2014, 18.05.2014]
_Jan Kowalski
jkow 2014 1
jkow 2014 2
_Jan Kot
jkot 2014
_Adam Nowak
an 2014 1
an 2014 2
an 2014 3

[Geecon 2015, 17.05.2015]
_Jan Kowalski
jkow 2015 1
jkow 2015 2
"""
    def "should find unique people"() {
        given:
        TextSlurper slurper = new TextSlurper()

        when:
        def result = slurper.slurp(input)

        then:
        def people = result.findPeople()
        people.size() == 3
        people.contains("Jan Kowalski")
        people.contains("Jan Kot")
        people.contains("Adam Nowak")
    }

    def "should find unique events"() {
        given:
        TextSlurper slurper = new TextSlurper()

        when:
        def result = slurper.slurp(input)

        then:
        def events = result.findEvents()
        events.size() == 2
        events.contains("Geecon 2014")
        events.contains("Geecon 2015")
    }

    def "should find facts for people and events"() {
        given:
        TextSlurper slurper = new TextSlurper()

        when:
        def result = slurper.slurp(input)

        then:
        result.hasFacts()
        result.size() == 8
        def kowalskiFacts = result.getFactsForPerson("Jan Kowalski")
        kowalskiFacts.size() == 4
        def kotFacts = result.getFactsForPerson("Jan Kot")
        kotFacts.size() == 1
        def nowakFacts = result.getFactsForPerson("Adam Nowak")
        nowakFacts.size() == 3

        def geecon2014Facts = result.getFactsForEvent("Geecon 2014")
        geecon2014Facts.size() == 6
        def geecon2015Facts = result.getFactsForEvent("Geecon 2015")
        geecon2015Facts.size() == 2
    }

    def "should find facts ignoring case"() {
        given:
        TextSlurper slurper = new TextSlurper()

        when:
        def result = slurper.slurp(input)

        then:
        def nowakFacts = result.getFactsForPerson("adam nowak")
        nowakFacts.size() == 3

        def geecon2014Facts = result.getFactsForEvent("geecon 2014")
        geecon2014Facts.size() == 6
    }

    def "should find facts by regexp"() {
        given:
        TextSlurper slurper = new TextSlurper()

        when:
        def result = slurper.slurp(input)

        then:
        result.getFactsForPerson("adam").size() == 3
        result.getFactsForPerson("nowak").size() == 3
        result.getFactsForPerson("ow").size() == 7 // nowak + kowalski
        result.getFactsForPerson("o").size() == 8 // nowak + kowalski + kot
        result.getFactsForEvent("2014").size() == 6
        result.getFactsForEvent("2015").size() == 2
        result.getFactsForEvent("eeco").size() == 8
    }
}
