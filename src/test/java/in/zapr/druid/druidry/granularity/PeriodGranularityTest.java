/*
 * Copyright (c) 2017-present, Red Brick Lane Marketing Solutions Pvt. Ltd.
 * All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package in.zapr.druid.druidry.granularity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PeriodGranularityTest {
    private static ObjectMapper objectMapper;
    private final static String PERIOD = "PT1H";
    private final static String TIMEZONE = "Asia/Kolkata";

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JSONException, JsonProcessingException {

        DateTime originDate = new DateTime(DateTimeZone.forID(TIMEZONE));
        PeriodGranularity spec = PeriodGranularity.builder()
                .origin(originDate)
                .period(PERIOD)
                .timeZone(DateTimeZone.forID(TIMEZONE))
                .build();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "period");
        jsonObject.put("period", PERIOD);
        jsonObject.put("timeZone", TIMEZONE);
        jsonObject.put("origin", originDate);

        DurationGranularity spec1 = new DurationGranularity(3141, originDate);

        String actualJSON = objectMapper.writeValueAsString(spec);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }
}
