package com.practicalunittesting.ppl

class Fact { String where; String when; String who; String what

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
    }
}
