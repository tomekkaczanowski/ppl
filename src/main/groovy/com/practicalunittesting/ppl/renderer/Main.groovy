package com.practicalunittesting.ppl.renderer

import com.practicalunittesting.ppl.Fact
import com.practicalunittesting.ppl.parser.FactsSlurper
import groovy.xml.MarkupBuilder

class Main {

    static def input = """\
[Geecon 2014, 18.05.2014]
_Jan Kowalski
jkow 2014 1
jkow 2014 2
_Jan Kot
jkot 2014
_Adam Nowak
an 2014 1
an 2014 2
an 2014 3

[Geecon 2015, 17.05.2015]
_Jan Kowalski
jkow 2015 1
jkow 2015 2
"""

    public static void main(String[] args) {
        StringWriter writer = new StringWriter()

        FactsSlurper slurper = new FactsSlurper()

//        def result = slurper.slurp(new File('/home/tomek/data/2013_lipiec.txt').text)
        def result = slurper.slurp(input)
        def build = new MarkupBuilder(writer)
        build.html{
            head{
                style(type:"text/css", '''
    .bigPaddingAndGreen {
        margin: 30px;
        padding: 30px;
        background-color: #00FF00
    }
    ''')
            }
            body{
                table{
                    tr{
                        td('class':'bigPaddingAndGreen', "Our very own moose:")
                        td('class':'bigPaddingAndGreen'){
                            img(src:"", border:0)
                        }
                    }
                }
                for (String person : result.findPeople() ) {
                    h1"$person"
                    def facts = result.getFactsForPerson(person)
                    for (String event : facts.findEvents() ) {
                        h2"$event"
                        for (Fact fact : facts.getFactsForEvent(event).getFacts() ) {
                            br"$fact.when $fact.what"
                        }
                    }
                }

                ul {
                    for (String person : result.findPeople() ) {
                        li "$person"
                        def facts = result.getFactsForPerson(person)
                        ul  {
                            for (String event : facts.findEvents() ) {
                                li "$event"
                                ul {
                                    for (Fact fact : facts.getFactsForEvent(event).getFacts() ) {
                                        li"$fact.when $fact.what"
                                    }
                                }
                            }
                        }
                    }
                }

                for (String event : result.findEvents() ) {
                    h1"$event"
                    def facts = result.getFactsForEvent(event)
                    for (String person : facts.findPeople() ) {
                        h2"$person"
                        for (Fact fact : facts.getFactsForPerson(person).getFacts() ) {
                            br"$fact.when $fact.what"
                        }
                    }
                }

                ul {
                    for (String event : result.findEvents() ) {
                        li "$event"
                        def facts = result.getFactsForEvent(event)
                        ul  {
                            for (String person : facts.findPeople() ) {
                                li "$person"
                                ul {
                                    for (Fact fact : facts.getFactsForPerson(person).getFacts() ) {
                                        li"$fact.when $fact.what"
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        println writer.toString()

        println(new StringTokenizer("mentions _MarylinMonroe, _AmyBamy and _JohnRambo", " ,").collect { it })
    }
}
