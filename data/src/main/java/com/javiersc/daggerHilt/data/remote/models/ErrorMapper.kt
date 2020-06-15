package com.javiersc.daggerHilt.data.remote.models

import com.javiersc.daggerHilt.domain.models.Error
import com.javiersc.resources.networkResponse.Headers

fun errorToDomain(error: Unit?, code: Int, headers: Headers) = Error.DEFAULT

fun unknownErrorToDomain(throwable: Throwable) = Error.DEFAULT

fun internetNotAvailableToDomain(message: String) = Error.DEFAULT
