/*
 * Copyright 2015-2018 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.params;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @since 5.4
 */
class Issue1665Tests {

	// This is ok
	static Stream<int[]> arrayStream1() {
		return Stream.of(new int[] { 1 }, new int[] { 2 });
	}

	@ParameterizedTest
	@MethodSource("arrayStream1")
	void test1(int[] par) {
		System.err.println(Arrays.toString(par));
	}

	// This is not
	static Stream<int[][]> arrayStream2() {
		return Stream.of(new int[][] { { 1, 2 } }, new int[][] { { 2, 3 } });
	}

	@ParameterizedTest
	@MethodSource("arrayStream2")
	void test2(int[][] par) {
		System.err.println(Arrays.deepToString(par));
	}

	// This is not
	static Stream<Object[]> arrayStream3() {
		return Stream.of(new Object[] { new int[][] { { 1, 2 } } }, new Object[] { new int[][] { { 2, 3 } } });
	}

	@ParameterizedTest
	@MethodSource("arrayStream3")
	void test3(int[][] par) {
		System.err.println(Arrays.deepToString(par));
	}

	static Stream<Arguments> arrayStream4() {
		return Stream.of(arguments((Object) new int[][] { { 1, 2 } }), arguments((Object) new int[][] { { 2, 3 } }));
	}

	@ParameterizedTest
	@MethodSource("arrayStream4")
	void test4(int[][] par) {
		System.err.println(Arrays.deepToString(par));
	}

}
