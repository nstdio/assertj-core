/*
 * Created on Oct 30, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.data;

import static junit.framework.Assert.assertEquals;
import static org.fest.assertions.data.Index.atIndex;

import org.junit.*;

/**
 * Tests for <code>{@link Index#toString()}</code>.
 *
 * @author Alex Ruiz
 */
public class Index_toString_Test {

  private static Index offset;

  @BeforeClass public static void setUpOnce() {
    offset = atIndex(8);
  }

  @Test public void should_implement_toString() {
    assertEquals("Index[value=8]", offset.toString());
  }
}
