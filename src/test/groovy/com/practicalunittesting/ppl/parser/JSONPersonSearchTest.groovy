package com.practicalunittesting.ppl.parser

import com.jayway.jsonassert.JsonAssert
import groovy.json.JsonSlurper
import org.hamcrest.Matchers
import spock.lang.Specification

class JSONPersonSearchTest extends Specification {

    def "should find exact person"() {
        given:
        def JSONPersonSearch searcher = new JSONPersonSearch()
        def input = """\
{
    "facts": {
        "fact": [
            {
                "where": "Geecon 2012",
                "when": "16.05.2014",
                "who": "Jan Kowalski",
                "what": "short chat"
            },
            {
                "where": "Geecon 2014",
                "when": "18.05.2014",
                "who": "Jan Kowalski",
                "what": "talked about weather"
            },
            {
                "where": "Geecon 2013",
                "when": "12.05.2013",
                "who": "Adam Nowak",
                "what": "never met him"
            }
        ]
    }
}
"""

        when:
        def kowalski = searcher.search(input, "Jan Kowalski")
        def nowak = searcher.search(input, "Adam Nowak")

        then:
        kowalski != null
        println kowalski[0]
        kowalski[0].who == "Jan Kowalski"
        kowalski[1].who == "Jan Kowalski"
        nowak[0].who == "Adam Nowak"
        JsonAssert.with(kowalski)
                .assertThat('who', Matchers.equalTo("Jan Kowalski"))
                .assertThat('where', Matchers.equalTo("Geecon 2014"))
                .assertThat('when', Matchers.equalTo("18.05.2014"))
                .assertThat('what', Matchers.equalTo("talked about weather"));

        JsonAssert.with(nowak)
                .assertThat('facts.fact[0].who', Matchers.equalTo("Adam Nowak"))
                .assertThat('facts.fact[0].where', Matchers.equalTo("Geecon 2013"))
                .assertThat('facts.fact[0].when', Matchers.equalTo("12.05.2013"))
                .assertThat('facts.fact[0].what', Matchers.equalTo("never met him"))
    }
}
