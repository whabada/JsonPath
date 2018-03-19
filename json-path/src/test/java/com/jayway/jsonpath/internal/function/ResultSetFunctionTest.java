package com.jayway.jsonpath.internal.function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.runners.Parameterized.Parameters;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Configurations;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines functional tests around executing functions on result sets.
 */
@RunWith(Parameterized.class)
public class ResultSetFunctionTest extends BaseFunctionTest {

    private static final Logger logger = LoggerFactory.getLogger(ResultSetFunctionTest.class);

    private Configuration conf = Configurations.GSON_CONFIGURATION;

    public ResultSetFunctionTest(Configuration conf) {
        logger.debug("Testing with configuration {}", conf.getClass().getName());
        this.conf = conf;
    }

    @Parameters
    public static Iterable<Configuration> configurations() {
        return Configurations.configurations();
    }

    @Test
    public void testMaxOfDoublesResultSet() {
        verifyExampleFunction(conf, "$.store.book[*].price.max()", 22.99);
        verifyExampleFunction(conf, "$.store..price.max()", 22.99);
    }

    @Test
    public void testMinOfDoublesResultSet() {
        verifyExampleFunction(conf, "$.store.book[*].price.min()", 8.95);
        verifyExampleFunction(conf, "$.store..price.min()", 8.95);
    }

    @Test
    public void testSumOfDoublesResultSet() {
        verifyExampleFunction(conf, "$.store.book[*].price.sum()", 53.92);
        verifyExampleFunction(conf, "$.store..price.sum()", 73.87);
    }

    @Test
    public void testAvgOfDoublesResultSet() {
        verifyExampleFunction(conf, "$.store.book[*].price.avg()", 13.48);
        verifyExampleFunction(conf, "$.store..price.avg()", 14.774000000000001);
    }

    @Test
    public void testLengthOfDoublesResultSet() {
        verifyExampleFunction(conf, "$.store.book[*].price.length()", 4);
        verifyExampleFunction(conf, "$.store..price.length()", 5);
    }

    @Test
    public void testLengthOfBooksResultSet() {
        verifyExampleFunction(conf, "$.store.book.length()", 4);
    }

    @Test
    public void testSumOfResultSet(){

        //     conf =  Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider())
        //             .options(Option.ALWAYS_RETURN_LIST, Option.SUPPRESS_EXCEPTIONS).build();


        String json = "{\n" +
                "    \"store\": {\n" +
                "        \"book\": [\n" +
                "            {\n" +
                "                \"category\": \"reference\",\n" +
                "                \"author\": \"Nigel Rees\",\n" +
                "                \"title\": \"Sayings of the Century\",\n" +
                "                \"price\": 8.95\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Evelyn Waugh\",\n" +
                "                \"title\": \"Sword of Honour\",\n" +
                "                \"price\": 12.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Herman Melville\",\n" +
                "                \"title\": \"Moby Dick\",\n" +
                "                \"isbn\": \"0-553-21311-3\",\n" +
                "                \"price\": 8.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"J. R. R. Tolkien\",\n" +
                "                \"title\": \"The Lord of the Rings\",\n" +
                "                \"isbn\": \"0-395-19395-8\",\n" +
                "                \"price\": 22.99\n" +
                "            }\n" +
                "        ],\n" +
                "        \"bicycle\": {\n" +
                "            \"color\": \"red\",\n" +
                "            \"price\": 19.95\n" +
                "        }\n" +
                "    },\n" +
                "    \"expensive\": 10\n" +
                "}";
        Object expectedValue = 53.92;
        String pathExpr = "$.store.book[*].price.sum()";
        Object result = executeQuery(conf, pathExpr, json);
        assertThat(conf.jsonProvider().unwrap(result)).isEqualTo(expectedValue);
    }

    @Test
    public void testSumOfFehlersumme(){
        String json = "[{\n" +
                "\t\t\"PARTNERNAME_NEU\": \"DELTA                         \",\n" +
                "\t\t\"HAUS_NR_NEU\": null,\n" +
                "\t\t\"FEHLER_EINRICHTUNG_DTM\": 1,\n" +
                "\t\t\"PARTNERNAME_ALT\": \"Jeansstoff-Test               \",\n" +
                "\t\t\"FEHLER_PARTNER_TYP_KZ\": 1,\n" +
                "\t\t\"FEHLER_GEBURT_DTM\": 1,\n" +
                "\t\t\"FEHLER_HERKUNFT_KZ\": 0,\n" +
                "\t\t\"FEHLER_GUELTIG_VON_DTM\": 1,\n" +
                "\t\t\"SCHLIESSUNG_DTM_NEU\": \"2015-02-13\",\n" +
                "\t\t\"FEHLER_NICHT_MELDEREL_MM\": 0,\n" +
                "\t\t\"HAUS_NR_ALT\": null,\n" +
                "\t\t\"PARTNER_TYP_KZ_NEU\": \" \",\n" +
                "\t\t\"FEHLER_PARTNERNAME\": 1,\n" +
                "\t\t\"PRT_NR_ALT\": 1104920036,\n" +
                "\t\t\"VTR_NR_ALT\": 5000100122,\n" +
                "\t\t\"ORTSNAME_NEU\": null,\n" +
                "\t\t\"FEHLER_ORTSNAME\": 0,\n" +
                "\t\t\"PRT_NR_NEU\": 0,\n" +
                "\t\t\"LFD_NR\": 1,\n" +
                "\t\t\"VTR_NR_NEU\": 0,\n" +
                "\t\t\"FEHLER_POSTLEITZAHL\": 0,\n" +
                "\t\t\"EINRICHTUNG_DTM_NEU\": \"2015-02-13\",\n" +
                "\t\t\"FEHLER_LAND_KZ\": 0,\n" +
                "\t\t\"ORTSNAME_ALT\": null,\n" +
                "\t\t\"NICHT_MELDEREL_MM_NEU\": \" \",\n" +
                "\t\t\"LAND_KZ_NEU\": null,\n" +
                "\t\t\"FEHLER_HAUS_NR\": 0,\n" +
                "\t\t\"STRASSENNAME_ALT\": null,\n" +
                "\t\t\"FEHLER_PRT_NR\": 1,\n" +
                "\t\t\"STRASSENNAME_NEU\": null,\n" +
                "\t\t\"FEHLER_HISTORISCH_VTR_KZ\": 0,\n" +
                "\t\t\"DATEI_NR_ALT\": 1,\n" +
                "\t\t\"FEHLER_LFD_NR\": 0,\n" +
                "\t\t\"FEHLER_INAKTIV_MM\": 0,\n" +
                "\t\t\"EINRICHTUNG_DTM_ALT\": \"0001-01-01\",\n" +
                "\t\t\"LAND_KZ_ALT\": null,\n" +
                "\t\t\"W_VORNAMEN_ZUSATZB_ALT\": \"                              \",\n" +
                "\t\t\"FEHLER_PARTNERART_KZ\": 1,\n" +
                "\t\t\"NICHT_MELDEREL_MM_ALT\": \" \",\n" +
                "\t\t\"HISTORISCH_VTR_KZ_NEU\": \" \",\n" +
                "\t\t\"FEHLER_W_VORNAMEN_ZUSATZB\": 0,\n" +
                "\t\t\"W_VORNAMEN_ZUSATZB_NEU\": \"                              \",\n" +
                "\t\t\"DATEI_NR_NEU\": 15,\n" +
                "\t\t\"HISTORISCH_VTR_KZ_ALT\": \" \",\n" +
                "\t\t\"FEHLER_DATEI_NR\": 1,\n" +
                "\t\t\"FEHLER_VTR_NR\": 1,\n" +
                "\t\t\"POSTLEITZAHL_NEU\": null,\n" +
                "\t\t\"PARTNER_TYP_KZ_ALT\": \"N\",\n" +
                "\t\t\"FEHLER_NUMMER_ALT\": 0,\n" +
                "\t\t\"FEHLER_VORNAME_ZUSATZBEZ\": 1,\n" +
                "\t\t\"HERKUNFT_KZ_NEU\": \" \",\n" +
                "\t\t\"GUELTIG_BIS_DTM_ALT\": \"9999-12-31\",\n" +
                "\t\t\"PARTNERART_KZ_ALT\": 1,\n" +
                "\t\t\"FEHLER_GUELTIG_BIS_DTM\": 1,\n" +
                "\t\t\"GUELTIG_VON_DTM_NEU\": \"0001-01-01\",\n" +
                "\t\t\"SCHLIESSUNG_DTM_ALT\": \"9999-12-31\",\n" +
                "\t\t\"PARTNERART_KZ_NEU\": 0,\n" +
                "\t\t\"INAKTIV_MM_NEU\": \" \",\n" +
                "\t\t\"FEHLER_NUMMER_NEU\": 0,\n" +
                "\t\t\"VORNAME_ZUSATZBEZ_ALT\": \"Janette                       \",\n" +
                "\t\t\"GUELTIG_VON_DTM_ALT\": \"2003-04-01\",\n" +
                "\t\t\"HERKUNFT_KZ_ALT\": \" \",\n" +
                "\t\t\"GUELTIG_BIS_DTM_NEU\": \"0001-01-01\",\n" +
                "\t\t\"VORNAME_ZUSATZBEZ_NEU\": \"FERTIG                        \",\n" +
                "\t\t\"FEHLER_GESAMT\": 12,\n" +
                "\t\t\"INAKTIV_MM_ALT\": \" \",\n" +
                "\t\t\"GEBURT_DTM_NEU\": \"0001-01-01\",\n" +
                "\t\t\"POSTLEITZAHL_ALT\": null,\n" +
                "\t\t\"FEHLER_SCHLIESSUNG_DTM\": 1,\n" +
                "\t\t\"FEHLER_FEHLER_NUMMER\": 0,\n" +
                "\t\t\"GEBURT_DTM_ALT\": \"1966-12-31\",\n" +
                "\t\t\"FEHLER_STRASSENNAME\": 0,\n" +
                "\t\t\"PRT_NR_UEB\": 1104920036\n" +
                "\t}, {\n" +
                "\t\t\"PARTNERNAME_NEU\": \"Quatsch-Test                  \",\n" +
                "\t\t\"HAUS_NR_NEU\": null,\n" +
                "\t\t\"FEHLER_EINRICHTUNG_DTM\": 1,\n" +
                "\t\t\"PARTNERNAME_ALT\": \"Gegenwind-Test                \",\n" +
                "\t\t\"FEHLER_PARTNER_TYP_KZ\": 0,\n" +
                "\t\t\"FEHLER_GEBURT_DTM\": 1,\n" +
                "\t\t\"FEHLER_HERKUNFT_KZ\": 0,\n" +
                "\t\t\"FEHLER_GUELTIG_VON_DTM\": 1,\n" +
                "\t\t\"SCHLIESSUNG_DTM_NEU\": \"9999-12-31\",\n" +
                "\t\t\"FEHLER_NICHT_MELDEREL_MM\": 0,\n" +
                "\t\t\"HAUS_NR_ALT\": null,\n" +
                "\t\t\"PARTNER_TYP_KZ_NEU\": \"N\",\n" +
                "\t\t\"FEHLER_PARTNERNAME\": 1,\n" +
                "\t\t\"PRT_NR_ALT\": 1106077918,\n" +
                "\t\t\"VTR_NR_ALT\": 5000101724,\n" +
                "\t\t\"ORTSNAME_NEU\": null,\n" +
                "\t\t\"FEHLER_ORTSNAME\": 0,\n" +
                "\t\t\"PRT_NR_NEU\": 711,\n" +
                "\t\t\"LFD_NR\": 2,\n" +
                "\t\t\"VTR_NR_NEU\": 11810052,\n" +
                "\t\t\"FEHLER_POSTLEITZAHL\": 0,\n" +
                "\t\t\"EINRICHTUNG_DTM_NEU\": \"2015-02-28\",\n" +
                "\t\t\"FEHLER_LAND_KZ\": 0,\n" +
                "\t\t\"ORTSNAME_ALT\": null,\n" +
                "\t\t\"NICHT_MELDEREL_MM_NEU\": \" \",\n" +
                "\t\t\"LAND_KZ_NEU\": null,\n" +
                "\t\t\"FEHLER_HAUS_NR\": 0,\n" +
                "\t\t\"STRASSENNAME_ALT\": null,\n" +
                "\t\t\"FEHLER_PRT_NR\": 1,\n" +
                "\t\t\"STRASSENNAME_NEU\": null,\n" +
                "\t\t\"FEHLER_HISTORISCH_VTR_KZ\": 0,\n" +
                "\t\t\"DATEI_NR_ALT\": 1,\n" +
                "\t\t\"FEHLER_LFD_NR\": 0,\n" +
                "\t\t\"FEHLER_INAKTIV_MM\": 0,\n" +
                "\t\t\"EINRICHTUNG_DTM_ALT\": \"0001-01-01\",\n" +
                "\t\t\"LAND_KZ_ALT\": null,\n" +
                "\t\t\"W_VORNAMEN_ZUSATZB_ALT\": \"                              \",\n" +
                "\t\t\"FEHLER_PARTNERART_KZ\": 0,\n" +
                "\t\t\"NICHT_MELDEREL_MM_ALT\": \" \",\n" +
                "\t\t\"HISTORISCH_VTR_KZ_NEU\": \" \",\n" +
                "\t\t\"FEHLER_W_VORNAMEN_ZUSATZB\": 0,\n" +
                "\t\t\"W_VORNAMEN_ZUSATZB_NEU\": \"                              \",\n" +
                "\t\t\"DATEI_NR_NEU\": 15,\n" +
                "\t\t\"HISTORISCH_VTR_KZ_ALT\": \" \",\n" +
                "\t\t\"FEHLER_DATEI_NR\": 1,\n" +
                "\t\t\"FEHLER_VTR_NR\": 1,\n" +
                "\t\t\"POSTLEITZAHL_NEU\": null,\n" +
                "\t\t\"PARTNER_TYP_KZ_ALT\": \"N\",\n" +
                "\t\t\"FEHLER_NUMMER_ALT\": 0,\n" +
                "\t\t\"FEHLER_VORNAME_ZUSATZBEZ\": 1,\n" +
                "\t\t\"HERKUNFT_KZ_NEU\": \" \",\n" +
                "\t\t\"GUELTIG_BIS_DTM_ALT\": \"9999-12-31\",\n" +
                "\t\t\"PARTNERART_KZ_ALT\": 1,\n" +
                "\t\t\"FEHLER_GUELTIG_BIS_DTM\": 0,\n" +
                "\t\t\"GUELTIG_VON_DTM_NEU\": \"2015-02-28\",\n" +
                "\t\t\"SCHLIESSUNG_DTM_ALT\": \"9999-12-31\",\n" +
                "\t\t\"PARTNERART_KZ_NEU\": 1,\n" +
                "\t\t\"INAKTIV_MM_NEU\": \" \",\n" +
                "\t\t\"FEHLER_NUMMER_NEU\": 0,\n" +
                "\t\t\"VORNAME_ZUSATZBEZ_ALT\": \"Sergei                        \",\n" +
                "\t\t\"GUELTIG_VON_DTM_ALT\": \"2003-04-01\",\n" +
                "\t\t\"HERKUNFT_KZ_ALT\": \" \",\n" +
                "\t\t\"GUELTIG_BIS_DTM_NEU\": \"9999-12-31\",\n" +
                "\t\t\"VORNAME_ZUSATZBEZ_NEU\": \"Frank Gerhard                 \",\n" +
                "\t\t\"FEHLER_GESAMT\": 8,\n" +
                "\t\t\"INAKTIV_MM_ALT\": \" \",\n" +
                "\t\t\"GEBURT_DTM_NEU\": \"1963-09-21\",\n" +
                "\t\t\"POSTLEITZAHL_ALT\": null,\n" +
                "\t\t\"FEHLER_SCHLIESSUNG_DTM\": 0,\n" +
                "\t\t\"FEHLER_FEHLER_NUMMER\": 0,\n" +
                "\t\t\"GEBURT_DTM_ALT\": \"1956-12-31\",\n" +
                "\t\t\"FEHLER_STRASSENNAME\": 0,\n" +
                "\t\t\"PRT_NR_UEB\": 1106077918\n" +
                "\t}, {\n" +
                "\t\t\"PARTNERNAME_NEU\": \"Austin-Healey-Test            \",\n" +
                "\t\t\"HAUS_NR_NEU\": null,\n" +
                "\t\t\"FEHLER_EINRICHTUNG_DTM\": 1,\n" +
                "\t\t\"PARTNERNAME_ALT\": \"Halse-Test                    \",\n" +
                "\t\t\"FEHLER_PARTNER_TYP_KZ\": 0,\n" +
                "\t\t\"FEHLER_GEBURT_DTM\": 1,\n" +
                "\t\t\"FEHLER_HERKUNFT_KZ\": 0,\n" +
                "\t\t\"FEHLER_GUELTIG_VON_DTM\": 1,\n" +
                "\t\t\"SCHLIESSUNG_DTM_NEU\": \"9999-12-31\",\n" +
                "\t\t\"FEHLER_NICHT_MELDEREL_MM\": 0,\n" +
                "\t\t\"HAUS_NR_ALT\": null,\n" +
                "\t\t\"PARTNER_TYP_KZ_NEU\": \"N\",\n" +
                "\t\t\"FEHLER_PARTNERNAME\": 1,\n" +
                "\t\t\"PRT_NR_ALT\": 1100279676,\n" +
                "\t\t\"VTR_NR_ALT\": 5000101823,\n" +
                "\t\t\"ORTSNAME_NEU\": null,\n" +
                "\t\t\"FEHLER_ORTSNAME\": 0,\n" +
                "\t\t\"PRT_NR_NEU\": 747,\n" +
                "\t\t\"LFD_NR\": 3,\n" +
                "\t\t\"VTR_NR_NEU\": 11921060,\n" +
                "\t\t\"FEHLER_POSTLEITZAHL\": 0,\n" +
                "\t\t\"EINRICHTUNG_DTM_NEU\": \"2015-02-28\",\n" +
                "\t\t\"FEHLER_LAND_KZ\": 0,\n" +
                "\t\t\"ORTSNAME_ALT\": null,\n" +
                "\t\t\"NICHT_MELDEREL_MM_NEU\": \" \",\n" +
                "\t\t\"LAND_KZ_NEU\": null,\n" +
                "\t\t\"FEHLER_HAUS_NR\": 0,\n" +
                "\t\t\"STRASSENNAME_ALT\": null,\n" +
                "\t\t\"FEHLER_PRT_NR\": 1,\n" +
                "\t\t\"STRASSENNAME_NEU\": null,\n" +
                "\t\t\"FEHLER_HISTORISCH_VTR_KZ\": 0,\n" +
                "\t\t\"DATEI_NR_ALT\": 1316,\n" +
                "\t\t\"FEHLER_LFD_NR\": 0,\n" +
                "\t\t\"FEHLER_INAKTIV_MM\": 1,\n" +
                "\t\t\"EINRICHTUNG_DTM_ALT\": \"0001-01-01\",\n" +
                "\t\t\"LAND_KZ_ALT\": null,\n" +
                "\t\t\"W_VORNAMEN_ZUSATZB_ALT\": \"                              \",\n" +
                "\t\t\"FEHLER_PARTNERART_KZ\": 0,\n" +
                "\t\t\"NICHT_MELDEREL_MM_ALT\": \" \",\n" +
                "\t\t\"HISTORISCH_VTR_KZ_NEU\": \" \",\n" +
                "\t\t\"FEHLER_W_VORNAMEN_ZUSATZB\": 0,\n" +
                "\t\t\"W_VORNAMEN_ZUSATZB_NEU\": \"                              \",\n" +
                "\t\t\"DATEI_NR_NEU\": 15,\n" +
                "\t\t\"HISTORISCH_VTR_KZ_ALT\": \" \",\n" +
                "\t\t\"FEHLER_DATEI_NR\": 1,\n" +
                "\t\t\"FEHLER_VTR_NR\": 1,\n" +
                "\t\t\"POSTLEITZAHL_NEU\": null,\n" +
                "\t\t\"PARTNER_TYP_KZ_ALT\": \"N\",\n" +
                "\t\t\"FEHLER_NUMMER_ALT\": 0,\n" +
                "\t\t\"FEHLER_VORNAME_ZUSATZBEZ\": 1,\n" +
                "\t\t\"HERKUNFT_KZ_NEU\": \" \",\n" +
                "\t\t\"GUELTIG_BIS_DTM_ALT\": \"2009-04-15\",\n" +
                "\t\t\"PARTNERART_KZ_ALT\": 1,\n" +
                "\t\t\"FEHLER_GUELTIG_BIS_DTM\": 1,\n" +
                "\t\t\"GUELTIG_VON_DTM_NEU\": \"2015-02-28\",\n" +
                "\t\t\"SCHLIESSUNG_DTM_ALT\": \"9999-12-31\",\n" +
                "\t\t\"PARTNERART_KZ_NEU\": 1,\n" +
                "\t\t\"INAKTIV_MM_NEU\": \" \",\n" +
                "\t\t\"FEHLER_NUMMER_NEU\": 0,\n" +
                "\t\t\"VORNAME_ZUSATZBEZ_ALT\": \"Udo Franz                     \",\n" +
                "\t\t\"GUELTIG_VON_DTM_ALT\": \"2003-04-01\",\n" +
                "\t\t\"HERKUNFT_KZ_ALT\": \" \",\n" +
                "\t\t\"GUELTIG_BIS_DTM_NEU\": \"9999-12-31\",\n" +
                "\t\t\"VORNAME_ZUSATZBEZ_NEU\": \"Lea                           \",\n" +
                "\t\t\"FEHLER_GESAMT\": 10,\n" +
                "\t\t\"INAKTIV_MM_ALT\": \"X\",\n" +
                "\t\t\"GEBURT_DTM_NEU\": \"1959-06-04\",\n" +
                "\t\t\"POSTLEITZAHL_ALT\": null,\n" +
                "\t\t\"FEHLER_SCHLIESSUNG_DTM\": 0,\n" +
                "\t\t\"FEHLER_FEHLER_NUMMER\": 0,\n" +
                "\t\t\"GEBURT_DTM_ALT\": \"1952-12-31\",\n" +
                "\t\t\"FEHLER_STRASSENNAME\": 0,\n" +
                "\t\t\"PRT_NR_UEB\": 1100279676\n" +
                "\t}, {\n" +
                "\t\t\"PARTNERNAME_NEU\": \"Hampelmann-Test               \",\n" +
                "\t\t\"HAUS_NR_NEU\": null,\n" +
                "\t\t\"FEHLER_EINRICHTUNG_DTM\": 1,\n" +
                "\t\t\"PARTNERNAME_ALT\": \"Gedankenstrich-Test           \",\n" +
                "\t\t\"FEHLER_PARTNER_TYP_KZ\": 0,\n" +
                "\t\t\"FEHLER_GEBURT_DTM\": 1,\n" +
                "\t\t\"FEHLER_HERKUNFT_KZ\": 0,\n" +
                "\t\t\"FEHLER_GUELTIG_VON_DTM\": 1,\n" +
                "\t\t\"SCHLIESSUNG_DTM_NEU\": \"9999-12-31\",\n" +
                "\t\t\"FEHLER_NICHT_MELDEREL_MM\": 0,\n" +
                "\t\t\"HAUS_NR_ALT\": null,\n" +
                "\t\t\"PARTNER_TYP_KZ_NEU\": \"N\",\n" +
                "\t\t\"FEHLER_PARTNERNAME\": 1,\n" +
                "\t\t\"PRT_NR_ALT\": 1100279676,\n" +
                "\t\t\"VTR_NR_ALT\": 5000101823,\n" +
                "\t\t\"ORTSNAME_NEU\": null,\n" +
                "\t\t\"FEHLER_ORTSNAME\": 0,\n" +
                "\t\t\"PRT_NR_NEU\": 1237,\n" +
                "\t\t\"LFD_NR\": 4,\n" +
                "\t\t\"VTR_NR_NEU\": 13571189,\n" +
                "\t\t\"FEHLER_POSTLEITZAHL\": 0,\n" +
                "\t\t\"EINRICHTUNG_DTM_NEU\": \"2014-12-31\",\n" +
                "\t\t\"FEHLER_LAND_KZ\": 0,\n" +
                "\t\t\"ORTSNAME_ALT\": null,\n" +
                "\t\t\"NICHT_MELDEREL_MM_NEU\": \" \",\n" +
                "\t\t\"LAND_KZ_NEU\": null,\n" +
                "\t\t\"FEHLER_HAUS_NR\": 0,\n" +
                "\t\t\"STRASSENNAME_ALT\": null,\n" +
                "\t\t\"FEHLER_PRT_NR\": 1,\n" +
                "\t\t\"STRASSENNAME_NEU\": null,\n" +
                "\t\t\"FEHLER_HISTORISCH_VTR_KZ\": 0,\n" +
                "\t\t\"DATEI_NR_ALT\": 1316,\n" +
                "\t\t\"FEHLER_LFD_NR\": 0,\n" +
                "\t\t\"FEHLER_INAKTIV_MM\": 0,\n" +
                "\t\t\"EINRICHTUNG_DTM_ALT\": \"0001-01-01\",\n" +
                "\t\t\"LAND_KZ_ALT\": null,\n" +
                "\t\t\"W_VORNAMEN_ZUSATZB_ALT\": \"                              \",\n" +
                "\t\t\"FEHLER_PARTNERART_KZ\": 0,\n" +
                "\t\t\"NICHT_MELDEREL_MM_ALT\": \" \",\n" +
                "\t\t\"HISTORISCH_VTR_KZ_NEU\": \" \",\n" +
                "\t\t\"FEHLER_W_VORNAMEN_ZUSATZB\": 0,\n" +
                "\t\t\"W_VORNAMEN_ZUSATZB_NEU\": \"                              \",\n" +
                "\t\t\"DATEI_NR_NEU\": 8,\n" +
                "\t\t\"HISTORISCH_VTR_KZ_ALT\": \" \",\n" +
                "\t\t\"FEHLER_DATEI_NR\": 1,\n" +
                "\t\t\"FEHLER_VTR_NR\": 1,\n" +
                "\t\t\"POSTLEITZAHL_NEU\": null,\n" +
                "\t\t\"PARTNER_TYP_KZ_ALT\": \"N\",\n" +
                "\t\t\"FEHLER_NUMMER_ALT\": 0,\n" +
                "\t\t\"FEHLER_VORNAME_ZUSATZBEZ\": 1,\n" +
                "\t\t\"HERKUNFT_KZ_NEU\": \" \",\n" +
                "\t\t\"GUELTIG_BIS_DTM_ALT\": \"9999-12-31\",\n" +
                "\t\t\"PARTNERART_KZ_ALT\": 1,\n" +
                "\t\t\"FEHLER_GUELTIG_BIS_DTM\": 0,\n" +
                "\t\t\"GUELTIG_VON_DTM_NEU\": \"2014-12-31\",\n" +
                "\t\t\"SCHLIESSUNG_DTM_ALT\": \"9999-12-31\",\n" +
                "\t\t\"PARTNERART_KZ_NEU\": 1,\n" +
                "\t\t\"INAKTIV_MM_NEU\": \" \",\n" +
                "\t\t\"FEHLER_NUMMER_NEU\": 0,\n" +
                "\t\t\"VORNAME_ZUSATZBEZ_ALT\": \"Udo Franz                     \",\n" +
                "\t\t\"GUELTIG_VON_DTM_ALT\": \"2009-04-16\",\n" +
                "\t\t\"HERKUNFT_KZ_ALT\": \" \",\n" +
                "\t\t\"GUELTIG_BIS_DTM_NEU\": \"9999-12-31\",\n" +
                "\t\t\"VORNAME_ZUSATZBEZ_NEU\": \"Henry Philipp                 \",\n" +
                "\t\t\"FEHLER_GESAMT\": 8,\n" +
                "\t\t\"INAKTIV_MM_ALT\": \" \",\n" +
                "\t\t\"GEBURT_DTM_NEU\": \"1957-10-11\",\n" +
                "\t\t\"POSTLEITZAHL_ALT\": null,\n" +
                "\t\t\"FEHLER_SCHLIESSUNG_DTM\": 0,\n" +
                "\t\t\"FEHLER_FEHLER_NUMMER\": 0,\n" +
                "\t\t\"GEBURT_DTM_ALT\": \"1952-12-31\",\n" +
                "\t\t\"FEHLER_STRASSENNAME\": 0,\n" +
                "\t\t\"PRT_NR_UEB\": 1100279676\n" +
                "\t}, {\n" +
                "\t\t\"PARTNERNAME_NEU\": \"Cremetu00F6rtchen-Test            \",\n" +
                "\t\t\"HAUS_NR_NEU\": null,\n" +
                "\t\t\"FEHLER_EINRICHTUNG_DTM\": 1,\n" +
                "\t\t\"PARTNERNAME_ALT\": \"Codec-Test                    \",\n" +
                "\t\t\"FEHLER_PARTNER_TYP_KZ\": 0,\n" +
                "\t\t\"FEHLER_GEBURT_DTM\": 1,\n" +
                "\t\t\"FEHLER_HERKUNFT_KZ\": 0,\n" +
                "\t\t\"FEHLER_GUELTIG_VON_DTM\": 1,\n" +
                "\t\t\"SCHLIESSUNG_DTM_NEU\": \"9999-12-31\",\n" +
                "\t\t\"FEHLER_NICHT_MELDEREL_MM\": 0,\n" +
                "\t\t\"HAUS_NR_ALT\": null,\n" +
                "\t\t\"PARTNER_TYP_KZ_NEU\": \"N\",\n" +
                "\t\t\"FEHLER_PARTNERNAME\": 1,\n" +
                "\t\t\"PRT_NR_ALT\": 1107325647,\n" +
                "\t\t\"VTR_NR_ALT\": 5000101922,\n" +
                "\t\t\"ORTSNAME_NEU\": null,\n" +
                "\t\t\"FEHLER_ORTSNAME\": 0,\n" +
                "\t\t\"PRT_NR_NEU\": 1502,\n" +
                "\t\t\"LFD_NR\": 5,\n" +
                "\t\t\"VTR_NR_NEU\": 14276051,\n" +
                "\t\t\"FEHLER_POSTLEITZAHL\": 0,\n" +
                "\t\t\"EINRICHTUNG_DTM_NEU\": \"2014-12-31\",\n" +
                "\t\t\"FEHLER_LAND_KZ\": 0,\n" +
                "\t\t\"ORTSNAME_ALT\": null,\n" +
                "\t\t\"NICHT_MELDEREL_MM_NEU\": \" \",\n" +
                "\t\t\"LAND_KZ_NEU\": null,\n" +
                "\t\t\"FEHLER_HAUS_NR\": 0,\n" +
                "\t\t\"STRASSENNAME_ALT\": null,\n" +
                "\t\t\"FEHLER_PRT_NR\": 1,\n" +
                "\t\t\"STRASSENNAME_NEU\": null,\n" +
                "\t\t\"FEHLER_HISTORISCH_VTR_KZ\": 0,\n" +
                "\t\t\"DATEI_NR_ALT\": 1,\n" +
                "\t\t\"FEHLER_LFD_NR\": 0,\n" +
                "\t\t\"FEHLER_INAKTIV_MM\": 0,\n" +
                "\t\t\"EINRICHTUNG_DTM_ALT\": \"0001-01-01\",\n" +
                "\t\t\"LAND_KZ_ALT\": null,\n" +
                "\t\t\"W_VORNAMEN_ZUSATZB_ALT\": \"                              \",\n" +
                "\t\t\"FEHLER_PARTNERART_KZ\": 0,\n" +
                "\t\t\"NICHT_MELDEREL_MM_ALT\": \" \",\n" +
                "\t\t\"HISTORISCH_VTR_KZ_NEU\": \" \",\n" +
                "\t\t\"FEHLER_W_VORNAMEN_ZUSATZB\": 0,\n" +
                "\t\t\"W_VORNAMEN_ZUSATZB_NEU\": \"                              \",\n" +
                "\t\t\"DATEI_NR_NEU\": 8,\n" +
                "\t\t\"HISTORISCH_VTR_KZ_ALT\": \" \",\n" +
                "\t\t\"FEHLER_DATEI_NR\": 1,\n" +
                "\t\t\"FEHLER_VTR_NR\": 1,\n" +
                "\t\t\"POSTLEITZAHL_NEU\": null,\n" +
                "\t\t\"PARTNER_TYP_KZ_ALT\": \"N\",\n" +
                "\t\t\"FEHLER_NUMMER_ALT\": 0,\n" +
                "\t\t\"FEHLER_VORNAME_ZUSATZBEZ\": 1,\n" +
                "\t\t\"HERKUNFT_KZ_NEU\": \" \",\n" +
                "\t\t\"GUELTIG_BIS_DTM_ALT\": \"9999-12-31\",\n" +
                "\t\t\"PARTNERART_KZ_ALT\": 1,\n" +
                "\t\t\"FEHLER_GUELTIG_BIS_DTM\": 0,\n" +
                "\t\t\"GUELTIG_VON_DTM_NEU\": \"2014-12-31\",\n" +
                "\t\t\"SCHLIESSUNG_DTM_ALT\": \"9999-12-31\",\n" +
                "\t\t\"PARTNERART_KZ_NEU\": 1,\n" +
                "\t\t\"INAKTIV_MM_NEU\": \" \",\n" +
                "\t\t\"FEHLER_NUMMER_NEU\": 0,\n" +
                "\t\t\"VORNAME_ZUSATZBEZ_ALT\": \"Alfred Martin                 \",\n" +
                "\t\t\"GUELTIG_VON_DTM_ALT\": \"2003-04-01\",\n" +
                "\t\t\"HERKUNFT_KZ_ALT\": \" \",\n" +
                "\t\t\"GUELTIG_BIS_DTM_NEU\": \"9999-12-31\",\n" +
                "\t\t\"VORNAME_ZUSATZBEZ_NEU\": \"Michael Ingo                  \",\n" +
                "\t\t\"FEHLER_GESAMT\": 8,\n" +
                "\t\t\"INAKTIV_MM_ALT\": \" \",\n" +
                "\t\t\"GEBURT_DTM_NEU\": \"1959-01-13\",\n" +
                "\t\t\"POSTLEITZAHL_ALT\": null,\n" +
                "\t\t\"FEHLER_SCHLIESSUNG_DTM\": 0,\n" +
                "\t\t\"FEHLER_FEHLER_NUMMER\": 0,\n" +
                "\t\t\"GEBURT_DTM_ALT\": \"1947-05-28\",\n" +
                "\t\t\"FEHLER_STRASSENNAME\": 0,\n" +
                "\t\t\"PRT_NR_UEB\": 1107325647\n" +
                "\t}, {\n" +
                "\t\t\"PARTNERNAME_NEU\": \"Ladenverku00E4ufer-Test           \",\n" +
                "\t\t\"HAUS_NR_NEU\": null,\n" +
                "\t\t\"FEHLER_EINRICHTUNG_DTM\": 1,\n" +
                "\t\t\"PARTNERNAME_ALT\": \"Nische-Test                   \",\n" +
                "\t\t\"FEHLER_PARTNER_TYP_KZ\": 0,\n" +
                "\t\t\"FEHLER_GEBURT_DTM\": 1,\n" +
                "\t\t\"FEHLER_HERKUNFT_KZ\": 0,\n" +
                "\t\t\"FEHLER_GUELTIG_VON_DTM\": 1,\n" +
                "\t\t\"SCHLIESSUNG_DTM_NEU\": \"9999-12-31\",\n" +
                "\t\t\"FEHLER_NICHT_MELDEREL_MM\": 0,\n" +
                "\t\t\"HAUS_NR_ALT\": null,\n" +
                "\t\t\"PARTNER_TYP_KZ_NEU\": \"N\",\n" +
                "\t\t\"FEHLER_PARTNERNAME\": 1,\n" +
                "\t\t\"PRT_NR_ALT\": 1100302973,\n" +
                "\t\t\"VTR_NR_ALT\": 5000102128,\n" +
                "\t\t\"ORTSNAME_NEU\": null,\n" +
                "\t\t\"FEHLER_ORTSNAME\": 0,\n" +
                "\t\t\"PRT_NR_NEU\": 2636,\n" +
                "\t\t\"LFD_NR\": 6,\n" +
                "\t\t\"VTR_NR_NEU\": 17339073,\n" +
                "\t\t\"FEHLER_POSTLEITZAHL\": 0,\n" +
                "\t\t\"EINRICHTUNG_DTM_NEU\": \"2014-11-30\",\n" +
                "\t\t\"FEHLER_LAND_KZ\": 0,\n" +
                "\t\t\"ORTSNAME_ALT\": null,\n" +
                "\t\t\"NICHT_MELDEREL_MM_NEU\": \" \",\n" +
                "\t\t\"LAND_KZ_NEU\": null,\n" +
                "\t\t\"FEHLER_HAUS_NR\": 0,\n" +
                "\t\t\"STRASSENNAME_ALT\": null,\n" +
                "\t\t\"FEHLER_PRT_NR\": 1,\n" +
                "\t\t\"STRASSENNAME_NEU\": null,\n" +
                "\t\t\"FEHLER_HISTORISCH_VTR_KZ\": 0,\n" +
                "\t\t\"DATEI_NR_ALT\": 1,\n" +
                "\t\t\"FEHLER_LFD_NR\": 0,\n" +
                "\t\t\"FEHLER_INAKTIV_MM\": 0,\n" +
                "\t\t\"EINRICHTUNG_DTM_ALT\": \"0001-01-01\",\n" +
                "\t\t\"LAND_KZ_ALT\": null,\n" +
                "\t\t\"W_VORNAMEN_ZUSATZB_ALT\": \"                              \",\n" +
                "\t\t\"FEHLER_PARTNERART_KZ\": 0,\n" +
                "\t\t\"NICHT_MELDEREL_MM_ALT\": \" \",\n" +
                "\t\t\"HISTORISCH_VTR_KZ_NEU\": \" \",\n" +
                "\t\t\"FEHLER_W_VORNAMEN_ZUSATZB\": 0,\n" +
                "\t\t\"W_VORNAMEN_ZUSATZB_NEU\": \"                              \",\n" +
                "\t\t\"DATEI_NR_NEU\": 5,\n" +
                "\t\t\"HISTORISCH_VTR_KZ_ALT\": \" \",\n" +
                "\t\t\"FEHLER_DATEI_NR\": 1,\n" +
                "\t\t\"FEHLER_VTR_NR\": 1,\n" +
                "\t\t\"POSTLEITZAHL_NEU\": null,\n" +
                "\t\t\"PARTNER_TYP_KZ_ALT\": \"N\",\n" +
                "\t\t\"FEHLER_NUMMER_ALT\": 0,\n" +
                "\t\t\"FEHLER_VORNAME_ZUSATZBEZ\": 1,\n" +
                "\t\t\"HERKUNFT_KZ_NEU\": \" \",\n" +
                "\t\t\"GUELTIG_BIS_DTM_ALT\": \"9999-12-31\",\n" +
                "\t\t\"PARTNERART_KZ_ALT\": 1,\n" +
                "\t\t\"FEHLER_GUELTIG_BIS_DTM\": 0,\n" +
                "\t\t\"GUELTIG_VON_DTM_NEU\": \"2014-11-30\",\n" +
                "\t\t\"SCHLIESSUNG_DTM_ALT\": \"9999-12-31\",\n" +
                "\t\t\"PARTNERART_KZ_NEU\": 1,\n" +
                "\t\t\"INAKTIV_MM_NEU\": \" \",\n" +
                "\t\t\"FEHLER_NUMMER_NEU\": 0,\n" +
                "\t\t\"VORNAME_ZUSATZBEZ_ALT\": \"Eniko                         \",\n" +
                "\t\t\"GUELTIG_VON_DTM_ALT\": \"2003-04-01\",\n" +
                "\t\t\"HERKUNFT_KZ_ALT\": \" \",\n" +
                "\t\t\"GUELTIG_BIS_DTM_NEU\": \"9999-12-31\",\n" +
                "\t\t\"VORNAME_ZUSATZBEZ_NEU\": \"Julia Elena                   \",\n" +
                "\t\t\"FEHLER_GESAMT\": 8,\n" +
                "\t\t\"INAKTIV_MM_ALT\": \" \",\n" +
                "\t\t\"GEBURT_DTM_NEU\": \"1944-11-15\",\n" +
                "\t\t\"POSTLEITZAHL_ALT\": null,\n" +
                "\t\t\"FEHLER_SCHLIESSUNG_DTM\": 0,\n" +
                "\t\t\"FEHLER_FEHLER_NUMMER\": 0,\n" +
                "\t\t\"GEBURT_DTM_ALT\": \"1946-12-31\",\n" +
                "\t\t\"FEHLER_STRASSENNAME\": 0,\n" +
                "\t\t\"PRT_NR_UEB\": 1100302973\n" +
                "\t}, {\n" +
                "\t\t\"PARTNERNAME_NEU\": \"Gehirn-Test                   \",\n" +
                "\t\t\"HAUS_NR_NEU\": null,\n" +
                "\t\t\"FEHLER_EINRICHTUNG_DTM\": 1,\n" +
                "\t\t\"PARTNERNAME_ALT\": \"Induktionsschritt-Test        \",\n" +
                "\t\t\"FEHLER_PARTNER_TYP_KZ\": 0,\n" +
                "\t\t\"FEHLER_GEBURT_DTM\": 1,\n" +
                "\t\t\"FEHLER_HERKUNFT_KZ\": 0,\n" +
                "\t\t\"FEHLER_GUELTIG_VON_DTM\": 1,\n" +
                "\t\t\"SCHLIESSUNG_DTM_NEU\": \"9999-12-31\",\n" +
                "\t\t\"FEHLER_NICHT_MELDEREL_MM\": 0,\n" +
                "\t\t\"HAUS_NR_ALT\": null,\n" +
                "\t\t\"PARTNER_TYP_KZ_NEU\": \"N\",\n" +
                "\t\t\"FEHLER_PARTNERNAME\": 1,\n" +
                "\t\t\"PRT_NR_ALT\": 1100546835,\n" +
                "\t\t\"VTR_NR_ALT\": 5000102722,\n" +
                "\t\t\"ORTSNAME_NEU\": null,\n" +
                "\t\t\"FEHLER_ORTSNAME\": 0,\n" +
                "\t\t\"PRT_NR_NEU\": 3292,\n" +
                "\t\t\"LFD_NR\": 7,\n" +
                "\t\t\"VTR_NR_NEU\": 19215076,\n" +
                "\t\t\"FEHLER_POSTLEITZAHL\": 0,\n" +
                "\t\t\"EINRICHTUNG_DTM_NEU\": \"2014-12-31\",\n" +
                "\t\t\"FEHLER_LAND_KZ\": 0,\n" +
                "\t\t\"ORTSNAME_ALT\": null,\n" +
                "\t\t\"NICHT_MELDEREL_MM_NEU\": \" \",\n" +
                "\t\t\"LAND_KZ_NEU\": null,\n" +
                "\t\t\"FEHLER_HAUS_NR\": 0,\n" +
                "\t\t\"STRASSENNAME_ALT\": null,\n" +
                "\t\t\"FEHLER_PRT_NR\": 1,\n" +
                "\t\t\"STRASSENNAME_NEU\": null,\n" +
                "\t\t\"FEHLER_HISTORISCH_VTR_KZ\": 0,\n" +
                "\t\t\"DATEI_NR_ALT\": 1,\n" +
                "\t\t\"FEHLER_LFD_NR\": 0,\n" +
                "\t\t\"FEHLER_INAKTIV_MM\": 0,\n" +
                "\t\t\"EINRICHTUNG_DTM_ALT\": \"0001-01-01\",\n" +
                "\t\t\"LAND_KZ_ALT\": null,\n" +
                "\t\t\"W_VORNAMEN_ZUSATZB_ALT\": \"                              \",\n" +
                "\t\t\"FEHLER_PARTNERART_KZ\": 0,\n" +
                "\t\t\"NICHT_MELDEREL_MM_ALT\": \" \",\n" +
                "\t\t\"HISTORISCH_VTR_KZ_NEU\": \" \",\n" +
                "\t\t\"FEHLER_W_VORNAMEN_ZUSATZB\": 1,\n" +
                "\t\t\"W_VORNAMEN_ZUSATZB_NEU\": \"Aljoscha                      \",\n" +
                "\t\t\"DATEI_NR_NEU\": 8,\n" +
                "\t\t\"HISTORISCH_VTR_KZ_ALT\": \" \",\n" +
                "\t\t\"FEHLER_DATEI_NR\": 1,\n" +
                "\t\t\"FEHLER_VTR_NR\": 1,\n" +
                "\t\t\"POSTLEITZAHL_NEU\": null,\n" +
                "\t\t\"PARTNER_TYP_KZ_ALT\": \"N\",\n" +
                "\t\t\"FEHLER_NUMMER_ALT\": 0,\n" +
                "\t\t\"FEHLER_VORNAME_ZUSATZBEZ\": 1,\n" +
                "\t\t\"HERKUNFT_KZ_NEU\": \" \",\n" +
                "\t\t\"GUELTIG_BIS_DTM_ALT\": \"9999-12-31\",\n" +
                "\t\t\"PARTNERART_KZ_ALT\": 1,\n" +
                "\t\t\"FEHLER_GUELTIG_BIS_DTM\": 0,\n" +
                "\t\t\"GUELTIG_VON_DTM_NEU\": \"2014-12-31\",\n" +
                "\t\t\"SCHLIESSUNG_DTM_ALT\": \"9999-12-31\",\n" +
                "\t\t\"PARTNERART_KZ_NEU\": 1,\n" +
                "\t\t\"INAKTIV_MM_NEU\": \" \",\n" +
                "\t\t\"FEHLER_NUMMER_NEU\": 0,\n" +
                "\t\t\"VORNAME_ZUSATZBEZ_ALT\": \"Olessia                       \",\n" +
                "\t\t\"GUELTIG_VON_DTM_ALT\": \"2003-04-01\",\n" +
                "\t\t\"HERKUNFT_KZ_ALT\": \" \",\n" +
                "\t\t\"GUELTIG_BIS_DTM_NEU\": \"9999-12-31\",\n" +
                "\t\t\"VORNAME_ZUSATZBEZ_NEU\": \"Nick Jonathan                 \",\n" +
                "\t\t\"FEHLER_GESAMT\": 9,\n" +
                "\t\t\"INAKTIV_MM_ALT\": \" \",\n" +
                "\t\t\"GEBURT_DTM_NEU\": \"1961-10-09\",\n" +
                "\t\t\"POSTLEITZAHL_ALT\": null,\n" +
                "\t\t\"FEHLER_SCHLIESSUNG_DTM\": 0,\n" +
                "\t\t\"FEHLER_FEHLER_NUMMER\": 0,\n" +
                "\t\t\"GEBURT_DTM_ALT\": \"1967-03-06\",\n" +
                "\t\t\"FEHLER_STRASSENNAME\": 0,\n" +
                "\t\t\"PRT_NR_UEB\": 1100546835\n" +
                "\t}, {\n" +
                "\t\t\"PARTNERNAME_NEU\": \"Toronto-Test                  \",\n" +
                "\t\t\"HAUS_NR_NEU\": null,\n" +
                "\t\t\"FEHLER_EINRICHTUNG_DTM\": 1,\n" +
                "\t\t\"PARTNERNAME_ALT\": \"Gefu00FChlsmensch-Test            \",\n" +
                "\t\t\"FEHLER_PARTNER_TYP_KZ\": 0,\n" +
                "\t\t\"FEHLER_GEBURT_DTM\": 1,\n" +
                "\t\t\"FEHLER_HERKUNFT_KZ\": 0,\n" +
                "\t\t\"FEHLER_GUELTIG_VON_DTM\": 1,\n" +
                "\t\t\"SCHLIESSUNG_DTM_NEU\": \"9999-12-31\",\n" +
                "\t\t\"FEHLER_NICHT_MELDEREL_MM\": 0,\n" +
                "\t\t\"HAUS_NR_ALT\": null,\n" +
                "\t\t\"PARTNER_TYP_KZ_NEU\": \"N\",\n" +
                "\t\t\"FEHLER_PARTNERNAME\": 1,\n" +
                "\t\t\"PRT_NR_ALT\": 1100432424,\n" +
                "\t\t\"VTR_NR_ALT\": 5000103027,\n" +
                "\t\t\"ORTSNAME_NEU\": null,\n" +
                "\t\t\"FEHLER_ORTSNAME\": 0,\n" +
                "\t\t\"PRT_NR_NEU\": 4012,\n" +
                "\t\t\"LFD_NR\": 8,\n" +
                "\t\t\"VTR_NR_NEU\": 21196125,\n" +
                "\t\t\"FEHLER_POSTLEITZAHL\": 0,\n" +
                "\t\t\"EINRICHTUNG_DTM_NEU\": \"2015-01-02\",\n" +
                "\t\t\"FEHLER_LAND_KZ\": 0,\n" +
                "\t\t\"ORTSNAME_ALT\": null,\n" +
                "\t\t\"NICHT_MELDEREL_MM_NEU\": \" \",\n" +
                "\t\t\"LAND_KZ_NEU\": null,\n" +
                "\t\t\"FEHLER_HAUS_NR\": 0,\n" +
                "\t\t\"STRASSENNAME_ALT\": null,\n" +
                "\t\t\"FEHLER_PRT_NR\": 1,\n" +
                "\t\t\"STRASSENNAME_NEU\": null,\n" +
                "\t\t\"FEHLER_HISTORISCH_VTR_KZ\": 0,\n" +
                "\t\t\"DATEI_NR_ALT\": 1,\n" +
                "\t\t\"FEHLER_LFD_NR\": 0,\n" +
                "\t\t\"FEHLER_INAKTIV_MM\": 0,\n" +
                "\t\t\"EINRICHTUNG_DTM_ALT\": \"0001-01-01\",\n" +
                "\t\t\"LAND_KZ_ALT\": null,\n" +
                "\t\t\"W_VORNAMEN_ZUSATZB_ALT\": \"                              \",\n" +
                "\t\t\"FEHLER_PARTNERART_KZ\": 0,\n" +
                "\t\t\"NICHT_MELDEREL_MM_ALT\": \" \",\n" +
                "\t\t\"HISTORISCH_VTR_KZ_NEU\": \" \",\n" +
                "\t\t\"FEHLER_W_VORNAMEN_ZUSATZB\": 0,\n" +
                "\t\t\"W_VORNAMEN_ZUSATZB_NEU\": \"                              \",\n" +
                "\t\t\"DATEI_NR_NEU\": 9,\n" +
                "\t\t\"HISTORISCH_VTR_KZ_ALT\": \" \",\n" +
                "\t\t\"FEHLER_DATEI_NR\": 1,\n" +
                "\t\t\"FEHLER_VTR_NR\": 1,\n" +
                "\t\t\"POSTLEITZAHL_NEU\": null,\n" +
                "\t\t\"PARTNER_TYP_KZ_ALT\": \"N\",\n" +
                "\t\t\"FEHLER_NUMMER_ALT\": 0,\n" +
                "\t\t\"FEHLER_VORNAME_ZUSATZBEZ\": 1,\n" +
                "\t\t\"HERKUNFT_KZ_NEU\": \" \",\n" +
                "\t\t\"GUELTIG_BIS_DTM_ALT\": \"9999-12-31\",\n" +
                "\t\t\"PARTNERART_KZ_ALT\": 1,\n" +
                "\t\t\"FEHLER_GUELTIG_BIS_DTM\": 0,\n" +
                "\t\t\"GUELTIG_VON_DTM_NEU\": \"2015-01-02\",\n" +
                "\t\t\"SCHLIESSUNG_DTM_ALT\": \"9999-12-31\",\n" +
                "\t\t\"PARTNERART_KZ_NEU\": 1,\n" +
                "\t\t\"INAKTIV_MM_NEU\": \" \",\n" +
                "\t\t\"FEHLER_NUMMER_NEU\": 0,\n" +
                "\t\t\"VORNAME_ZUSATZBEZ_ALT\": \"Margareta                     \",\n" +
                "\t\t\"GUELTIG_VON_DTM_ALT\": \"2003-04-01\",\n" +
                "\t\t\"HERKUNFT_KZ_ALT\": \" \",\n" +
                "\t\t\"GUELTIG_BIS_DTM_NEU\": \"9999-12-31\",\n" +
                "\t\t\"VORNAME_ZUSATZBEZ_NEU\": \"Dietrich Peter                \",\n" +
                "\t\t\"FEHLER_GESAMT\": 8,\n" +
                "\t\t\"INAKTIV_MM_ALT\": \" \",\n" +
                "\t\t\"GEBURT_DTM_NEU\": \"1963-08-22\",\n" +
                "\t\t\"POSTLEITZAHL_ALT\": null,\n" +
                "\t\t\"FEHLER_SCHLIESSUNG_DTM\": 0,\n" +
                "\t\t\"FEHLER_FEHLER_NUMMER\": 0,\n" +
                "\t\t\"GEBURT_DTM_ALT\": \"1962-12-31\",\n" +
                "\t\t\"FEHLER_STRASSENNAME\": 0,\n" +
                "\t\t\"PRT_NR_UEB\": 1100432424\n" +
                "\t}\n" +
                "\t]";

        Object expectedValue = 71.00;
        String pathExpr = "$.[*].FEHLER_GESAMT.sum()";
        Object result = executeQuery(conf, pathExpr, json);
        System.out.println(result.toString());
        assertThat(conf.jsonProvider().unwrap(result)).isEqualTo(expectedValue);

    }
}
