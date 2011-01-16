/*
 * Created on Oct 12, 2010
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
package org.fest.assertions.error;

import static junit.framework.Assert.assertEquals;
import static org.fest.util.Collections.*;

import org.fest.assertions.description.*;
import org.junit.*;

/**
 * Tests for <code>{@link Contains#create(Description)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Contains_create_Test {

  private ErrorMessage errorMessage;

  @Before public void setUp() {
    errorMessage = Contains.contains(list("Yoda"), list("Luke", "Yoda"), set("Yoda"));
  }

  @Test public void should_create_error_message() {
    String message = errorMessage.create(new TextDescription("Test"));
    assertEquals("[Test] expecting:<['Yoda']> not to contain:<['Luke', 'Yoda']> but found:<['Yoda']>", message);
  }
}
