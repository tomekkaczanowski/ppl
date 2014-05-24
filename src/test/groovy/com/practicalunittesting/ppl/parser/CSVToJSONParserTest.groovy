package com.practicalunittesting.ppl.parser

import com.jayway.jsonassert.JsonAssert
import org.hamcrest.Matchers
import spock.lang.Specification

class CSVToJSONParserTest extends Specification {

    def "should not fail with empty input"() {
        given:
        CSVToJSONParser parser = new CSVToJSONParser()

        when:
        def jsonResult = parser.parse("")

        then:
        jsonResult != null
        jsonResult.equals("")
    }

    def "should parse single line"() {
        given:
        CSVToJSONParser parser = new CSVToJSONParser()
        def input = "Geecon 2014, 18.05.2014, Jan Kowalski, talked about weather"

        when:
        def jsonResult = parser.parse(input)

        then:
        JsonAssert.with(jsonResult)
                .assertThat('facts.fact[0].who', Matchers.equalTo("Jan Kowalski"))
                .assertThat('facts.fact[0].where', Matchers.equalTo("Geecon 2014"))
                .assertThat('facts.fact[0].when', Matchers.equalTo("18.05.2014"))
                .assertThat('facts.fact[0].what', Matchers.equalTo("talked about weather"))
        ;
    }

    def "should parse multiple lines"() {
        given:
        CSVToJSONParser parser = new CSVToJSONParser()
        def input = """\
Geecon 2014, 18.05.2014, Jan Kowalski, talked about weather
Geecon 2013, 12.05.2013, Adam Nowak, never met him
"""

        when:
        def jsonResult = parser.parse(input)

        then:
        JsonAssert.with(jsonResult)
                .assertThat('facts.fact[0].who', Matchers.equalTo("Jan Kowalski"))
                .assertThat('facts.fact[0].where', Matchers.equalTo("Geecon 2014"))
                .assertThat('facts.fact[0].when', Matchers.equalTo("18.05.2014"))
                .assertThat('facts.fact[0].what', Matchers.equalTo("talked about weather"))
                .assertThat('facts.fact[1].who', Matchers.equalTo("Adam Nowak"))
                .assertThat('facts.fact[1].where', Matchers.equalTo("Geecon 2013"))
                .assertThat('facts.fact[1].when', Matchers.equalTo("12.05.2013"))
                .assertThat('facts.fact[1].what', Matchers.equalTo("never met him"))
        ;
    }
}