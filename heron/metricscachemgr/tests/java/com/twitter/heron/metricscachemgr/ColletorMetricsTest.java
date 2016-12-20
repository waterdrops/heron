// Copyright 2016 Twitter. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.twitter.heron.metricscachemgr;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.twitter.heron.spi.metricsmgr.metrics.MetricsFilter.MetricAggregationType;

// test CollectorMetrics
public class ColletorMetricsTest {

  public static final String CONFIG_PATH =
      "/Users/huijunw/workspace/heron/heron/config/src/yaml/conf/examples/metrics_sinks.yaml";
  private CollectorMetrics cm = null;

  @Before
  public void before() throws Exception {
    cm = new CollectorMetrics(CONFIG_PATH);
  }

  @After
  public void after() {
    // nop
  }

  @Test
  public void testGetAggregationType() {
    Assert.assertEquals(cm.getMetricsFilter().getAggregationType("__jvm-uptime-secs"),
        MetricAggregationType.LAST);
    Assert.assertEquals(cm.getMetricsFilter().getAggregationType("__fail-count"),
        MetricAggregationType.SUM);
  }

  @Test
  public void testIsCollectorMetric() {
    Assert.assertTrue(cm.getMetricsFilter().contains("__time_spent_back_pressure_by_compid"));
    Assert.assertFalse(cm.getMetricsFilter().contains("__time_spent_back_x_pressure_by_compid"));
  }
}
