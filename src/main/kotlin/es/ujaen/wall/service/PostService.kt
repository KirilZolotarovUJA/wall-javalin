package es.ujaen.wall.service

import es.ujaen.wall.exception.PostNotFoundException
import es.ujaen.wall.model.Post
import es.ujaen.wall.model.PostCreateRequest
import es.ujaen.wall.repository.PostRepository

class PostService(private val postRepository: PostRepository) {
    fun getAllPosts(): List<Post> {
        return postRepository.findAll()
    }

    fun createPost(request: PostCreateRequest): Post {
        val post = Post(
            author = request.author,
            content = request.content,
        )

        return postRepository.save(post)
    }

    fun getPostById(id: Long): Post {
        return postRepository.findByIdOrNull(id) ?: throw PostNotFoundException(id)
    }
}
