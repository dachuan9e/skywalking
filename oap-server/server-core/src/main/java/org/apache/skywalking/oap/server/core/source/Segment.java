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

package org.apache.skywalking.oap.server.core.source;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.apache.skywalking.oap.server.core.analysis.manual.searchtag.Tag;

import static org.apache.skywalking.oap.server.core.source.DefaultScopeDefine.SEGMENT;

@Getter
@Setter
@ScopeDeclaration(id = SEGMENT, name = "Segment")
public class Segment extends Source {

    @Override
    public int scope() {
        return DefaultScopeDefine.SEGMENT;
    }

    @Override
    public String getEntityId() {
        return segmentId;
    }

    private String segmentId;
    private String traceId;
    private String serviceId;
    private String serviceInstanceId;
    private String endpointName;
    private String endpointId;
    private long startTime;
    private int latency;
    private int isError;
    private byte[] dataBinary;
    private List<Tag> tags = new ArrayList<>();

    @Override
    public void recycle() {
        segmentId = null;
        traceId = null;
        serviceId = null;
        serviceInstanceId = null;
        endpointName = null;
        endpointId = null;
        startTime = 0;
        latency = 0;
        isError = 0;
        dataBinary = null;
        tags.clear();
        setTimeBucket(0);
        handle.recycle(this);
    }
}
