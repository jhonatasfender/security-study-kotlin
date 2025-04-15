package com.security.usecases

import com.security.usecases.ports.UseCase
import org.springframework.stereotype.Component

@Component
class UseCaseExecutor {
    fun <RequestDto, Request, Response, ResponseDto> execute(
        useCase: UseCase<Request, Response>,
        requestDto: RequestDto,
        requestConverter: (RequestDto) -> Request,
        responseConverter: (Response) -> ResponseDto,
    ): ResponseDto {
        val request = requestConverter(requestDto)
        val response = useCase.execute(request)
        return responseConverter(response)
    }
}
