package com.practicalunitesting.ppl

import com.practicalunittesting.ppl.People
import com.practicalunittesting.ppl.Person
import spock.lang.Specification

class PeopleTest extends Specification {

    def "should recognize categories"() {
        given:
        Person jugger = new Person("_JanJugowicz", "jug")
        Person jugAndFamily = new Person("_MarekJugowicz", "jug", "family")
        Person familyMember = new Person("_ZbyszekKrewniak", "family")

        People people = new People(jugger, jugAndFamily, familyMember)

        when:
        Person[] jugPpl = people.getPeopleByCategory("jug")
        Person[] familyPpl = people.getPeopleByCategory("family")

        then:
        jugPpl != null
        jugPpl.size() == 2
        jugPpl.contains(jugger)
        jugPpl.contains(jugAndFamily)
        familyPpl != null
        familyPpl.size() == 2
        familyPpl.contains(familyMember)
        familyPpl.contains(jugAndFamily)
    }
}