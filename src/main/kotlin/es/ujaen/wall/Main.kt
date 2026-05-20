package es.ujaen.wall

import es.ujaen.wall.model.Post
import es.ujaen.wall.repository.PostRepository
import es.ujaen.wall.repository.createSessionFactory

fun main() {
    createSessionFactory().use { sessionFactory ->
        val postRepository = PostRepository(sessionFactory)

        val post = Post("CyZ", "Hello, world!")

        postRepository.save(post)

        println(post)

        println(postRepository.findByIdOrNull(post.id!!))
        println(postRepository.findAll())
    }
}
