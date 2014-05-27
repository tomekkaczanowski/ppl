package com.practicalunittesting.ppl.parser

import com.jayway.jsonassert.JsonAssert
import org.hamcrest.Matchers
import spock.lang.Specification

class TextToJSONParserTest extends Specification {

    def "should not fail with empty input"() {
        given:
        TextToJSONParser parser = new TextToJSONParser()

        when:
        def jsonResult = parser.parse("")

        then:
        jsonResult != null
        jsonResult.equals("")
    }

    def "should parse multiple lines"() {
        given:
        TextToJSONParser parser = new TextToJSONParser()
        def input = """\
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

        when:
        def jsonResult = parser.parse(input)

        then:
        for ( i in 0..5 ) {
            JsonAssert.with(jsonResult)
                    .assertThat("facts.fact[$i].where", Matchers.equalTo("Geecon 2014"))
                    .assertThat("facts.fact[$i].when", Matchers.equalTo("18.05.2014"))
        }

        JsonAssert.with(jsonResult)
                .assertThat("facts.fact[0].who", Matchers.equalTo("Jan Kowalski"))
                .assertThat("facts.fact[1].who", Matchers.equalTo("Jan Kowalski"))
                .assertThat("facts.fact[2].who", Matchers.equalTo("Jan Kot"))
                .assertThat("facts.fact[3].who", Matchers.equalTo("Adam Nowak"))
                .assertThat("facts.fact[4].who", Matchers.equalTo("Adam Nowak"))
                .assertThat("facts.fact[5].who", Matchers.equalTo("Adam Nowak"))

        for ( i in 6..7 ) {
            JsonAssert.with(jsonResult)
                    .assertThat("facts.fact[$i].where", Matchers.equalTo("Geecon 2015"))
                    .assertThat("facts.fact[$i].when", Matchers.equalTo("17.05.2015"))
                    .assertThat("facts.fact[$i].who", Matchers.equalTo("Jan Kowalski"))
        }

        JsonAssert.with(jsonResult)
                .assertThat("facts.fact[0].what", Matchers.equalTo("jkow 2014 1"))
                .assertThat("facts.fact[1].what", Matchers.equalTo("jkow 2014 2"))
                .assertThat("facts.fact[2].what", Matchers.equalTo("jkot 2014"))
                .assertThat("facts.fact[3].what", Matchers.equalTo("an 2014 1"))
                .assertThat("facts.fact[4].what", Matchers.equalTo("an 2014 2"))
                .assertThat("facts.fact[5].what", Matchers.equalTo("an 2014 3"))
                .assertThat("facts.fact[6].what", Matchers.equalTo("jkow 2015 1"))
                .assertThat("facts.fact[7].what", Matchers.equalTo("jkow 2015 2"))
    }
}
