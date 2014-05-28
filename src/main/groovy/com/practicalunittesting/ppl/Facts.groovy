package com.practicalunittesting.ppl

class Facts {

    def facts = []

    int size() {
        facts.size()
    }

    void add(Fact fact) {
        facts << fact
    }

    Fact[] getFactsForPerson(String person) {
        return facts.findAll { fact ->
            def who = fact.who.toUpperCase()
            who == person.toUpperCase() || (who =~ ".*${person.toUpperCase()}.*").matches()
        }
    }

    Fact[] getFactsForEvent(String event) {
        return facts.findAll { fact ->
            def where = fact.where.toUpperCase()
            where == event.toUpperCase() || (where =~ ".*${event.toUpperCase()}.*").matches()
        }
    }

    boolean hasFacts() {
        return size() != 0
    }
}
