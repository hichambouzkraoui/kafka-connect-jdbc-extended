/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.connect.jdbc.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChangeTrackingOffset {
  private static final Logger log = LoggerFactory.getLogger(JdbcSourceTask.class);
  static final String CHANGE_TRACKING_OFFSET_FIELD = "sys_change_version";

  private final Long changeVersionOffset;

  /**
   * @param changeVersionOffset the incrementing offset.
   *                           If null, {@link #getChangeVersionOffset()} will return 0.
   */
  public ChangeTrackingOffset(Long changeVersionOffset) {
    this.changeVersionOffset = changeVersionOffset;
  }

  public long getChangeVersionOffset() {
    return changeVersionOffset == null ? 0 : changeVersionOffset;
  }

  public Map<String, Object> toMap() {
    Map<String, Object> map = new HashMap<>(1);
    if (changeVersionOffset != null) {
      map.put(CHANGE_TRACKING_OFFSET_FIELD, changeVersionOffset);
    }
    return map;
  }

  public static ChangeTrackingOffset fromMap(Map<String, ?> map) {
    if (map == null || map.isEmpty()) {
      return new ChangeTrackingOffset(null);
    }
    Long versionOffset = (Long) map.get(CHANGE_TRACKING_OFFSET_FIELD);
    return new ChangeTrackingOffset(versionOffset);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ChangeTrackingOffset that = (ChangeTrackingOffset) o;

    return Objects.equals(changeVersionOffset, that.changeVersionOffset);
  }

  @Override
  public int hashCode() {
    int result = changeVersionOffset != null ? changeVersionOffset.hashCode() : 0;
    return result;
  }
}
