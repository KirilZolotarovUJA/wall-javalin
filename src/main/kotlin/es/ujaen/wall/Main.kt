package es.ujaen.wall

import es.ujaen.wall.exception.PostNotFoundException
import es.ujaen.wall.model.PostCreateRequest
import es.ujaen.wall.repository.PostRepository
import es.ujaen.wall.repository.createSessionFactory
import es.ujaen.wall.service.PostService
import io.javalin.Javalin

fun main() {
    val sessionFactory = createSessionFactory()
    val postRepository = PostRepository(sessionFactory)
    val postService = PostService(postRepository)

    Runtime.getRuntime().addShutdownHook(
        Thread {
            sessionFactory.close()
        },
    )

    Javalin.create { config ->
        config.routes.exception(PostNotFoundException::class.java) { e, ctx ->
            ctx.status(404).json(mapOf("message" to e.message))
        }

        config.routes.get("/api/post") { ctx ->
            ctx.json(postService.getAllPosts())
        }

        config.routes.post("/api/post") { ctx ->
            val request = ctx.bodyAsClass(PostCreateRequest::class.java)
            ctx.status(201).json(postService.createPost(request))
        }

        config.routes.get("/api/post/{id}") { ctx ->
            val id = ctx.pathParam("id").toLong()
            ctx.json(postService.getPostById(id))
        }
    }.start(8080)
}
