/*
 * Copyright 2018-present Red Brick Lane Marketing Solutions Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package in.zapr.druid.druidry.aggregator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Slf4j
public class LongFirstAggregatorTest {

    private static ObjectMapper objectMapper;

    @BeforeClass
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAllFields() throws JsonProcessingException, JSONException {

        LongFirstAggregator longFirstAggregator = new LongFirstAggregator("CarpeDiem",
                "Hey");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "longFirst");
        jsonObject.put("name", "CarpeDiem");
        jsonObject.put("fieldName", "Hey");

        String actualJSON = objectMapper.writeValueAsString(longFirstAggregator);
        String expectedJSON = jsonObject.toString();
        JSONAssert.assertEquals(expectedJSON, actualJSON, JSONCompareMode.NON_EXTENSIBLE);
    }



    @Test(expectedExceptions = NullPointerException.class)
    public void testNullName() throws JsonProcessingException, JSONException {

        LongFirstAggregator longFirstAggregator = new LongFirstAggregator(null, "Hey");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testFieldName() throws JsonProcessingException, JSONException {

        LongFirstAggregator longFirstAggregator = new LongFirstAggregator("CarpeDiem", null);
    }

    @Test
    public void testEqualsPositive() {
        LongFirstAggregator aggregator1 = new LongFirstAggregator("name", "field");
        LongFirstAggregator aggregator2 = new LongFirstAggregator("name", "field");

        Assert.assertEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsNegative() {
        LongFirstAggregator aggregator1 = new LongFirstAggregator("name", "field");
        LongFirstAggregator aggregator2 = new LongFirstAggregator("name1", "field1");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }

    @Test
    public void testEqualsWithAnotherSubClass() {
        LongFirstAggregator aggregator1 = new LongFirstAggregator("name", "field");
        CountAggregator aggregator2 = new CountAggregator("countAgg1");

        Assert.assertNotEquals(aggregator1, aggregator2);
    }
}