package com.practicalunittesting.ppl

class Person {

    final String id
    final String[] categories

    Person(String id, String ... categories) {
        this.id = id
        this.categories = categories
    }
    // TODO not really id
    def getName() {
        id
    }

    boolean hasCategory(String category) {
        boolean matches = false
        categories.each { it ->
            if (it.equalsIgnoreCase(category)) {
                matches = true
            }
        }
        matches
    }
}
