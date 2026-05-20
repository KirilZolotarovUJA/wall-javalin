package es.ujaen.wall.repository

import es.ujaen.wall.model.Post
import org.hibernate.SessionFactory

class PostRepository(private val sessionFactory: SessionFactory) {
    fun save(post: Post): Post {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.persist(post)
            session.transaction.commit()
        }

        return post
    }

    fun findAll(): List<Post> {
        return sessionFactory.openSession().use { session ->
            val criteriaBuilder = session.criteriaBuilder
            val criteriaQuery = criteriaBuilder.createQuery(Post::class.java)
            val postRoot = criteriaQuery.from(Post::class.java)

            criteriaQuery
                .select(postRoot)
                .orderBy(criteriaBuilder.asc(postRoot.get<Long>("id")))

            session.createQuery(criteriaQuery).resultList
        }
    }

    fun findByIdOrNull(id: Long): Post? {
        return sessionFactory.openSession().use { session ->
            session.find(Post::class.java, id)
        }
    }
}
