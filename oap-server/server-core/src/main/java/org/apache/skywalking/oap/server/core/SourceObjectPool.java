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
 */

package org.apache.skywalking.oap.server.core;

import io.netty.util.Recycler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.oap.server.core.source.Source;

@Slf4j
public final class SourceObjectPool {
    private static final Map<Class<? extends Source>, Recycler<Source>> POOLS =
        new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T extends Source> T get(Class<T> type) {
        final Recycler<Source> pool = POOLS.computeIfAbsent(type, __ -> new Recycler<Source>() {
            @Override
            protected Source newObject(Handle<Source> handle) {
                try {
                    final Source m = type.getDeclaredConstructor().newInstance();
                    m.handle(handle);
                    return m;
                } catch (Exception e) {
                    log.error("Failed to create object for {}", type, e);
                    throw new RuntimeException(e);
                }
            }
        });
        return (T) pool.get();
    }
}
