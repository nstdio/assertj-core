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

import static org.fest.assertions.error.DoesNotEndWith.doesNotEndWith;
import static org.fest.assertions.test.DoubleArrayFactory.*;
import static org.fest.assertions.test.ErrorMessages.*;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.unexpectedNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.test.ExpectedException;
import org.junit.*;

/**
 * Tests for <code>{@link DoubleArrays#assertEndsWith(AssertionInfo, double[], double[])}</code>.
 *
 * @author Alex Ruiz
 */
public class DoubleArrays_assertEndsWith_Test {

  private static double[] actual;

  @Rule public ExpectedException thrown = none();

  private Failures failures;
  private DoubleArrays arrays;

  @BeforeClass public static void setUpOnce() {
    actual = array(6d, 8d, 10d, 12d);
  }

  @Before public void setUp() {
    failures = spy(new Failures());
    arrays = new DoubleArrays(failures);
  }

  @Test public void should_throw_error_if_sequence_is_null() {
    thrown.expectNullPointerException(valuesToLookForIsNull());
    arrays.assertEndsWith(someInfo(), actual, null);
  }

  @Test public void should_throw_error_if_sequence_is_empty() {
    thrown.expectIllegalArgumentException(valuesToLookForIsEmpty());
    arrays.assertEndsWith(someInfo(), actual, emptyArray());
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(unexpectedNull());
    arrays.assertEndsWith(someInfo(), null, array(8d));
  }

  @Test public void should_fail_if_sequence_is_bigger_than_actual() {
    AssertionInfo info = someInfo();
    double[] sequence = { 6d, 8d, 10d, 12d, 20d, 22d };
    try {
      arrays.assertEndsWith(info, actual, sequence);
    } catch (AssertionError e) {
      shouldHaveFailedIfActualDoesNotEndWithSequence(info, sequence);
      return;
    }
    fail("expected AssertionError not thrown");
  }

  @Test public void should_fail_if_actual_does_not_end_with_sequence() {
    AssertionInfo info = someInfo();
    double[] sequence = { 20d, 22d };
    try {
      arrays.assertEndsWith(info, actual, sequence);
    } catch (AssertionError e) {
      shouldHaveFailedIfActualDoesNotEndWithSequence(info, sequence);
      return;
    }
    fail("expected AssertionError not thrown");
  }

  @Test public void should_fail_if_actual_ends_with_first_elements_of_sequence_only() {
    AssertionInfo info = someInfo();
    double[] sequence = { 6d, 20d, 22d };
    try {
      arrays.assertEndsWith(info, actual, sequence);
    } catch (AssertionError e) {
      shouldHaveFailedIfActualDoesNotEndWithSequence(info, sequence);
      return;
    }
    fail("expected AssertionError not thrown");
  }

  private void shouldHaveFailedIfActualDoesNotEndWithSequence(AssertionInfo info, double[] sequence) {
    verify(failures).failure(info, doesNotEndWith(actual, sequence));
  }

  @Test public void should_pass_if_actual_ends_with_sequence() {
    arrays.assertEndsWith(someInfo(), actual, array(8d, 10d, 12d));
  }

  @Test public void should_pass_if_actual_and_sequence_are_equal() {
    arrays.assertEndsWith(someInfo(), actual, array(6d, 8d, 10d, 12d));
  }
}
