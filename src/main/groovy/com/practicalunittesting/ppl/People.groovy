package com.practicalunittesting.ppl

class People {

    Person[] people

    People(Person[] people) {
        this.people = people
    }

    Person[] getPeopleByCategory(String category) {
        people.findAll { person ->
            person.hasCategory(category)
        }
    }
}
