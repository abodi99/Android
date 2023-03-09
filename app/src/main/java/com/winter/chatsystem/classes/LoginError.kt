package com.winter.chatsystem.classes

sealed class LoginError {
    object EmptyEmail : LoginError()
    object InvalidEmail : LoginError()
    object EmptyPassword : LoginError()
    object WeakPassword : LoginError()
    object nonMatchPassword: LoginError()
    object Unknown : LoginError()

    object EmailAlreadyExists : LoginError()
}