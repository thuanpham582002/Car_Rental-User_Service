package dev.noroom113.customermanagementservice.exception

sealed class BaseException(
    val code: Int,
    override val message: String
) : RuntimeException(message){
    class UserExistsException : BaseException(1001, "User already exists")
    class UserNameNotFoundException : BaseException(1002, "User name not found")
    class AccessibilityAlreadyExistsException : BaseException(1003, "Accessibility already exists")
}
