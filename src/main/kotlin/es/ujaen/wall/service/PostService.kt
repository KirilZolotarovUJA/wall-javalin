package es.ujaen.wall.service

import es.ujaen.wall.exception.InvalidPostAuthorException
import es.ujaen.wall.exception.InvalidPostContentException
import es.ujaen.wall.exception.PostNotFoundException
import es.ujaen.wall.model.Post
import es.ujaen.wall.model.PostCreateRequest
import es.ujaen.wall.repository.PostRepository

class PostService(private val postRepository: PostRepository) {
    companion object {
        private const val MAX_AUTHOR_LENGTH = 32
        private const val MAX_CONTENT_LENGTH = 256
    }

    fun getAllPosts(): List<Post> {
        return postRepository.findAll()
    }

    fun createPost(request: PostCreateRequest): Post {
        validatePostCreateRequest(request)

        val post = Post(
            author = request.author,
            content = request.content,
        )

        return postRepository.save(post)
    }

    fun getPostById(id: Long): Post {
        return postRepository.findByIdOrNull(id) ?: throw PostNotFoundException(id)
    }

    private fun validatePostCreateRequest(request: PostCreateRequest) {
        if (request.author.isBlank() || request.author.length > MAX_AUTHOR_LENGTH) {
            throw InvalidPostAuthorException()
        }

        if (request.content.isBlank() || request.content.length > MAX_CONTENT_LENGTH) {
            throw InvalidPostContentException()
        }
    }
}
