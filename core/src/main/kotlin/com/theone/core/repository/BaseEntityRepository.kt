package com.theone.core.repository

import com.theone.core.domain.BaseEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

/**
 * @author Aaron.Liu
 * @email bravoon@126.com
 * @date 20/11/2018 22:11
 * @description
 */
@NoRepositoryBean
interface BaseEntityRepository<T : BaseEntity> : JpaRepository<T, Long> {

    fun findByDeleted(deleted: Boolean): List<T>

    fun findByDeleted(deleted: Boolean, pageable: Pageable): Page<T>
}
