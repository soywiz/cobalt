package org.hexworks.cobalt.examples

import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.http.content.resource
import io.ktor.http.content.static
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.script
import kotlinx.html.title

fun main() {
    embeddedServer(Netty, port = 8080) {
        routing {
            get("/databinding-example") {
                call.respondHtml {
                    head {
                        title("Cobalt Databinding Example!")
                    }
                    body {

                        script(src = "/static/cobalt.examples.js") {}
                    }
                }
            }
            static("/static") {
                resource("cobalt.examples.js")
            }
        }
    }.start(wait = true)
}