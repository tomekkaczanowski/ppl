package com.practicalunittesting.ppl

class Fact { String where; String when; String who; String what; String mentionedBy; String[] mentionedPeople

    Fact(String where, String when, String who, String what) {
        this.where = where.trim()
        this.when = when.trim()
        this.who = who.trim()
        this.what = what.trim()
        this.mentionedPeople = findMentionedPeople();
    }
    Fact(Fact fact, String person) {
        this.where = fact.where.trim()
        this.when = fact.when.trim()
        this.who = person.trim()
        this.what = fact.what.trim()
        this.mentionedBy = fact.who.trim()
    }
    // TODO introduce Event class (where + when)
    // TODO add exact date and print date (e.g. 5 maj 2014, vs maj 2014)
    // TODO introduce Person class (who)
    // TODO parse looking for people mentioned (and events?!)

    Set<String> findMentionedPeople() {
        def tokens = new StringTokenizer(what, " ,").collect { it }
        def mentionedPeople = [] as TreeSet
        for (String token : tokens) {
            if (token.startsWith("_")) {
                def cleaned = token.replace('_', '').replaceAll(/(?<=[a-z])(?=[A-Z])/) { ' ' + it }
                mentionedPeople.add(cleaned)
            }
        }
        mentionedPeople
    }
}
