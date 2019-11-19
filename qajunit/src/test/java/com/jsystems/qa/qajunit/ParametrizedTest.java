package com.jsystems.qa.qajunit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.EnumMap;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParametrizedTest extends ConfigJunit{

    @ParameterizedTest(name = "Parametrized test with value (0)")
    @ValueSource(ints = {5,15,25})
    public void firstParametrizedTest (int number){

        int [] ints = {5,15,25};
        for (int i=0; i <ints.length; i++){
            assertTrue(ints[i] % 5 == 0);
        }
    }

    @ParameterizedTest(name = "Parametrized test with value (0)")
    @ValueSource(strings = {"Hello", "Hello junit", "Hello student"})
    public void secondParametrizedTest (String text){

       assertTrue(text.contains("Hello"));
        }

    @ParameterizedTest(name = "Parametrized test with value (0)")
    @CsvSource(value = {"Hello, 5", "Hello junit 5, 15", "Hello 5!', 25"}, delimiter = ',')
    public void nextParametrizedTest (String text, int number){

        assertTrue(text.contains("Hello"));
        assertTrue(number % 5 == 0);
    }

    @ParameterizedTest(name = "Parametrized test with value {0} and {1}")
    @CsvFileSource(resources = "/plik.csv", delimiter = ',')
    public void csvFileParametrizedTest (String text, int number){

        assertTrue(text.contains("Hello"));
        assertTrue(number % 5 == 0);
    }

    @ParameterizedTest(name = "Parametrized test with value {0} and {1}")
    @EnumSource(value = ParamEnum.class)
    public void enumParametrizedTest (ParamEnum enumtype){

        assertTrue(enumtype.toString().contains("ENUM"));

        String simpleString = "simpleString";
        String simpleString_2 = new String("simpleString");
        String simpleString_3 = new String("simpleString");
        assertTrue(simpleString == "simpleString");
        assertTrue(simpleString == simpleString_2);
        assertTrue(simpleString_2 == simpleString_3);

        int a = 1;
        Integer a_1 = 1;
    }
    enum ParamEnum {
        ENUM_ONE,
        ENUM_TWO
    }

    @ParameterizedTest(name = "Test of Wordpress powers with value {0}")
    @ValueSource(strings = {"1", "10000", "10000000"})
    public void exString (String text){

        String resultString = "Wordpress powers " + text + "% of the internet";
        String expectedString = "Wordpress powers [number]% of the internet";

        assertTrue(resultString.startsWith("Wordpress powers "));
        assertTrue(resultString.endsWith("% of the internet"));
        assertThat(resultString).matches("(Wordpress powers )\\d+(% of the internet)");

        String result = resultString.replace("Wordpress powers ", "").replace("% of the internet", "");
        int resultNumber = Integer.parseInt(result);
        assertTrue(resultNumber > 0);

    }

}

