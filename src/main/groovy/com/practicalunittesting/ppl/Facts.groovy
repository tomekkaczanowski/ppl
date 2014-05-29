package com.practicalunittesting.ppl

class Facts {

    def facts

    int size() {
        facts.size()
    }

    Facts() {
        this.facts = []
    }

    Facts(facts) {
        this.facts = facts;
    }

    Facts getFactsForPerson(String person) {
        new Facts(facts.findAll { fact ->
            (fact.who.toUpperCase() =~ ".*${person.toUpperCase()}.*").matches()
        })
    }

    Facts getFactsForEvent(String event) {
        new Facts(facts.findAll { fact ->
            (fact.where.toUpperCase() =~ ".*${event.toUpperCase()}.*").matches()
        })
    }

    Set<String> findPeople() {
        def people = [] as TreeSet
        facts.each {
            people << it.who
        }
        people
    }

    Set<String> findEvents() {
        def events = [] as TreeSet
        facts.each {
            events << it.where
        }
        events
    }
}
