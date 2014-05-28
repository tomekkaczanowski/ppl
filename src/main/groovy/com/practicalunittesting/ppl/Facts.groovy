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
            fact.who == person
        }
    }

    Fact[] getFactsForEvent(String event) {
        return facts.findAll { fact ->
            fact.where == event
        }
    }

    boolean hasFacts() {
        return size() != 0
    }
}
