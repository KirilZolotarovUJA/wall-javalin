package es.ujaen.wall.model

import jakarta.persistence.*

@Entity
class Post(
    @Column(nullable = false, length = 32)
    var author: String,

    @Column(nullable = false, length = 256)
    var content: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
) {
    override fun toString(): String = "Post(id=$id, author=$author)"
}

data class PostCreateRequest(
    val author: String,
    val content: String,
)
