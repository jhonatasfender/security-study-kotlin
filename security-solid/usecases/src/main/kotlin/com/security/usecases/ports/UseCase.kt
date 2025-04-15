package com.security.usecases.ports

interface UseCase<in Request, out Response> {
    fun execute(request: Request): Response
}
