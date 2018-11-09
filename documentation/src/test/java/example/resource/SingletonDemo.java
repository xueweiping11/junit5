/*
 * Copyright 2015-2018 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package example.resource;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.net.URI;
import java.util.List;

import com.sun.net.httpserver.HttpServer;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ResourceSupplier.Singleton;

class SingletonDemo {

	@Test
	void usingSharedServerInstance(@Singleton(WebServer.class) WebServer server) {
		var actual = WebServer.getLines(server.getUri());
		assertLinesMatch(List.of("counter = [1|2|3|4]"), actual);
	}

	@Test
	void usingSharedServerInstance(@Singleton(WebServer.class) HttpServer server) {
		var host = server.getAddress().getHostName();
		var port = server.getAddress().getPort();
		var actual = WebServer.getLines(URI.create("http://" + host + ":" + port));
		assertLinesMatch(List.of("counter = [1|2|3|4]"), actual);
	}

	@Nested
	class SecondLayer {

		@Test
		void usingSharedServerInstance(@Singleton(WebServer.class) WebServer server) {
			var actual = WebServer.getLines(server.getUri());
			assertLinesMatch(List.of("counter = [1|2|3|4]"), actual);
		}
	}
}
