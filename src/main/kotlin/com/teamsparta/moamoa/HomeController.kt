package com.teamsparta.moamoa

import com.teamsparta.moamoa.infra.security.UserPrincipal
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class HomeController {
    @GetMapping("/start8080")
    fun home(): String {
        return "index"
    }

    @GetMapping("/order")
    fun goToOrderPage(
        @RequestParam productId: String,
    ): String {
        return "order"
    }

    @GetMapping("/groupOrder")
    fun goToGroupOrderPage(
        @RequestParam productId: String,
    ): String {
        return "groupOrder"
    }

    @GetMapping("/product-detail")
    fun goToProductDetail(
        @RequestParam productId: String,
    ): String {
        return "product-detail"
    }

    @GetMapping("/my-order-page")
    fun myOrderPage(): String {
        return "my-order-page"
    }

}
