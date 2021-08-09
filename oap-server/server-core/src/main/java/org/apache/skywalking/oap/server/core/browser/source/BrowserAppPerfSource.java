/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.skywalking.oap.server.core.browser.source;

import lombok.Getter;
import lombok.Setter;
import org.apache.skywalking.oap.server.core.analysis.NodeType;
import org.apache.skywalking.oap.server.core.source.Source;

/**
 * Browser performance details
 */
@Setter
@Getter
public abstract class BrowserAppPerfSource extends Source {
    protected String name;
    protected final NodeType nodeType = NodeType.Browser;
    private int redirectTime;
    private int dnsTime;
    private int ttfbTime;
    private int tcpTime;
    private int transTime;
    private int domAnalysisTime;
    private int fptTime;
    private int domReadyTime;
    private int loadPageTime;
    private int resTime;
    private int sslTime;
    private int ttlTime;
    private int firstPackTime;
    private int fmpTime;

    @Override
    public void recycle() {
        name = null;
        redirectTime = 0;
        dnsTime = 0;
        ttfbTime = 0;
        tcpTime = 0;
        transTime = 0;
        domAnalysisTime = 0;
        fptTime = 0;
        domReadyTime = 0;
        loadPageTime = 0;
        resTime = 0;
        sslTime = 0;
        ttlTime = 0;
        firstPackTime = 0;
        fmpTime = 0;
        setTimeBucket(0);
        handle.recycle(this);
        recycle0();
    }

    protected abstract void recycle0();
}
