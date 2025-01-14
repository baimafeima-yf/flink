/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.runtime.state.v2;

import org.apache.flink.api.common.functions.SerializerFactory;
import org.apache.flink.api.common.state.v2.AggregatingState;
import org.apache.flink.api.common.state.v2.ListState;
import org.apache.flink.api.common.state.v2.MapState;
import org.apache.flink.api.common.state.v2.ReducingState;
import org.apache.flink.api.common.state.v2.ValueState;
import org.apache.flink.runtime.state.AsyncKeyedStateBackend;
import org.apache.flink.runtime.state.VoidNamespace;
import org.apache.flink.runtime.state.VoidNamespaceSerializer;
import org.apache.flink.util.Preconditions;

import javax.annotation.Nonnull;

/** Default implementation of KeyedStateStore. */
public class DefaultKeyedStateStore implements KeyedStateStore {

    private final AsyncKeyedStateBackend<?> asyncKeyedStateBackend;
    protected final SerializerFactory serializerFactory;

    public DefaultKeyedStateStore(
            @Nonnull AsyncKeyedStateBackend asyncKeyedStateBackend,
            SerializerFactory serializerFactory) {
        this.asyncKeyedStateBackend = Preconditions.checkNotNull(asyncKeyedStateBackend);
        this.serializerFactory = Preconditions.checkNotNull(serializerFactory);
    }

    @Override
    public <T> ValueState<T> getValueState(@Nonnull ValueStateDescriptor<T> stateProperties) {
        Preconditions.checkNotNull(stateProperties, "The state properties must not be null");
        try {
            stateProperties.initializeSerializerUnlessSet(serializerFactory);
            return asyncKeyedStateBackend.getOrCreateKeyedState(
                    VoidNamespace.INSTANCE, VoidNamespaceSerializer.INSTANCE, stateProperties);
        } catch (Exception e) {
            throw new RuntimeException("Error while getting state", e);
        }
    }

    @Override
    public <T> ListState<T> getListState(@Nonnull ListStateDescriptor<T> stateProperties) {
        Preconditions.checkNotNull(stateProperties, "The state properties must not be null");
        try {
            stateProperties.initializeSerializerUnlessSet(serializerFactory);
            return asyncKeyedStateBackend.getOrCreateKeyedState(
                    VoidNamespace.INSTANCE, VoidNamespaceSerializer.INSTANCE, stateProperties);
        } catch (Exception e) {
            throw new RuntimeException("Error while getting state", e);
        }
    }

    @Override
    public <UK, UV> MapState<UK, UV> getMapState(
            @Nonnull MapStateDescriptor<UK, UV> stateProperties) {
        Preconditions.checkNotNull(stateProperties, "The state properties must not be null");
        try {
            stateProperties.initializeSerializerUnlessSet(serializerFactory);
            return asyncKeyedStateBackend.getOrCreateKeyedState(
                    VoidNamespace.INSTANCE, VoidNamespaceSerializer.INSTANCE, stateProperties);
        } catch (Exception e) {
            throw new RuntimeException("Error while getting state", e);
        }
    }

    @Override
    public <T> ReducingState<T> getReducingState(
            @Nonnull ReducingStateDescriptor<T> stateProperties) {
        Preconditions.checkNotNull(stateProperties, "The state properties must not be null");
        try {
            stateProperties.initializeSerializerUnlessSet(serializerFactory);
            return asyncKeyedStateBackend.getOrCreateKeyedState(
                    VoidNamespace.INSTANCE, VoidNamespaceSerializer.INSTANCE, stateProperties);
        } catch (Exception e) {
            throw new RuntimeException("Error while getting state", e);
        }
    }

    @Override
    public <IN, ACC, OUT> AggregatingState<IN, OUT> getAggregatingState(
            @Nonnull AggregatingStateDescriptor<IN, ACC, OUT> stateProperties) {
        Preconditions.checkNotNull(stateProperties, "The state properties must not be null");
        try {
            stateProperties.initializeSerializerUnlessSet(serializerFactory);
            return asyncKeyedStateBackend.getOrCreateKeyedState(
                    VoidNamespace.INSTANCE, VoidNamespaceSerializer.INSTANCE, stateProperties);
        } catch (Exception e) {
            throw new RuntimeException("Error while getting state", e);
        }
    }
}
