package com.study.security.controller

import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController : HelloApi {
    override fun hello(): String = "Hello, World!"
} 
