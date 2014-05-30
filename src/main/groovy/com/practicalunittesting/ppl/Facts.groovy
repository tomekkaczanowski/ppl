package com.practicalunittesting.ppl

class Facts {

    def facts

    int size() {
        facts.size()
    }

    Facts() {
        this.facts = []
    }

    // TODO do not like the boolean API here
    Facts(facts, boolean lookForNew) {
        def newFacts = []
        if (lookForNew) {
            facts.each { fact ->
                if (fact.mentionedPeople != null && fact.mentionedPeople.size() > 0) {
                    fact.mentionedPeople.each { person ->
                        newFacts += new Fact(fact, person)
                    }
                }
            }
        }
        this.facts = facts + newFacts
    }

    Facts getFactsForPerson(String person) {
        new Facts(facts.findAll { fact ->
            (fact.who.toUpperCase() =~ ".*${person.toUpperCase()}.*").matches()
        }, false)
    }

    Facts getFactsForEvent(String event) {
        new Facts(facts.findAll { fact ->
            (fact.where.toUpperCase() =~ ".*${event.toUpperCase()}.*").matches()
        }, false)
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
