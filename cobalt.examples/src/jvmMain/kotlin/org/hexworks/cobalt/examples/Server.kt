package org.hexworks.cobalt.examples

import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.http.content.resource
import io.ktor.http.content.static
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.html.InputType
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.form
import kotlinx.html.head
import kotlinx.html.id
import kotlinx.html.input
import kotlinx.html.label
import kotlinx.html.link
import kotlinx.html.nav
import kotlinx.html.script
import kotlinx.html.title

fun main() {
    embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respondHtml {
                    head {
                        title("Cobalt HTML Example")
                        link(
                                href = "https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css",
                                rel = "stylesheet")
                    }
                    body {
                        nav("navbar navbar-dark bg-dark") {
                            div("container") {
                                div("row") {
                                    a(href = "#", classes = "navbar-brand") {
                                        +"Cobalt HTML Showcase"
                                    }
                                }
                            }
                        }
                        div("container mt-3") {
                            div("row") {
                                div("col") {
                                    div("card") {
                                        div("card-header") {
                                            +"Form with Databinding and Validation"
                                        }
                                        div("card-body") {
                                            form(classes = "needs-validation") {
                                                id = "registerForm"
                                                div("form-row") {
                                                    div("form-group col-md-6") {
                                                        label {
                                                            htmlFor = "email"
                                                            +"Email"
                                                        }
                                                        input(type = InputType.email, classes = "form-control") {
                                                            id = "email"
                                                        }
                                                    }
                                                    div("form-group col-md-6") {
                                                        label {
                                                            htmlFor = "password"
                                                            +"Password"
                                                        }
                                                        input(type = InputType.password, classes = "form-control") {
                                                            id = "password"
                                                        }
                                                    }
                                                }
                                                div("form-row") {
                                                    div("form-group col-md-4") {
                                                        label {
                                                            htmlFor = "firstName"
                                                            +"First Name"
                                                        }
                                                        input(type = InputType.text, classes = "form-control") {
                                                            id = "firstName"
                                                        }
                                                        div("invalid-feedback") {
                                                            +"First Name is missing"
                                                        }
                                                    }
                                                    div("form-group col-md-4") {
                                                        label {
                                                            htmlFor = "lastName"
                                                            +"Last Name"
                                                        }
                                                        input(type = InputType.text, classes = "form-control") {
                                                            id = "lastName"
                                                        }
                                                        div("invalid-feedback") {
                                                            +"Last Name is missing"
                                                        }
                                                    }
                                                    div("form-group col-md-4") {
                                                        label {
                                                            htmlFor = "fullName"
                                                            +"Full Name"
                                                        }
                                                        input(type = InputType.text, classes = "form-control") {
                                                            id = "fullName"
                                                            readonly = true
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        script(src = "https://code.jquery.com/jquery-3.4.1.slim.min.js") {}
                        script(src = "https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js") {}
                        script(src = "https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js") {}
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