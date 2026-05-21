package es.ujaen.wall.exception

sealed class DomainException(message: String) : RuntimeException(message)

class PostNotFoundException(val id: Long) : DomainException("Post #$id not found")

sealed class ValidationException(message: String) : DomainException(message)

class InvalidPostAuthorException : ValidationException("Author must be between 1 and 32 characters")

class InvalidPostContentException : ValidationException("Content must be between 1 and 256 characters")
