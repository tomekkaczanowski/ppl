package com.practicalunitesting.ppl

import com.practicalunittesting.ppl.Fact
import spock.lang.Specification

class FactTest extends Specification {

    def "should recognize fact mentions other people"() {
        given:
        def fact = new Fact("where", "when", "John Doe", "mentions _MarylinMonroe, _AmyBamy and _JohnRambo")

        when:
        def mentionedPeople = fact.getMentionedPeople()

        then:
        assert mentionedPeople.contains("John Rambo")
        assert mentionedPeople.contains("Marylin Monroe")
    }
}
