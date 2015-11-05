/**
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
package backtype.storm.windowing;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Invokes {@link TriggerHandler#onTrigger()} after the duration.
 */
public class TimeTriggerPolicy<T> implements TriggerPolicy<T> {
    private long duration;
    private final TriggerHandler handler;
    private final ScheduledExecutorService executor;

    public TimeTriggerPolicy(long millis, TriggerHandler handler) {
        this.duration = millis;
        this.handler = handler;
        this.executor = Executors.newSingleThreadScheduledExecutor();
        start();
    }

    @Override
    public void track(Event<T> event) {
        // NOOP
    }

    @Override
    public void reset() {
        // NOOP
    }

    @Override
    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(2, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private void start() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                handler.onTrigger();
            }
        };
        executor.scheduleAtFixedRate(task, duration, duration, TimeUnit.MILLISECONDS);
    }

    @Override
    public String toString() {
        return "TimeTriggerPolicy{" +
                "duration=" + duration +
                '}';
    }
}
