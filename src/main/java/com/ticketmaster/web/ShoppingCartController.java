package com.ticketmaster.web;

import com.ticketmaster.service.ShoppingCartService;
import com.ticketmaster.model.ShoppingCart;
import com.ticketmaster.service.TicketService;
import com.ticketmaster.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

//TODO: ne e menuvan prezemen od aud gitlab


@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final TicketService ticketService;
    private final UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, TicketService ticketService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("tickets", this.shoppingCartService.listAllTicketsInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent", "shopping-cart");
        return "masterTemplate";
    }

    @PostMapping("/add-event/{id}")
    public String addTicketToShoppingCart(@PathVariable Long id, @RequestParam int quantity,  HttpServletRequest req, Authentication authentication) {
        try {
            // = (User) authentication.getPrincipal();
          //  User user = this.userService.findByUsername(req.getRemoteUser());
            this.shoppingCartService.addTicketToShoppingCart(req.getRemoteUser(), id, quantity);
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteTicket(@PathVariable Long id,HttpServletRequest req){
        try {
            this.shoppingCartService.deleteTicket(req.getRemoteUser(), id);
            this.ticketService.delete(id);
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }
}

