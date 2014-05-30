package com.practicalunittesting.ppl

class Fact { String where; String when; String who; String what; String mentionedPeople

    // TODO introduce Event class (where + when)
    void setWhere(String where) {
        this.where = where.trim()
    }

    // TODO add exact date and print date (e.g. 5 maj 2014, vs maj 2014)
    void setWhen(String when) {
        this.when = when.trim()
    }

    // TODO introduce Person class (who)
    void setWho(String who) {
        this.who = who.trim()
    }

    // TODO parse looking for people mentioned (and events?!)
    void setWhat(String what) {
        this.what = what.trim()
        this.mentionedPeople = findMentionedPeople();
    }

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
