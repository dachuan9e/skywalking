/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.oap.server.core.analysis.manual.service;

import java.util.Map;
import org.apache.skywalking.oap.server.core.MetricsObjectPool;
import org.apache.skywalking.oap.server.core.analysis.NodeType;
import org.junit.Assert;
import org.junit.Test;

public class ServiceTrafficTest {
    @Test
    public void testGrouping() {
        ServiceTraffic traffic = MetricsObjectPool.get(ServiceTraffic.class);
        traffic.setName("group-name::service-name");
        traffic.setNodeType(NodeType.Normal);
        final Map<String, Object> stringObjectMap = new ServiceTraffic.Builder().entity2Storage(traffic);
        Assert.assertEquals("group-name", stringObjectMap.get(ServiceTraffic.GROUP));
    }

    @Test
    public void testNoGrouping() {
        ServiceTraffic traffic = MetricsObjectPool.get(ServiceTraffic.class);
        traffic.setName("group-name:service-name:no");
        traffic.setNodeType(NodeType.Normal);
        final Map<String, Object> stringObjectMap = new ServiceTraffic.Builder().entity2Storage(traffic);
        Assert.assertNull(stringObjectMap.get(ServiceTraffic.GROUP));
    }
}
