package es.ujaen.wall

import es.ujaen.wall.model.PostCreateRequest
import es.ujaen.wall.repository.PostRepository
import es.ujaen.wall.repository.createSessionFactory
import es.ujaen.wall.service.PostService

fun main() {
    createSessionFactory().use { sessionFactory ->
        val postRepository = PostRepository(sessionFactory)
        val postService = PostService(postRepository)

        val post = postService.createPost(PostCreateRequest("CyZ", "Hello, world!"))

        println(post)

        println(postService.getPostById(post.id!!))
        println(postService.getAllPosts())
    }
}
