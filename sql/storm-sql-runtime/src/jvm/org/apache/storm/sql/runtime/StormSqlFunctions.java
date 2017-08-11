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

package org.apache.storm.sql.runtime;

public class StormSqlFunctions {

    /**
     * Whether the object equals the other one.
     * @param b0 one object
     * @param b1 the other object
     * @return true if the object equals the other one
     */
    public static Boolean eq(Object b0, Object b1) {
        if (b0 == null || b1 == null) {
            return null;
        }
        return b0.equals(b1);
    }

    /**
     * Whether the object dose not equals the other one.
     * @param b0 one object
     * @param b1 the other object
     * @return true if the object dose not equals the other one
     */
    public static Boolean ne(Object b0, Object b1) {
        if (b0 == null || b1 == null) {
            return null;
        }
        return !b0.equals(b1);
    }
}
