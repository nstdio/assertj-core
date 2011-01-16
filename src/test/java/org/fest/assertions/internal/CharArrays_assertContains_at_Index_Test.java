/*
 * Created on Dec 20, 2010
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
package org.fest.assertions.internal;

import static org.fest.assertions.data.Index.atIndex;
import static org.fest.assertions.error.DoesNotContainAtIndex.doesNotContainAtIndex;
import static org.fest.assertions.test.CharArrayFactory.*;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.*;
import static org.fest.assertions.test.TestData.*;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.data.Index;
import org.fest.assertions.test.ExpectedException;
import org.junit.*;

/**
 * Tests for <code>{@link CharArrays#assertContains(AssertionInfo, char[], char, Index)}</code>.
 *
 * @author Alex Ruiz
 */
public class CharArrays_assertContains_at_Index_Test {

  private static char[] actual;

  @Rule public ExpectedException thrown = none();

  private Failures failures;
  private CharArrays arrays;

  @BeforeClass public static void setUpOnce() {
    actual = array('a', 'b', 'c');
  }

  @Before public void setUp() {
    failures = spy(new Failures());
    arrays = new CharArrays(failures);
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(unexpectedNull());
    arrays.assertContains(someInfo(), null, 'a', someIndex());
  }

  @Test public void should_fail_if_actual_is_empty() {
    thrown.expectAssertionError(unexpectedEmpty());
    arrays.assertContains(someInfo(), emptyArray(), 'a', someIndex());
  }

  @Test public void should_throw_error_if_Index_is_null() {
    thrown.expectNullPointerException("Index should not be null");
    arrays.assertContains(someInfo(), actual, 'a', null);
  }

  @Test public void should_throw_error_if_Index_is_out_of_bounds() {
    thrown.expectIndexOutOfBoundsException("Index should be between <0> and <2> (inclusive,) but was <6>");
    arrays.assertContains(someInfo(), actual, 'a', atIndex(6));
  }

  @Test public void should_fail_if_actual_does_not_contain_value_at_index() {
    AssertionInfo info = someInfo();
    Index index = atIndex(1);
    try {
      arrays.assertContains(info, actual, 'a', index);
    } catch (AssertionError e) {
      verify(failures).failure(info, doesNotContainAtIndex(actual, 'a', index, 'b'));
      return;
    }
    fail("expected AssertionError not thrown");
  }

  @Test public void should_pass_if_actual_contains_value_at_index() {
    arrays.assertContains(someInfo(), actual, 'b', atIndex(1));
  }
}
