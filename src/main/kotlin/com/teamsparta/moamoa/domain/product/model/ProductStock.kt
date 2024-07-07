package com.teamsparta.moamoa.domain.product.model

import com.teamsparta.moamoa.infra.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "product_stock")
class ProductStock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    //    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    var product: Product,
    @Column(name = "stock")
    var stock: Int,
    @Column(name = "product_name")
    val productName: String,
) : BaseTimeEntity() {
    companion object

    fun discountForTest(num: Int): ProductStock {
        val discountNum = stock - num
        return ProductStock(
            id = id!!,
            stock = discountNum,
            productName = productName,
            product = product,
        )
    }

    fun discount(num: Int) {
        this.stock - num
    }
}
